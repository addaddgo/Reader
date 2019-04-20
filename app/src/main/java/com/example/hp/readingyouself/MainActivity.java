package com.example.hp.readingyouself;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.HttpAuthHandler;

import com.example.hp.readingyouself.mainFragment.BookShelfFragment;
import com.example.hp.readingyouself.mainFragment.CategoryFragment;
import com.example.hp.readingyouself.mainFragment.CommunityFragment;
import com.example.hp.readingyouself.mainFragment.FindFragment;
import com.example.hp.readingyouself.mainFragment.RankingFragment;
import com.example.hp.readingyouself.mainFragment.SearchFragment;
import com.example.hp.readingyouself.readActivity.ReadingViewActivity;
import com.example.hp.readingyouself.readingDataSupport.DataConnector;
import com.example.hp.readingyouself.readingDataSupport.DataGiveService;
import com.example.hp.readingyouself.readingDataSupport.ListInCategoryActivity;
import com.example.hp.readingyouself.readingDataSupport.dataForm.SearchBookBean;
import com.example.hp.readingyouself.readingDataSupport.dataForm.SearchLog;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchFragment.SearchFragmentInter,
        BookShelfFragment.OnMaiActivityInteractionListener,
        CategoryFragment.OnMaiActivityInteractionListener{


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(bookShelfFragment);
                    currentFragment = BOOK_SHELF_FRAGMENT;
                    return true;
                case R.id.navigation_dashboard:
                    currentFragment = CATEGORY_FRAGMENT;
                    replaceFragment(communityFragment);
                    return true;
                case R.id.navigation_notifications:
                    currentFragment = RANKING_FRAGMENT;
                    replaceFragment(findFragment);
                    return true;
                case R.id.navigation_search:
                    currentFragment = SEARCH_FRAGMENT;
                    replaceFragment(searchFragment);
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //创建碎片
        bookShelfFragment = new BookShelfFragment();
        communityFragment = new CommunityFragment();
        findFragment = new FindFragment();
        searchFragment = new SearchFragment();

        replaceFragment(bookShelfFragment);

        handler = new MyHandler(getMainLooper());

        Intent intent = new Intent(this,DataGiveService.class);
        startService(intent);
//        Intent intent1 = new Intent(this,ReadingViewActivity.class);
//        intent1.putExtra(ReadingViewActivity.CHAPTER_BODY_URL,"http://chapter2.zhuishushenqi.com/chapter/http:%2F%2Fbook.my716.com%2FgetBooks.aspx%3Fmethod=content&bookId=633074&chapterFile=U_753547_201607012243065574_6770_1.txt");
//        intent1.putExtra(ReadingViewActivity.BOOK_ID,"5569ba444127a49f1fa99d29");
//        intent1.putExtra(ReadingViewActivity.CHAPTER_NAME,"第一个章");
//        startActivity(intent1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this,DataGiveService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this,DataGiveService.class);
        stopService(intent);
    }

    //碎片控制
    private BookShelfFragment bookShelfFragment;
    private CommunityFragment communityFragment;
    private FindFragment findFragment;
    private SearchFragment searchFragment;

    //切换碎片
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_layout,fragment);
        transaction.commit();
    }

    //数据控制
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DataGiveService.DataBinder dataBinder = (DataGiveService.DataBinder)service;
            dataConnector = dataBinder.getDataConnector();
            dataConnector.setWorkHandler(handler);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            dataConnector.setWorkHandler(null);
            dataConnector = null;
        }
    };

    private DataConnector dataConnector;
    private MyHandler handler;
    private int currentFragment;


     class MyHandler extends Handler{
         MyHandler(Looper looper) {
             super(looper);
         }
         @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (currentFragment){
                case BOOK_SHELF_FRAGMENT:
                    break;
                case SEARCH_FRAGMENT:

                    break;

            }
        }
    }

    //MaiActivityInterface

    @Override
    public void startReadActivity(String bookID) {
        Intent intent = new Intent(this,ChapterActivity.class);
        intent.putExtra(ChapterActivity.BOOK_ID,bookID);
        startActivity(intent);
    }

    @Override
    public void startListInCategoryActivity(String major, String mins) {
        Intent intent = new Intent(this,ListInCategoryActivity.class);
        intent.putExtra(ListInCategoryActivity.BOOK_CATEGORY_MAJOR,major);
        intent.putExtra(ListInCategoryActivity.BOOK_CATEGORY_MINOR,mins);
        startActivity(intent);
    }

    @Override
    public List<SearchLog> giveSearchLog() {
         return dataConnector.getSearchLog();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    //CategoryFragment


    @Override
    public void getAllCategory() {
        dataConnector.sentCategory();
    }
}
