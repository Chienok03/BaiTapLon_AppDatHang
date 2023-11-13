package com.example.baitaplon_appdathang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    private List<Product> productList;
    private ProductAdapter adapter;
    TextView Tongtien;
    Button thanhtoan, qltrangdssp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        //Tạo list
        productList = new ArrayList<>();

        //Lây dssp từ database
        ProductDatabaseHelper dbHelper = new ProductDatabaseHelper(this);
        productList = dbHelper.getProductsFromRow9Onwards();
        //productList = dbHelper.getAllNewProducts();
        if (productList.isEmpty()) {
            Toast.makeText(ProductListActivity.this, "Danh sach trống", Toast.LENGTH_SHORT).show();
        } else {
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            adapter = new ProductAdapter(productList);
            dbHelper.deleteAllProducts();
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        //Thành Tiền
        Tongtien = findViewById(R.id.GiaThanhTien);
        String sum = adapter.getTotalSum();
        Tongtien.setText(sum);

        //Thanh Toan
        thanhtoan = findViewById(R.id.thanhtoantien);
        thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductListActivity.this, ActivityImformation.class);
                intent.putExtra("tongTien", sum);
                startActivity(intent);
            }
        });

        //Chọn Tiếp SP
        qltrangdssp = findViewById(R.id.qlaidssp);
        qltrangdssp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductListActivity.this, dsProduct.class);
                startActivity(intent);
            }
        });

    }
    // xóa sp
    public void onDeleteButtonClick(View view)
    {
        int position = ((RecyclerView.ViewHolder) view.getTag()).getAdapterPosition();
        productList.remove(position);
        adapter.notifyItemRemoved(position);
        Toast.makeText(this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
    }

}
