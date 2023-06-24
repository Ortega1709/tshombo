package com.ortega.tshombo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.ortega.tshombo.screens.FirstFragment;
import com.ortega.tshombo.screens.SecondFragment;

public class FragmentActivity extends AppCompatActivity {

    MaterialButton btnClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        // config fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new FirstFragment()).commit();
        }

    }
}
