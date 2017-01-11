package com.arifcebe.favoritmovie.base;

import android.util.Log;

import com.arifcebe.favoritmovie.BuildConfig;

/**
 * Created by arifcebe
 * on Jan 1/11/17 14:28.
 * Project : FavoritMovie
 * <p>
 * Lebih Baik Pulang Nama, Daripada Gagal di Medan Laga
 */
public class AppLog {
    private static AppLog ourInstance = new AppLog();

    public static AppLog getInstance() {
        return ourInstance;
    }

    private AppLog() {
    }

    public void showLog(String tag,String message) {
        if (BuildConfig.DEBUG){
            Log.d(tag,message);
        }
    }
}
