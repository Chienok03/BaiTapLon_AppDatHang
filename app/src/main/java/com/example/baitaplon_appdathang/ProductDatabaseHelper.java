package com.example.baitaplon_appdathang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabaseHelper extends SQLiteOpenHelper {
    // Database Information
    private static final String DATABASE_NAME = "ProductDatabase.db";
    // database version
    private static final int DATABASE_VERSION = 1;
    // Table Name
    private static final String TABLE_PRODUCTS = "products";
    // Table columns
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";

    // Table Name
    private static final String TABLE_NEW_PRODUCTS = "products_new";
    // Table columns
    private static final String COLUMN_ID_NEW = "_id_new";
    private static final String COLUMN_NAME_NEW = "name_new";
    private static final String COLUMN_PRICE_NEW = "price_new";
    private static final String COLUMN_QUANTITY_NEW = "quantity_new";

    private static final String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_NAME + " TEXT," +
            COLUMN_PRICE + " REAL)";

    private static final String CREATE_NEW_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_NEW_PRODUCTS + "(" +
            COLUMN_ID_NEW + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_NEW + " TEXT , " +
            COLUMN_QUANTITY_NEW + " INTEGER," +
            COLUMN_PRICE_NEW + " REAL)";

    public ProductDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        //Tạo 2 bảng
        db.execSQL(CREATE_PRODUCTS_TABLE);
        db.execSQL(CREATE_NEW_PRODUCTS_TABLE);
        //Bảng user
        db.execSQL("create table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }

    public boolean insertData (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username",username);
        values.put("password", password);

        long result = db.insert("users", null, values);
        if(result == -1) return  false;
        else
            return  true;
    }
    public  boolean checkusername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[] {username});
        if(cursor.getCount()>0)
            return  true;
        else
            return false;
    }

    public  boolean checkusernamepassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", new String[] {username, password});
        if(cursor.getCount()>0)
            return  true;
        else
            return false;
    }
    public void deleteAllProducts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, null, null);
        db.close();
    }

    public void deleteProduct(int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?", new String[]{String.valueOf(productId)});
        db.close();
    }

    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, product.getId());
        values.put(COLUMN_NAME, product.getName());
        values.put(COLUMN_PRICE, product.getPrice());

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public void addNewProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID_NEW, product.getId());
        values.put(COLUMN_NAME_NEW, product.getName());
        values.put(COLUMN_QUANTITY_NEW, product.getQuantity());
        values.put(COLUMN_PRICE_NEW, product.getPrice());
        db.insert(TABLE_NEW_PRODUCTS, null, values);
        db.close();
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                product.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                product.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productList;
    }

    public List<Product> getAllNewProducts() {
        List<Product> productList_new = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NEW_PRODUCTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_NEW)));
                product.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_NEW)));
                product.setQuantity(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY_NEW)));
                product.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE_NEW)));
                productList_new.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productList_new;
    }

    public List<Product> getProductsFromRow9Onwards() {
        List<Product> productList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_ID + " >9";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                product.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                product.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productList;
    }
}



