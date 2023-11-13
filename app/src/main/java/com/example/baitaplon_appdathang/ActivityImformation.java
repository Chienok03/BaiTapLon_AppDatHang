package com.example.baitaplon_appdathang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ActivityImformation extends AppCompatActivity {

    Button thanhtoansp ;

    Toolbar toolbarTT;
    TextView tongtien;
    private EditText name ,gmail,sdt;
    ImageView qlgiohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imformation);
        //Toolbar
        toolbarTT = findViewById(R.id.toolbarTT);
        setSupportActionBar(toolbarTT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Ánh xạ
        thanhtoansp = findViewById(R.id.ThanhToan);
        name = findViewById(R.id.nameproduct);
        gmail = findViewById(R.id.gmailprodcut);
        sdt = findViewById(R.id.sdtproduct);

        //Thành Tiền
        Intent intent = getIntent();
        tongtien = findViewById(R.id.price);
        String sum =intent.getStringExtra("tongTien");
        tongtien.setText(sum);

        thanhtoansp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().isEmpty() && !gmail.getText().toString().isEmpty() &&  !sdt.getText().toString().isEmpty()) {
                    Intent intent = new Intent(ActivityImformation.this, ActivityXacNhan.class);
                    intent.putExtra("Ten", name.getText().toString());
                    intent.putExtra("Gmail", gmail.getText().toString());
                    intent.putExtra("Sdt", sdt.getText().toString());
                    intent.putExtra("TongTienThanhToan", sum);
                    startActivity(intent);
                } else {
                    Toast.makeText(ActivityImformation.this, "Mời bạn thêm thông tin", Toast.LENGTH_SHORT).show();
                }
//                name.setText("");
//                gmail.setText("");
//                sdt.setText("");
            }
        });

    }
    public boolean onSupportNavigateUp() {
        // Xử lý sự kiện khi người dùng click vào nút back trên toolbar
        onBackPressed();
        return true;
    }

}