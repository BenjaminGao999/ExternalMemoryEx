package com.gaos.externalmemoryex;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.gaos.externalmemoryex.utils.ExternalMemoryUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

/**
 * Author:　Created by benjamin
 * DATE :  2017/4/20 10:01
 * versionCode:　1.0.0
 */

public class EMFrameLayout extends FrameLayout implements View.OnClickListener {
    private static final String TAG = EMFrameLayout.class.getSimpleName();

    public EMFrameLayout(Context context) {
        super(context);
        setContentView();
        initView();
    }


    private void setContentView() {
        View.inflate(getContext(), R.layout.layout_em, this);
    }

    private void initView() {
        Button btnEm = (Button) findViewById(R.id.btn_em);
        btnEm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_em:

                try {
                    externalStorageInfo();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            default:
                break;
        }

    }

    private void externalStorageInfo() throws IOException {
//        File externalFilesDir = getContext().getExternalFilesDir(null);
//        long totalSpace = externalFilesDir.getTotalSpace();

        if (ExternalMemoryUtils.isExternalStorageWritable()) {

            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            long totalSpace = externalStorageDirectory.getTotalSpace();
            Log.i(TAG, "externalStorageInfo: external total space = " + Formatter.formatFileSize(getContext(), totalSpace));
            Log.i(TAG, "externalStorageInfo: external storage path =" + externalStorageDirectory.getAbsolutePath());
//            File file = new File(externalStorageDirectory, "com.gaos.ex");
//            file.createNewFile();
//            file.delete();
//            file.mkdir();
//            Log.i(TAG, "externalStorageInfo: file path " + file.getAbsolutePath() + " ; file is exist = " + file.exists());
        }

//        File dataDirectory = Environment.getDataDirectory();
//        Log.i(TAG, "externalStorageInfo: data directory =" + dataDirectory.getAbsolutePath());
//        long dataDirectoryTotalSpace = dataDirectory.getTotalSpace();
//        Log.i(TAG, "externalStorageInfo: data directory total space = " + Formatter.formatFileSize(getContext(), dataDirectoryTotalSpace));
//        long freeSpace = dataDirectory.getFreeSpace();
//        Log.i(TAG, "externalStorageInfo: freeSpce = " + Formatter.formatFileSize(getContext(), freeSpace));
//        long usableSpace = dataDirectory.getUsableSpace();
//        Log.w(TAG, "externalStorageInfo: usableSpace = " + Formatter.formatFileSize(getContext(), usableSpace));

//        File rootDirectory = Environment.getRootDirectory();
//        Log.d(TAG, "externalStorageInfo: root dir path = " + rootDirectory.getAbsolutePath());


//        File dir = getContext().getDir(null, Context.MODE_PRIVATE);
//        File parentFile = dir.getParentFile();
//        Log.i(TAG, "externalStorageInfo: dir = " + parentFile.getAbsolutePath());
//
//        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
//        ActivityManager activityManager = (ActivityManager) getContext().getSystemService(Service.ACTIVITY_SERVICE);
//        activityManager.getMemoryInfo(memoryInfo);
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
//            long totalMem = memoryInfo.totalMem;
//            Log.i(TAG, "externalStorageInfo: totalMemory = " + Formatter.formatFileSize(getContext(), totalMem));
//            long availMem = memoryInfo.availMem;
//            Log.i(TAG, "externalStorageInfo: availMem = " + Formatter.formatFileSize(getContext(), availMem));
//        }

        if (ExternalMemoryUtils.isExternalStorageWritable()) {
            File externalFilesDir = getContext().getExternalFilesDir(null);
            Log.i(TAG, "externalStorageInfo: externalFilesDir = " + externalFilesDir.getAbsolutePath());
            File externalCacheDir = getContext().getExternalCacheDir();
            Log.i(TAG, "externalStorageInfo: externalCacheDir = " + externalCacheDir.getAbsolutePath());
        }
    }


    void createExternalStoragePrivateFile() throws Exception {
        // Create a path where we will place our private file on external
        // storage.
        File file = new File(getContext().getExternalFilesDir(null), "DemoFile.jpg");

        try {
            // Very simple code to copy a picture from the application's
            // resource into the external file.  Note that this code does
            // no error checking, and assumes the picture is small (does not
            // try to copy it in chunks).  Note that if external storage is
            // not currently mounted this will silently fail.
            InputStream is =
                    getResources().openRawResource(R.raw.soilder);
            OutputStream os = new FileOutputStream(file);
            byte[] data = new byte[is.available()];
            is.read(data);
            os.write(data);
            is.close();
            os.close();
        } catch (IOException e) {
            // Unable to create file, likely because external storage is
            // not currently mounted.
            Log.w("ExternalStorage", "Error writing " + file, e);
        }
    }

    void deleteExternalStoragePrivateFile() {
        // Get path for the file on external storage.  If external
        // storage is not currently mounted this will fail.
        File file = new File(getContext().getExternalFilesDir(null), "DemoFile.jpg");
        if (file != null) {
            file.delete();
        }
    }

    boolean hasExternalStoragePrivateFile() {
        // Get path for the file on external storage.  If external
        // storage is not currently mounted this will fail.
        File file = new File(getContext().getExternalFilesDir(null), "DemoFile.jpg");
        if (file != null) {
            return file.exists();
        }
        return false;
    }
}
