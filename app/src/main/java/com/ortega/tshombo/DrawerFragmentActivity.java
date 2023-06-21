package com.ortega.tshombo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.ortega.tshombo.screens.FirstFragment;
import com.ortega.tshombo.screens.HomeFragment;
import com.ortega.tshombo.screens.MyStoreFragment;
import com.ortega.tshombo.screens.SearchFragment;
import com.ortega.tshombo.screens.SecondFragment;
import com.ortega.tshombo.screens.StoreFragment;
public class DrawerFragmentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_fragment);

        initComponent();
    }
    void initComponent() {
        // setup tool bar
        materialToolbar = findViewById(R.id.toolBar);
        materialToolbar.setTitle("Drawer Fragment");
        setSupportActionBar(materialToolbar);

        // setup navigation view
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // config nav view and toolbar
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawerLayout, materialToolbar,
                        R.string.open_nav, R.string.close_nav);

        // sync drawer
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.frist:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new FirstFragment()).commit();

                break;

            case R.id.second:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new SecondFragment()).commit();

                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}