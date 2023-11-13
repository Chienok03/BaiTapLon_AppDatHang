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
import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText repassword;
    TextView  signin;
    Button signup;
    ProductDatabaseHelper dbHelper = new ProductDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.pass);
        signup = findViewById(R.id.signup);
        signin = findViewById(R.id.signin);
        repassword = findViewById(R.id.repass);
        dbHelper = new ProductDatabaseHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(MainActivity.this, "Warning", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = dbHelper.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = dbHelper.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(MainActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), ActivityIndex.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "người dùng đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                    }
                }
            }

//            int strength = PasswordStrengthHelper.checkPasswordStrength();
//    switch (strength) {
//                case PasswordStrengthHelper.WEAK:
//                    // Hiển thị thông báo yêu cầu người dùng nhập mật khẩu mạnh hơn
//                    break;
//                case PasswordStrengthHelper.MEDIUM:
//                    // Hiển thị thông báo về mức độ mật khẩu trung bình
//                    break;
//                case PasswordStrengthHelper.STRONG:
//                    // Tiếp tục quy trình đăng ký
//                    break;
//            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

//    public static class PasswordStrengthHelper()
//    {
//        public static final int WEAK = 0;
//        public static final int MEDIUM = 1;
//        public static final int STRONG = 2;
//
//        public static int checkPasswordStrength(String password) {
//            // Kiểm tra độ dài của mật khẩu
//            if (password.length() < 8) {
//                return WEAK;
//            }
//
//            // Kiểm tra các yêu cầu về mật khẩu
//            boolean hasDigit = false;
//            boolean hasLowerCase = false;
//            boolean hasUpperCase = false;
//            boolean hasSpecialChar = false;
//
//            for (int i = 0; i < password.length(); i++) {
//                char ch = password.charAt(i);
//
//                if (Character.isDigit(ch)) {
//                    hasDigit = true;
//                } else if (Character.isLowerCase(ch)) {
//                    hasLowerCase = true;
//                } else if (Character.isUpperCase(ch)) {
//                    hasUpperCase = true;
//                } else if (!Character.isLetterOrDigit(ch)) {
//                    hasSpecialChar = true;
//                }
//            }
//
//            // Xác định mức độ mạnh yếu của mật khẩu
//            if (hasDigit && hasLowerCase && hasUpperCase && hasSpecialChar) {
//                return STRONG;
//            } else if ((hasDigit && hasLowerCase) || (hasDigit && hasUpperCase) || (hasLowerCase && hasUpperCase)) {
//                return MEDIUM;
//            } else {
//                return WEAK;
//            }
//        }
//    }

    // Phương thức để kiểm tra mật khẩu theo yêu cầu
//    private boolean isValidPassword(String password) {
//        // Sử dụng biểu thức chính quy
//        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(password);
//
//        return matcher.matches();
//    }
}