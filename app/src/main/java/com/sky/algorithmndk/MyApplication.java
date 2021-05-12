package com.sky.algorithmndk;

import android.app.Application;
import android.content.Context;
import android.os.Debug;
import android.util.Log;

import com.didichuxing.doraemonkit.DoraemonKit;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.util.NIMUtil;

/**
 * @author: xuzhiyong
 * @date: 21-2-19  上午11:55
 * @Email: 18971269648@163.com
 * @description:
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        Debug.startMethodTracing("test-nkk-log");
        DoraemonKit.install(this,"fa75146e7622a7fbffbb84371b08053e");
        long cur = System.currentTimeMillis();
        Log.d("MyApplication","App onCreate = "+cur);


        NIMClient.init(this, loginInfo(), options());

        // ... your codes

        // 使用 `NIMUtil` 类可以进行主进程判断。
        // boolean mainProcess = NIMUtil.isMainProcess(context)
        if (NIMUtil.isMainProcess(this)) {
            // 注意：以下操作必须在主进程中进行
            // 1、UI相关初始化操作
            // 2、相关Service调用
//            slowInfo();

        }
        Log.d("MyApplication","App time = "+(System.currentTimeMillis() - cur));
    }

    private void slowInfo() {
        long start = System.currentTimeMillis();
//        for (long i = 0; i < 100000; i++) {
//            Log.d("MyApplication","日志");
//        }

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long cur = System.currentTimeMillis();
        Log.d("MyApplication","slowInfo="+(cur - start) + "  cur="+cur);
    }

    // 如果提供，将同时进行自动登录。如果当前还没有登录用户，请传入null。详见自动登录章节。
    private LoginInfo loginInfo() {
        return null;
    }

    // 设置初始化配置参数，如果返回值为 null，则全部使用默认参数。
    private SDKOptions options() {
        SDKOptions options = new SDKOptions();

        // 配置是否需要预下载附件缩略图，默认为 true
        options.preloadAttach = true;
        options.appKey = "xxx";


        return options;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }
}
