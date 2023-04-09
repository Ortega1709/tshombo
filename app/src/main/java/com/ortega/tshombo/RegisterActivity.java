package com.ortega.tshombo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.ortega.tshombo.R;
import com.ortega.tshombo.models.UserModel;
import com.ortega.tshombo.services.UserService;

public class RegisterActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    MaterialButton createBtn;

    TextInputEditText usernameText, emailText, passwdText;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initilize();

        linearLayout.setOnClickListener(v -> finish());
        createBtn.setOnClickListener(v -> createAccount());

    }

    protected void initilize() {

        linearLayout = findViewById(R.id.login_text);
        createBtn = findViewById(R.id.create_btn);

        usernameText = findViewById(R.id.input_username);
        emailText = findViewById(R.id.input_email);

        passwdText = findViewById(R.id.input_passwd);
        progressBar = findViewById(R.id.progress);

    }

    protected void createAccount() {

        progressBar.setVisibility(View.VISIBLE);
        UserService userService = new UserService();

        String username = String.valueOf(usernameText.getText());
        String email = String.valueOf(emailText.getText());
        String passwd = String.valueOf(passwdText.getText());

        if (!username.equals("") && !email.equals("") && !passwd.equals("")) {

            UserModel userModel = new UserModel(username, email, passwd);
            userService.save(userModel);

            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, R.string.you_have_create_account, Toast.LENGTH_SHORT).show();

            finish();

        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, R.string.all_fields_required, Toast.LENGTH_SHORT).show();
        }




    }
}