package com.buylist.solomakha.buylistapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.buylist.solomakha.buylistapp.storage.database.DataBaseHalper;
import com.buylist.solomakha.buylistapp.storage.database.dal.DBDataSource;
import com.buylist.solomakha.buylistapp.storage.database.dal.DataSource;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.storage.database.tables.ProductTable;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostResume()
    {
        super.onPostResume();

        DataSource dataSource = new DBDataSource(this);
        long groupId = dataSource.createGroup("Test group");

        Product product = new Product();
        product.setName("Apple");
        product.setGroupId(String.valueOf(groupId));
        product.setPriority(false);
        product.setQuantity(2);

        long productId = dataSource.createProduct(product);

        System.out.println(productId);
    }
}
