package com.ortega.tshombo.services;

import com.google.firebase.database.DatabaseReference;
import com.ortega.tshombo.models.UserModel;
import com.ortega.tshombo.utils.FIrebaseUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserService implements Services<UserModel> {

    private final String table = "user";
    private final DatabaseReference databaseReference = FIrebaseUtil.getInstance();

    @Override
    public void save(UserModel data) {
        databaseReference.child(this.table).push().setValue(data);
    }

    @Override
    public void update(UserModel data, String id) {

        Map<String, Object> updateData = new HashMap<>();
        updateData.put(id, data);


        databaseReference.child(this.table).updateChildren(updateData);
    }

    @Override
    public void delete(String id) {
        databaseReference.child(this.table).child(id).removeValue();
    }


}
