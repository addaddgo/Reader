package com.example.hp.readingyouself;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.hp.readingyouself.Fragment.BookShelfFragment;
import com.example.hp.readingyouself.Fragment.CategoryFragment;
import com.example.hp.readingyouself.Fragment.RankingFragment;
import com.example.hp.readingyouself.ReadingDataSupport.DataConnector;
import com.example.hp.readingyouself.ReadingDataSupport.DataForm.CategoryBean;
import com.example.hp.readingyouself.ReadingDataSupport.DataForm.Rank;
import com.example.hp.readingyouself.ReadingDataSupport.DataGiveService;
import com.example.hp.readingyouself.ReadingDataSupport.ListInCategoryActivity;

public class MainActivity extends AppCompatActivity implements RankingFragment.OnMaiActivityInteractionListener,
        BookShelfFragment.OnMaiActivityInteractionListener,
        CategoryFragment.OnMaiActivityInteractionListener{


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(bookShelfFragment);
                    return true;
                case R.id.navigation_dashboard:
                    replaceFragment(categoryFragment);
                    return true;
                case R.id.navigation_notifications:
                    replaceFragment(rankingFragment);
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
        categoryFragment = new CategoryFragment();
        rankingFragment = new RankingFragment();

        replaceFragment(bookShelfFragment);

        handler = new MyHandler(getMainLooper());

        Intent intent = new Intent(this,DataGiveService.class);
        startService(intent);
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
    private FragmentTransaction transaction;
    private BookShelfFragment bookShelfFragment;
    private CategoryFragment categoryFragment;
    private RankingFragment rankingFragment;

    //切换碎片
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
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

     class MyHandler extends Handler{
         MyHandler(Looper looper) {
             super(looper);
         }
         @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case BookShelfFragment:break;
                case RankingFragment:
                    rankingFragment.reSetRank((Rank) msg.obj);
                    break;
                case CategoryFragment:
                    categoryFragment.resetCategory((CategoryBean)msg.obj);
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

    //RankingFragment

    @Override
    public void getRank(int rankType) {
        if(dataConnector != null){
            dataConnector.sentRankToMainThread(rankType);
        }
    }

    @Override
    public void getRankBySearch(String string) {
        dataConnector.senRankingBySearch(string);
    }

    //BookShelfFragment


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    //CategoryFragment


    @Override
    public void getAllCategory() {
        dataConnector.sendCategory();
    }
}
