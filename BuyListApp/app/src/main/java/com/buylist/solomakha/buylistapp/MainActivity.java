package com.buylist.solomakha.buylistapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.buylist.solomakha.buylistapp.storage.database.dal.DBDataSource;
import com.buylist.solomakha.buylistapp.storage.database.dal.DataSource;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b =(Button) findViewById(R.id.but_fill);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillDBDefaultValues();
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();


    }


    protected void  fillDBDefaultValues()
    {

        DataSource dataSource = new DBDataSource(this);
        long groupId = dataSource.createCategory("Others");
        long groupId1 = dataSource.createCategory("Others");
        long groupId2 = dataSource.createCategory("Others");

        Product product = new Product();
        product.setName("Apple");
        product.setGroupId(String.valueOf(groupId));
        product.setPriority(false);
        product.setQuantity(2);

        long productId = dataSource.createProduct(product);

        System.out.println(productId);
    }
}
