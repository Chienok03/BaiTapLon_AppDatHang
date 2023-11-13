package com.example.baitaplon_appdathang;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> productList;
    private  double sum = 0.0;
    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    //Phương thức onCreateViewHolder được sử dụng để tạo một ViewHolder mới cho mỗi sản phẩm
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }
    //Phương thức onBindViewHolder được gọi mỗi khi cần hiển thị dữ liệu cho một sản phẩm
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int sl  = 2;
        Product product = productList.get(position);
        holder.nameTextView.setText(product.getName());
        holder.quantityEditText.setText(String.valueOf(sl));
      //  holder.imgae.setImageResource(R.drawable.pizaa);
        String format =  String.format("%.3f đ", product.getPrice());
        holder.priceTextView.setText(format);
    }

    public String getTotalSum() {
        int sl  = 2;
        for (Product product : productList) {
            sum += product.getPrice() * sl ;
        }
        String formatSum =  String.format("%.3f đ", sum);
        return formatSum;
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public EditText quantityEditText;
        public TextView priceTextView;
        public ImageView imgae;
        public ImageButton btnXoaGioHang;
        public ViewHolder(View itemView) {
            super(itemView);
            //anh xa
            nameTextView = itemView.findViewById(R.id.nameTextView); // Thay thế 'R.id.nameTextView' bằng ID thích hợp
            quantityEditText = itemView.findViewById(R.id.quantityEditText); // Thay thế 'R.id.quantityTextView' bằng ID thích hợp
           // imgae = itemView.findViewById(R.id.imgProduct);
            priceTextView = itemView.findViewById(R.id.priceTextView); // Thay thế 'R.id.priceTextView' bằng ID thích hợp
            btnXoaGioHang = itemView.findViewById(R.id.deleteProduct);
        }

    }

}
