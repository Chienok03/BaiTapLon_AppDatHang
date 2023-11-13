package com.example.baitaplon_appdathang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ProductActivity extends AppCompatActivity {
    private TextView tenSP,GiaSP  ;
    private EditText soLuongSP;
    String name ;
    Toolbar toolbarSp;
    ImageView imageView;
    String price;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        toolbarSp = findViewById(R.id.toolbarSP);
        setSupportActionBar(toolbarSp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tenSP = findViewById(R.id.productNameTextView);
        GiaSP = findViewById(R.id.productPriceTextView);
        soLuongSP = findViewById(R.id.productNumberEditText);
        imageView = findViewById(R.id.anh1);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }
        Product product = (Product) bundle.get("oject_product");


      //  imageView.setImageResource(product.getImageUrl());
        tenSP.setText(product.getName());
        String format =  String.format("%.3f", product.getPrice());

        GiaSP.setText(format);

        name = tenSP.getText().toString();
        price = GiaSP.getText().toString();
       // soLuongSP = soLuongSP.getText().toString();



        Button viewProductButton = findViewById(R.id.viewProductButton);
        ProductDatabaseHelper dbHelper = new ProductDatabaseHelper(ProductActivity.this);

        viewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = soLuongSP.getText().toString();
                if (!number.isEmpty()) {
                    Product product_new = new Product();
                    product_new.setId(11);
                    product_new.setName(product.getName());
                    product_new.setPrice(product.getPrice());
                    dbHelper.addProduct(product_new);
                   // int quantity = Integer.parseInt(number);
                    //  product.setQuantity(quantity);
                    // dbHelper.addNewProduct(product);
                    Intent intent = new Intent(ProductActivity.this, ProductListActivity.class);
                    startActivity(intent);
                    Toast.makeText(ProductActivity.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ProductActivity.this, "Nhập số lượng!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public boolean onSupportNavigateUp() {
        // Xử lý sự kiện khi người dùng click vào nút back trên toolbar
        onBackPressed();
        return true;
    }
}
