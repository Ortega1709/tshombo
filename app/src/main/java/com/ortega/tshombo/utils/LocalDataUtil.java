package com.ortega.tshombo.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalDataUtil {

    public static String LOCAL = "LOCAL_DATA";

    // keys
    public static String _UUID = "uuid";
    public static String _USERNAME = "username";
    public static String _EMAIL = "email";

    // context
    private Context context;

    public LocalDataUtil(Context context) {
        this.context = context;
    }

    public SharedPreferences getLocalData() {
        return context.getSharedPreferences(LOCAL, MODE_PRIVATE);
    }

}
