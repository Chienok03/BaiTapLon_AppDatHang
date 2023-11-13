package com.example.baitaplon_appdathang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    TextView signin, backtosignup;
    ProductDatabaseHelper dbHelper = new ProductDatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.edtUser1);
        password = findViewById(R.id.edtPass1);
        signin = findViewById(R.id.btnSignin);
        backtosignup = findViewById(R.id.btnBack);
        dbHelper = new ProductDatabaseHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    Toast.makeText(LoginActivity.this, "Warning", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = dbHelper.checkusernamepassword(user,pass);
                    if(checkuserpass == true){
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ActivityIndex.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        backtosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}