package com.ortega.tshombo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ortega.tshombo.models.UserModel;

public class LoginActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    MaterialButton authBtn;

    DatabaseReference databaseReference;
    ProgressBar progressBar;
    TextInputEditText emailText, passwdText;

    SharedPreferences pref; // 0 - for private mode

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();

        linearLayout.setOnClickListener(
                v -> {

                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);

                });

        authBtn.setOnClickListener(
                v -> authentication());


        pref = getApplicationContext().getSharedPreferences("userData", 0);

    }

    protected void initialize() {

        linearLayout = findViewById(R.id.register_text);
        authBtn = findViewById(R.id.auth_btn);

        emailText = findViewById(R.id.input_email);
        emailText.setTextColor(Color.WHITE);
        passwdText = findViewById(R.id.input_passwd);
        passwdText.setTextColor(Color.WHITE);

        progressBar = findViewById(R.id.progress);
    }

    protected void authentication() {


        progressBar.setVisibility(View.VISIBLE);
        authBtn.setCheckable(false);

        new Handler().postDelayed(() -> {

            String email = String.valueOf(emailText.getText());
            String passwd = String.valueOf(passwdText.getText());


            if (!email.equals("") && !passwd.equals("")) {

                //progressBar.setVisibility(View.GONE);
                //authBtn.setCheckable(true);

                databaseReference = FirebaseDatabase.getInstance().getReference("user");
                databaseReference.addValueEventListener(new ValueEventListener() {

                    Boolean is_logged = false;

                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot itemSnapshot: snapshot.getChildren()) {

                            UserModel userModel = itemSnapshot.getValue(UserModel.class);
                            assert userModel != null;
                            userModel.setId(itemSnapshot.getKey());

                            if (userModel.getEmail().equalsIgnoreCase(email) && userModel.getPassword().equals(passwd)) {

                                progressBar.setVisibility(View.GONE);
                                authBtn.setCheckable(true);

                                is_logged = true;
                                //Intent intent = new Intent(this, MainActivity.class);

                                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                //startActivity(intent);
                                getIs_logged(userModel);
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }

                    public void getIs_logged(UserModel userModel) {

                        if (is_logged) {
                            progressBar.setVisibility(View.GONE);
                            authBtn.setCheckable(true);

                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("username", userModel.getUsername());
                            editor.putString("uuid", userModel.getId());
                            editor.putString("email", userModel.getEmail());
                            editor.putString("passwd", userModel.getPassword());

                            editor.apply();
                            System.out.println("UUID" + pref.getString("uuid", null));
                        }

                    }
                });

                if (pref.getString("uuid", null) == null) {
                    progressBar.setVisibility(View.GONE);
                    authBtn.setCheckable(true);
                    Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(this, MainActivity.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }


            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(this, R.string.all_fields_required, Toast.LENGTH_SHORT).show();
            }

        }, 2 * 1000);

    }

}