package com.ortega.tshombo.services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.ortega.tshombo.models.UserModel;
import com.ortega.tshombo.utils.LocalDataUtil;

public class LocalDataService {

    private SharedPreferences localData;
    private Context context;

    public LocalDataService(Context context) {
        this.context = context;
        this.localData = new LocalDataUtil(context).getLocalData();
    }

    public void insert(UserModel userModel) {

        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = localData.edit();
        editor.putString(LocalDataUtil._UUID, userModel.getId());
        editor.putString(LocalDataUtil._USERNAME, userModel.getId());
        editor.putString(LocalDataUtil._EMAIL, userModel.getId());

        editor.apply();
        System.out.println("[+] New record ...");
    }

    public void delete() {
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = localData.edit();
        editor.clear();
        editor.apply();
    }

    public void show() {

        System.out.println("Nom" + localData.getString(LocalDataUtil._UUID, null));

    }

}
