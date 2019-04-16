package com.example.hp.readingyouself.readingDataSupport;


import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.example.hp.readingyouself.ChapterBodyActivity;
import com.example.hp.readingyouself.MaiActivityInterface;
import com.example.hp.readingyouself.ChapterActivity;
import com.example.hp.readingyouself.commentActivity.commentBean.BookCommentListBean;
import com.example.hp.readingyouself.commentActivity.commentBean.RecommendBookListBean;
import com.example.hp.readingyouself.readingDataSupport.dataForm.BookChapter;
import com.example.hp.readingyouself.readingDataSupport.dataForm.BookInCategoryBean;
import com.example.hp.readingyouself.readingDataSupport.dataForm.BookInformation;
import com.example.hp.readingyouself.readingDataSupport.dataForm.BookSummary;
import com.example.hp.readingyouself.readingDataSupport.dataForm.CategoryBean;
import com.example.hp.readingyouself.commentActivity.commentBean.ComprehensiveAndOriginalCommentBean;
import com.example.hp.readingyouself.readingDataSupport.dataForm.Rank;
import com.example.hp.readingyouself.readingDataSupport.locData.LocDataGiver;
import com.example.hp.readingyouself.readingDataSupport.netData.NetDataGiver;

import java.util.ArrayList;

/*
类简要描述：
    根据需求，提供相关的函数。负责将网络上拿到的或本地的信息进行整理。
    保存当前在看的书的章节，书基本情况等....
类具体描述：

*/
public class DataConnector {

    private Handler workHandler;
    private NetDataGiver netDataGiver;
    private LocDataGiver locDataGiver;
    private HandlerThread handlerThread;
    private Handler handler;
    
    private ArrayList<BookChapter> currentBookChapters;
    private BookInformation currentBookInformation;
    private BookSummary currentBookSummary;
    

    public DataConnector( ) {
        handlerThread = new HandlerThread("Net");
        handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper());
        locDataGiver = new LocDataGiver();//TODO(1) 先向本地获取，如果没有再生成
        netDataGiver = new NetDataGiver();
    }

    public void setWorkHandler(Handler handler){
        this.workHandler = handler;
    }

    //得到排行榜
    public final static int MALE_HOTTEST_RANKING_WEEK = 1;
    public final static int MALE_HOTTEST_RANKING_MONTH = 2;
    public final static int MALE_HOTTEST_RANKING_TOTAL = 3;
    public final static int FEMALE_HOTTEST_RANKING_WEEK = 4;
    public final static int FEMALE_HOTTEST_RANKING_MONTH = 5;
    public final static int FEMALE_HOTTEST_RANKING_TOTAL = 6;
    
    //获取排行榜
    public void sentRankToMainThread(final int rankName){
       handler.post(new Runnable() {
           @Override
           public void run() {
               Rank rank = null;
               switch (rankName){
                    case MALE_HOTTEST_RANKING_WEEK:
                        rank = netDataGiver.getLatestHottestRanking(NetDataGiver.MALE_HOTTEST_RANKING_WEEK_URL);
                        if( rank == null){
                            rank = locDataGiver.getMaleWeekRank();
                        }else {
                            locDataGiver.setMaleWeekRank(rank);
                        }
                        break;
                    case MALE_HOTTEST_RANKING_MONTH:
                        rank = netDataGiver.getLatestHottestRanking(NetDataGiver.MALE_HOTTEST_RANKING_MONTH_URL);
                        if( rank == null){
                            rank = locDataGiver.getMaleMonthRank();
                        }else {
                            locDataGiver.setMaleMonthRank(rank);
                        }
                        break;
                    case MALE_HOTTEST_RANKING_TOTAL:
                        rank = netDataGiver.getLatestHottestRanking(NetDataGiver.MALE_HOTTEST_RANKING_TOTAL_URL);
                        if( rank == null){
                            rank = locDataGiver.getMaleTotalRank();
                        }else {
                            locDataGiver.setMaleTotalRank(rank);
                        }
                        break;
                   case FEMALE_HOTTEST_RANKING_WEEK:
                       rank = netDataGiver.getLatestHottestRanking(NetDataGiver.FEMALE_HOTTEST_RANKING_WEEK_URL);
                       if( rank == null){
                           rank = locDataGiver.getFemaleWeekRank();
                       }else {
                           locDataGiver.setFemaleWeekRank(rank);
                       }
                       break;
                   case FEMALE_HOTTEST_RANKING_MONTH:
                       rank = netDataGiver.getLatestHottestRanking(NetDataGiver.FEMALE_HOTTEST_RANKING_MONTH_URL);
                       if( rank == null){
                           rank = locDataGiver.getFemaleMonthRank();
                       }else {
                           locDataGiver.setFemaleMonthRank(rank);
                       }
                       break;
                   case FEMALE_HOTTEST_RANKING_TOTAL:
                       rank = netDataGiver.getLatestHottestRanking(NetDataGiver.FEMALE_HOTTEST_RANKING_TOTAL_URL);
                       if( rank == null){
                           rank = locDataGiver.getFemaleTotalRank();
                       }else {
                           locDataGiver.setFemaleTotalRank(rank);
                       }
                       break;
               }
               if(workHandler != null){
                   Message message = workHandler.obtainMessage();
                   message.what = MaiActivityInterface.RANKING_FRAGMENT;
                   message.obj = rank;
                   workHandler.sendMessage(message);
               }
           }
       });
    }

    //获取章节信息   //TODO(2),并且发起网络访问之前，查看本地是否已经存在数据
    public void sentChapterList(final String bookID){
        handler.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<BookChapter> chapters = netDataGiver.getChapterBooks(bookID);
                if(chapters!=null){
                    currentBookChapters = chapters;
                    Message message = workHandler.obtainMessage();
                    message.what = ChapterActivity.CHAPTER;
                    message.obj = chapters;
                    workHandler.sendMessage(message);
                }
            }
        });
    }

    //获取章节对应的body
    public void sentChapterBody(final String urlChapter){
        handler.post(new Runnable() {
            @Override
            public void run() {
                String string = netDataGiver.getBodyByChapterUrl(urlChapter);
                //TODO(3)如何保存body
                if(string != null){
                    Message message = workHandler.obtainMessage();
                    message.what = ChapterBodyActivity.CHAPTER_BODY;
                    message.obj = string;
                    workHandler.sendMessage(message);
                }
            }
        });
    }

    //通过搜索
    public void senRankingBySearch(final String bookName){
        handler.post(new Runnable() {
            @Override
            public void run() {
                Rank rank = netDataGiver.search(bookName);
                if(rank != null){
                    Message message = workHandler.obtainMessage();
                    message.what = MaiActivityInterface.RANKING_FRAGMENT;
                    message.obj = rank;
                    workHandler.sendMessage(message);
                }//TODO(6)返回本地,已经保存的信息
            }
        });
    }

    //获取分类
    public void sentCategory(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                CategoryBean categoryBean = netDataGiver.getLatestCategory();
                if(categoryBean != null && workHandler != null){
                    Message message = workHandler.obtainMessage();
                    message.what = MaiActivityInterface.COMMUNITY_FRAGMENT;
                    message.obj = categoryBean;
                    workHandler.sendMessage(message);
                }//TODO(5)返回本地
            }
        });
    }

    //获取分类对应的书单
    public void sentCategoryList(final String type, final String major, final String minor, final String start, final String limit){
        handler.post(new Runnable() {
            @Override
            public void run() {
                BookInCategoryBean bookInCategoryBean = netDataGiver.getBookInCategoryBean(type,major,minor,start,limit);
                if(bookInCategoryBean != null && workHandler != null){
                    Message message = workHandler.obtainMessage();
                    message.obj = bookInCategoryBean;
                    workHandler.sendMessage(message);
                }else {
                    //TODO(6)从本地
                }
            }
        });
    }

    //获得综合评论区列表
    public void sentComprehensiveCommentList(final String sort,final int start,final int limit, final boolean distillate){
        handler.post(new Runnable() {
            @Override
            public void run() {
                ComprehensiveAndOriginalCommentBean comprehensiveCommentBean = netDataGiver.getComprehensiveCommentList(sort,start,limit,distillate);
                if(comprehensiveCommentBean == null){
                    //
                }else if (workHandler != null){
                        Message message = workHandler.obtainMessage();
                        message.obj = comprehensiveCommentBean;
                        workHandler.sendMessage(message);
                }
            }
        });
    }

    //原创区
    public void sentOriginalCommentList(final String sort,final int start,final int limit, final boolean distillate){
        handler.post(new Runnable() {
            @Override
            public void run() {
                ComprehensiveAndOriginalCommentBean comprehensiveCommentBean = netDataGiver.getOriginalCommentList(sort,start,limit,distillate);
                if(comprehensiveCommentBean == null){
                    //
                }else if (workHandler != null){
                    Message message = workHandler.obtainMessage();
                    message.obj = comprehensiveCommentBean;
                    workHandler.sendMessage(message);
                }
            }
        });
    }

    //获取书评区
    public void sendBookComment(final String sort, final String type, final int start, final int limit, final boolean distillate ){
        handler.post(new Runnable() {
            @Override
            public void run() {
                BookCommentListBean bookCommentBean = netDataGiver.getBookCommentBean(sort,type,start,limit,distillate );
                if(bookCommentBean == null){

                }else if(workHandler != null){
                    Message message = workHandler.obtainMessage();
                    message.obj = bookCommentBean;
                    workHandler.sendMessage(message);
                }
            }
        });
    }

    //获得书荒区
    public void sendRecommendBookListBean(final String sort, final int start, final int limit, final boolean distillate){
        handler.post(new Runnable() {
            @Override
            public void run() {
                RecommendBookListBean recommendBookListBean = netDataGiver.getRecommendBookListBean(sort,start,limit,distillate);
                if(recommendBookListBean == null){

                }else if(workHandler != null){
                    Message message = workHandler.obtainMessage();
                    message.obj = recommendBookListBean;
                    workHandler.sendMessage(message);
                }
            }
        });
    }

    public ArrayList<BookChapter> getCurrentBookChapters() {
        return currentBookChapters;
    }

    public BookInformation getCurrentBookInformation() {
        return currentBookInformation;
    }

    public BookSummary getCurrentBookSummary() {
        return currentBookSummary;
    }


}
