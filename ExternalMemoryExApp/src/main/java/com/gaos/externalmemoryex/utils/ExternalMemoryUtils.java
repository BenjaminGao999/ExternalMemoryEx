package com.gaos.externalmemoryex.utils;

import android.os.Environment;

/**
 * Author:　Created by benjamin
 * DATE :  2017/4/20 10:14
 * versionCode:　1.0.0
 */

public class ExternalMemoryUtils {

    /*Checks if external storage is available for read and write*/
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
