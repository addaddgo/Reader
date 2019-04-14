package com.example.hp.readingyouself.ReadingDataSupport;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class DataGiveService extends Service {


    //数据控制
    private DataConnector dataConnector;

    public DataGiveService(){
        dataConnector = new DataConnector();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return new DataBinder();
    }

    public class DataBinder extends Binder {

        public DataConnector getDataConnector(){
            return dataConnector;
        }

    }

}
