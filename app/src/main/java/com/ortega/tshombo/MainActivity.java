package com.ortega.tshombo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.ortega.tshombo.models.UserModel;
import com.ortega.tshombo.screens.HomeFragment;
import com.ortega.tshombo.screens.MyStoreFragment;
import com.ortega.tshombo.screens.SearchFragment;
import com.ortega.tshombo.screens.StoreFragment;
import com.ortega.tshombo.services.LocalDataService;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;

    SharedPreferences pref;

    TextView emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LocalDataService localDataService = new LocalDataService(this);
        //localDataService.show();

        // disable night theme
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
 
        materialToolbar = findViewById(R.id.toolBar);
        materialToolbar.setTitle(R.string.home);
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

        // config fragment container
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment()).commit();

            materialToolbar.setTitle(R.string.home);

            navigationView.setCheckedItem(R.id.nav_home);
        }

        pref = getApplicationContext().getSharedPreferences("userData", 0);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.username);
        TextView navEmail = (TextView) headerView.findViewById(R.id.email);

        navUsername.setText(pref.getString("username", null));
        navEmail.setText(pref.getString("email", null));

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment()).commit();

                materialToolbar.setTitle(R.string.home);
                break;

            case R.id.my_store:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new MyStoreFragment()).commit();

                materialToolbar.setTitle(R.string.my_store);
                break;

            case R.id.nav_share:

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "link to playstore app";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

                break;

            case R.id.nav_logout:

                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(this, LoginActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
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