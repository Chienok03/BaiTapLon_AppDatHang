package com.example.baitaplon_appdathang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class dsProduct extends AppCompatActivity {
   RecyclerView rcvSp;
    TextView textViewname ,textViewprice;
    Toolbar toolbardsSp;
    private  ProductAdapter1 adapter;
    private SearchView searchView;
    int counter = 0;
    ImageView qltc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_product);
        //Toolbar
        toolbardsSp = findViewById(R.id.toolbarDsSp);
        setSupportActionBar(toolbardsSp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ProductDatabaseHelper dbHelper = new ProductDatabaseHelper(dsProduct.this);
        //tao list doi tuong
        List<Product> productList = new ArrayList<>();
        //add tung doi tuong
        productList.add(new Product( 1, "Bún than",0,0,121));
        productList.add(new Product( 2 , "Đùi gà",0,0,121));
        productList.add(new Product( 3 , "Bún đậu mắn tôm",0,0,120));
        productList.add(new Product( 4 , "Pizza",0,0,121));
        productList.add(new Product( 5 , "Sườn sào chua ngọt",0,0,121));
        productList.add(new Product( 6 , "Xiên bẩn",0,0,120));
        productList.add(new Product( 7 , "Chân gà xả tắc",0,0,121));
        productList.add(new Product( 8 , "Nem cuốn",0,0,120));
        productList.add(new Product( 9 , "Mực sào xả ớt",0,0,121));

        for( Product product:productList){
            dbHelper.addProduct(product);
        }
        // anh xa
        rcvSp = findViewById(R.id.recyclerViewDSsp);
        //tạo thanh gạch dưới mỗi item
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this , DividerItemDecoration.VERTICAL);
        //Hien Thi
        adapter = new ProductAdapter1(this,dbHelper.getAllProducts());
        rcvSp.setAdapter(adapter);
        rcvSp.addItemDecoration(itemDecoration);
        rcvSp.setLayoutManager(new LinearLayoutManager(this));

    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    //Tìm kiếm
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}