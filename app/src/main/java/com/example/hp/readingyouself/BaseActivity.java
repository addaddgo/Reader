package com.example.hp.readingyouself;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.hp.readingyouself.readingDataSupport.DataConnector;
import com.example.hp.readingyouself.readingDataSupport.DataGiveService;

public abstract class BaseActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforeBindServiceOnCreated();
        handler = new MyHandler(getMainLooper());
        Intent intent = new Intent(this,DataGiveService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }


    private DataConnector dataConnector;
    private MyHandler handler;

    public final DataConnector getDataConnector() {
        return dataConnector;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this,DataGiveService.class);
        unbindService(serviceConnection);
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DataGiveService.DataBinder dataBinder = (DataGiveService.DataBinder)service;
            dataConnector = dataBinder.getDataConnector();
            dataConnector.setWorkHandler(handler);
            onDataServiceConnect();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            dataConnector = null;
            dataConnector.setWorkHandler(null);
            onDataServiceDisconnect();
        }
    };

    class MyHandler extends Handler {

        MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            onHandleMessage(msg);
        }
    }

    protected void initBeforeBindServiceOnCreated(){

    }

    protected abstract void onHandleMessage(Message msg);
    protected abstract void onDataServiceConnect();
    protected abstract void onDataServiceDisconnect();
}
