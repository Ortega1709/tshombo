package com.ortega.tshombo;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class EventHandlerActivity extends AppCompatActivity {
    TextView tvNormalClick, tvLongClick;
    MaterialButton btnNormalClick, btnLongClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_handler);

        initComponent();

        // normal click
        btnNormalClick.setOnClickListener(v -> { writeTvNormalClick(); });
        // long click
        btnLongClick.setOnLongClickListener(v -> { writeTvLongClick(); return false; });
    }

    void initComponent() {
        tvNormalClick = findViewById(R.id.tvNormalClick);
        tvLongClick = findViewById(R.id.tvLongClick);

        btnNormalClick = findViewById(R.id.normalClick);
        btnLongClick = findViewById(R.id.longClick);
    }

    void writeTvNormalClick() { tvNormalClick.setText(R.string.custom_message); }
    void writeTvLongClick() { tvLongClick.setText(R.string.custom_message); }
}