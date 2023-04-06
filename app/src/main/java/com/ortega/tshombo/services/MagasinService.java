package com.ortega.tshombo.services;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.ortega.tshombo.models.MagasinModel;
import com.ortega.tshombo.utils.FIrebaseUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MagasinService implements Services<MagasinModel> {

    private final String table = "magasin";
    private final DatabaseReference databaseReference = FIrebaseUtil.getInstance();

    @Override
    public void save(MagasinModel data) {
        databaseReference.child(this.table).push().setValue(data);
    }


    @Override
    public void update(MagasinModel data, String id) {

        Map<String, Object> updateData = new HashMap<>();
        updateData.put(id, data);


        databaseReference.child(this.table).updateChildren(updateData);
    }

    @Override
    public void delete(String id) {
        databaseReference.child(this.table).child(id).removeValue();
    }



}
