package com.example.baitaplon_appdathang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class ActivityXacNhan extends AppCompatActivity {
    Button velaitchu;
    private ProductAdapter adapter;
    private TextView TongTien;
    Toolbar toolbarXnTT;
    private TextView ten,gmail,sdt ,snttien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan);
        velaitchu = findViewById(R.id.velaitrangchu);

        toolbarXnTT = findViewById(R.id.toolbarXnTT);
        setSupportActionBar(toolbarXnTT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ten = findViewById(R.id.tenkh);
        gmail =findViewById(R.id.gmailkh);
        sdt =findViewById(R.id.sdtkh);

        Intent intent = getIntent();
        String tenkh =  intent.getStringExtra("Ten");
        String gmailkh=  intent.getStringExtra("Gmail");
        String sdtkh = intent.getStringExtra("Sdt");

        ten.setText(tenkh);
        gmail.setText(gmailkh);
        sdt.setText(sdtkh);

        velaitchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityXacNhan.this , ActivityIndex.class);
                startActivity(intent);
            }
        });
        //Thành tiền
        TongTien = findViewById(R.id.xntongtien);
        TongTien.setText(intent.getStringExtra("TongTienThanhToan"));
    }
    public boolean onSupportNavigateUp() {
        // Xử lý sự kiện khi người dùng click vào nút back trên toolbar
        onBackPressed();
        return true;
    }
}