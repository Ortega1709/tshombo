package com.ortega.tshombo.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FIrebaseUtil {

    public static DatabaseReference getInstance() {
        System.out.println("[i] instance: " + FirebaseDatabase.getInstance().getReference());
        return FirebaseDatabase.getInstance().getReference();
    }

}
