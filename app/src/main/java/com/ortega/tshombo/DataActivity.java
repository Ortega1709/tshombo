package com.ortega.tshombo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ortega.tshombo.adapters.DataAdapter;
import com.ortega.tshombo.models.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    // list of data
    ArrayList<DataModel> dataModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        // init recyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewData);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);



        // get JSON data
        try {

            JSONObject obj = new JSONObject(loadJsonData());

            JSONArray dataArray = obj.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {

                ArrayList<JSONArray> classes = new ArrayList<>();

                JSONObject details = dataArray.getJSONObject(i);
                JSONArray jsonArray = details.getJSONArray("classes");

                DataModel dataModel = new DataModel(details.getString("room"),details.getString("subject"), details.getString("hoursMask"), details.getString("start"), details.getString("end"), jsonArray.toString());

                dataModels.add(dataModel);
            }

            DataAdapter dataAdapter = new DataAdapter(DataActivity.this, dataModels);
            recyclerView.setAdapter(dataAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public String loadJsonData() {
        String json = null;
        try {
            InputStream is = getAssets().open("json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}