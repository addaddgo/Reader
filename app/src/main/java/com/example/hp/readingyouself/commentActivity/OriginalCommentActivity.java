package com.example.hp.readingyouself.commentActivity;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.hp.readingyouself.BaseActivity;
import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.commentActivity.commentBean.ComprehensiveAndOriginalCommentBean;
import com.example.hp.readingyouself.readingDataSupport.netData.NetConstantParameter;

public class OriginalCommentActivity extends ComprehensiveCommentActivity {

//    @Override
//    protected void onHandleMessage(Message msg) {
//        if(msg.obj instanceof ComprehensiveAndOriginalCommentBean){
//            bean = (ComprehensiveAndOriginalCommentBean)msg.obj;
//            recyclerAdapter.resetPosts();
//        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.community_usual_menu_all:
                if(currentDistillate){
                    dataConnector.sentOriginalCommentList(currentSort,0,20,false);
                    currentDistillate = false;
                }
                break;
            case R.id.community_usual_menu_distillate:
                if(!currentDistillate){
                    dataConnector.sentOriginalCommentList(currentSort,0,20,true);
                    currentDistillate = true;
                }
                break;
            case R.id.community_usual_menu_update:
                if(!currentSort.equals(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_UPDATED)){
                    dataConnector.sentOriginalCommentList(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_UPDATED,0,20,currentDistillate);
                    currentSort = NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_UPDATED;
                }
                break;
            case R.id.community_usual_menu_most:
                if(!currentSort.equals(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_MOST)){
                    dataConnector.sentOriginalCommentList(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_MOST,0,20,currentDistillate);
                    currentSort = NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_MOST;
                }
                break;
            case R.id.community_usual_menu_created:
                if(!currentSort.equals(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_CREATED)){
                    dataConnector.sentOriginalCommentList(NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_UPDATED,0,20,currentDistillate);
                    currentSort = NetConstantParameter.COMPREHENSIVE_COMMENT_SORT_CREATED;
                }
                break;
        }
       return true;
    }
}
