package com.ortega.tshombo;

import static com.ortega.tshombo.draw.colors;
import static com.ortega.tshombo.draw.current_brush;
import static com.ortega.tshombo.draw.paths;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class DrawActivity extends AppCompatActivity {

    MaterialToolbar toolbar; ExtendedFloatingActionButton btnDraw;
    public static Path path = new Path();
    public static Paint paint = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        initComponent();
        toolbar.setNavigationOnClickListener(v -> eraser());
        btnDraw.setOnClickListener(v -> pencil());

    }

    void initComponent() {
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        btnDraw = findViewById(R.id.btnDraw);
    }

    void eraser() {
        paths.clear();
        colors.clear();
        path.reset();
    }

    void pencil() { paint.setColor(Color.BLACK); currentColor(paint.getColor()); }

    void currentColor(int color) { current_brush = color; path = new Path(); }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.colorBlue:
                currentColor(Color.BLUE);
                return true;
            case R.id.colorJaune:
                currentColor(Color.YELLOW);
                return true;
            case R.id.colorRouge:
                currentColor(Color.RED);
                return true;
            case R.id.colorVert:
                currentColor(Color.GREEN)   ;
                return true;
        }
        return true;
    }
}
