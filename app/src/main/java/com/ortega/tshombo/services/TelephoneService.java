package com.ortega.tshombo.services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.annotations.NotNull;
import com.ortega.tshombo.models.TelephoneModel;
import com.ortega.tshombo.utils.FIrebaseUtil;

import java.util.HashMap;
import java.util.Map;

public class TelephoneService implements Services<TelephoneModel> {

    private final String table = "telephone";
    private final DatabaseReference databaseReference = FIrebaseUtil.getInstance();

    @Override
    public void save(TelephoneModel data) {
        databaseReference.child(this.table).push().setValue(data);
    }

    @Override
    public void get(String id) {
    }

    @Override
    public void update(TelephoneModel data, String id) {

        Map<String, Object> updateData = new HashMap<>();
        updateData.put(id, data);

        databaseReference.child(this.table).updateChildren(updateData);
    }

    @Override
    public void delete(String id) {
        databaseReference.child(this.table).child(id).removeValue();
    }

    @Override
    public void getAll() {

    }
}
