package com.arifcebe.favoritmovie.base;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by arifcebe
 * on Jan 1/11/17 14:52.
 * Project : FavoritMovie
 * <p>
 * Lebih Baik Pulang Nama, Daripada Gagal di Medan Laga
 *
 * adalah class untuk menampilkan progress loading dialog
 */
public class AppProgressDialog {
    private static AppProgressDialog ourInstance;
    private ProgressDialog progressDialog;

    public static AppProgressDialog getInstance(Context context) {
        if(ourInstance == null)
            ourInstance = new AppProgressDialog(context);
        return ourInstance;
    }

    /**
     * constructor
     * @param context   Context -> app context
     */
    private AppProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
    }

    /**
     * menampilkan progress dialog beserta pesannya
     * @param message   pesan
     */
    public void showDialog(String message) {
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    /**
     * tutup dialog
     */
    public void dissmissDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
