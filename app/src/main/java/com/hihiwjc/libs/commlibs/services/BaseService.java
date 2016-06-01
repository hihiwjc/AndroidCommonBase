package com.hihiwjc.libs.commlibs.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * 基本服务</b>
 * <p>Created by hihiwjc on 2015/9/18 0016.</p>
 * <p>Author:hihiwjc</p>
 * <p>Email:hihiwjc@live.com</p>
 */
public class BaseService extends Service {
    private Binder mBinder;

    public BaseService() {
        mBinder = new BaseBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class BaseBinder extends Binder implements BaseServiceInterface {

    }

    public interface BaseServiceInterface {

    }
}
