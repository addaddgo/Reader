package com.example.hp.readingyouself.findActivity;

import android.os.Message;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.example.hp.readingyouself.BaseActivity;
import com.example.hp.readingyouself.R;
import com.example.hp.readingyouself.findActivity.rankFragment.BaseRankFragment;
import com.example.hp.readingyouself.mainFragment.BookShelfFragment;
import com.example.hp.readingyouself.mainFragment.CommunityFragment;
import com.example.hp.readingyouself.mainFragment.RankingFragment;
import com.example.hp.readingyouself.readingDataSupport.DataConnector;
import com.example.hp.readingyouself.readingDataSupport.dataForm.AllRankingBean;

import java.util.List;

public class RankingActivity extends BaseActivity implements BaseRankFragment.Listener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.rank_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        maleRankFrg = new BaseRankFragment();
        maleRankFrg.setTitle(MALE);
        femaleRankFrg = new BaseRankFragment();
        femaleRankFrg.setTitle(FEMALE);
        pictureRankFrg = new BaseRankFragment();
        pictureRankFrg.setTitle(PICTURE);
        epubRankFrg = new BaseRankFragment();
        epubRankFrg.setTitle(EPUB);
        currentFragment = MALE;
        replaceFragment(maleRankFrg);
    }

    private String currentFragment;
    private final static String MALE = "男生";
    private final static String FEMALE = "女生";
    private final static String PICTURE = "Picture";
    private final static String EPUB = "Epub";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.rank_navigation_male:
                    if(!currentFragment.equals(MALE)){
                        replaceFragment(maleRankFrg);
//                        List maleList = allRankingBean.getMale();
//                        List<AllRankingBean.BaseBean> male = maleList;
//                        maleRankFrg.reSet(male);
                        currentFragment = MALE;
                        return true;
                    }
                case R.id.rank_navigation_female:
                    if(!currentFragment.equals(FEMALE)){
                        replaceFragment(femaleRankFrg);
//                        List femaleList = allRankingBean.getFemale();
//                        List<AllRankingBean.BaseBean> female = femaleList;
//                        femaleRankFrg.reSet(female);
                        currentFragment = FEMALE;
                        return true;
                    }
                case R.id.rank_navigation_picture:
                    if(!currentFragment.equals(PICTURE)){
                        replaceFragment(pictureRankFrg);
//                        List pictureList = allRankingBean.getPicture();
//                        List<AllRankingBean.BaseBean> picture = pictureList;
//                        femaleRankFrg.reSet(picture);
                        currentFragment =PICTURE;
                        return true;
                    }
                case R.id.rank_navigation_epub:
                    if(!currentFragment.equals(EPUB)){
                        replaceFragment(epubRankFrg);
//                        List epubList = allRankingBean.getEpub();
//                        List<AllRankingBean.BaseBean> epub = epubList;
//                        femaleRankFrg.reSet(epub);
                        currentFragment = EPUB;
                        return true;
                    }
            }
            return false;
        }
    };
    private DataConnector dataConnector;
    private AllRankingBean allRankingBean;

    @Override
    protected void onHandleMessage(Message msg) {
        if(msg.obj instanceof AllRankingBean){
            allRankingBean = (AllRankingBean)msg.obj;
            switch (currentFragment){
                case MALE:
                    List maleList = allRankingBean.getMale();
                    List<AllRankingBean.BaseBean> male = maleList;
                    maleRankFrg.reSet(male);
                    break;
                case FEMALE:
                    List femaleList = allRankingBean.getFemale();
                    List<AllRankingBean.BaseBean> female = femaleList;
                    femaleRankFrg.reSet(female);
                    break;
                case PICTURE:
                    List pictureList = allRankingBean.getPicture();
                    List<AllRankingBean.BaseBean> picture = pictureList;
                    femaleRankFrg.reSet(picture);
                    break;
                case EPUB:
                    List epubList = allRankingBean.getEpub();
                    List<AllRankingBean.BaseBean> epub = epubList;
                    femaleRankFrg.reSet(epub);
                    break;
            }
        }
    }

    @Override
    protected void onDataServiceConnect() {
        dataConnector = getDataConnector();
        dataConnector.sendAllRankinBean();
    }

    @Override
    protected void onDataServiceDisconnect() {
        dataConnector = null;
    }

    @Override
    public List<AllRankingBean.BaseBean> getBaseBean(String fragmentType) {
      if(dataConnector != null){
              switch (currentFragment){
                  case MALE:
                      List maleList = allRankingBean.getMale();
                      List<AllRankingBean.BaseBean> male = maleList;
                      return (List<AllRankingBean.BaseBean>)male;
                  case FEMALE:
                      List femaleList = allRankingBean.getFemale();
                      List<AllRankingBean.BaseBean> female = femaleList;
                      return  female;
                  case PICTURE:
                      List pictureList = allRankingBean.getPicture();
                      List<AllRankingBean.BaseBean> picture = pictureList;
                      return picture;
                  case EPUB:
                      List epubList = allRankingBean.getEpub();
                      List<AllRankingBean.BaseBean> epub = epubList;
                      return epub;
                      default:
                          return null;
              }
      }
      return null;
    }


    private BaseRankFragment maleRankFrg;
    private BaseRankFragment femaleRankFrg;
    private BaseRankFragment pictureRankFrg;
    private BaseRankFragment epubRankFrg;

    //切换碎片
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.rank_fragment_layout,fragment);
        transaction.commit();
    }
}
