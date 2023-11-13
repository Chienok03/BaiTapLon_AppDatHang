package com.example.baitaplon_appdathang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter1 extends RecyclerView.Adapter<ProductAdapter1.ViewHolder> implements Filterable {
     private  List<Product> productList;
     private final List<Product> productListOld;
     private final Context context;

    public ProductAdapter1( Context context,List<Product> productList) {
        this.productList = productList;
        this.productListOld = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_prodcut1, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        if( product == null){
            return;
        }
        holder.nameTextView.setText(product.getName());
        //holder.imgae.setImageResource(product.getImage());
        String format =  String.format("%.3f", product.getPrice());
        holder.priceTextView.setText(format);

        holder.layout_item.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 onclickGotoDsprodcut(product);
             }
         });
    }
    private  void onclickGotoDsprodcut( Product product){
        Intent intent = new Intent(context , ProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("oject_product" ,product);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public ImageView imgae;
        public TextView priceTextView;
        private RelativeLayout layout_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_item = itemView.findViewById(R.id.layout_item);
            nameTextView = itemView.findViewById(R.id.NameSpTextView);
            imgae = itemView.findViewById(R.id.ImageProduct);
            priceTextView = itemView.findViewById(R.id.PriceSpTextView);

        }
    }
    // tim  kiếm
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()) {
                    // Nếu không có từ khóa tìm kiếm, sử dụng danh sách gốc
                    productList = productListOld;
                } else {
                    // Tìm kiếm và lưu kết quả vào danh sách tạm thời
                    List<Product> filteredList = new ArrayList<>();
                    for (Product product : productListOld) {
                        if (product.getName().toLowerCase().contains(strSearch.toLowerCase())) {
                            filteredList.add(product);
                        }
                    }
                    productList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = productList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.values != null) {
                    productList = (List<Product>) results.values;
                    notifyDataSetChanged();
                }
            }
        };
    }

}
