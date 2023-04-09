package com.ortega.tshombo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ortega.tshombo.R;

public class LoginActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    MaterialButton authBtn;

    ProgressBar progressBar;
    TextInputEditText emailText, passwdText;

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
                v -> auth());

    }

    protected void initialize() {

        linearLayout = findViewById(R.id.register_text);
        authBtn = findViewById(R.id.auth_btn);

        emailText = findViewById(R.id.input_email);
        passwdText = findViewById(R.id.input_passwd);

        progressBar = findViewById(R.id.progress);
    }

    protected void auth() {

        progressBar.setVisibility(View.VISIBLE);
        authBtn.setCheckable(false);

        new Handler().postDelayed(() -> {

            String email = String.valueOf(emailText.getText());
            String passwd = String.valueOf(passwdText.getText());


            if (!email.equals("") && !passwd.equals("")) {

                progressBar.setVisibility(View.GONE);
                authBtn.setCheckable(true);

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("user_email", email);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


            } else Toast.makeText(this, R.string.all_fields_required, Toast.LENGTH_SHORT).show();

        }, 5 * 1000);

    }
}