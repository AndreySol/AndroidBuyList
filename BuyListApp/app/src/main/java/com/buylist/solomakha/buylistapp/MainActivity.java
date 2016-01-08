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
    Boolean alreadyCreated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b =(Button) findViewById(R.id.but_fill);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!alreadyCreated)  fillDBDefaultValues();

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
        long categoryId = dataSource.createCategory("Others");
        long categoryId_Dai = dataSource.createCategory("Dairy");
        long categoryId_Me = dataSource.createCategory("Meat");
        long categoryId_Fr = dataSource.createCategory("Fruit");
        long categoryId_Appliances = dataSource.createCategory("Appliances");

        long unitId_kg = dataSource.createUnit("kg");
        long unitId1 = dataSource.createUnit("L.");
        long unitId_pcs = dataSource.createUnit("pcs");

        long listId_Products = dataSource.createList("PostProducts");
        long listId1 =  dataSource.createList("PostClothes");
        long listId2 = dataSource.createList("PostAppliances");



//ПРОДУКТ:
        //продукты  привязка к UNIT and CATEGORY
        //Apple - не внесен  в список
        Product product = new Product();
        product.setName("Apple");
        product.setCategory("Fruit");
        product.setPriority(false);
        product.setQuantity(2);
        product.setUnit("kg");
        long productId = dataSource.createProduct(product);


        // Kettle - не внесен  в список
        Product product1 = new Product();
        product1.setName("Kettle");
        product1.setCategory("Appliances");
        product1.setPriority(false);
        product1.setQuantity(1);
        product1.setUnit("pcs");
        long productId1 = dataSource.createProduct(product1);


        // Banana -  внесен  в список
        Product product2 = new Product();
        product2.setName("Banana");
        product2.setCategory("Fruit");
        product2.setPriority(false);
        product2.setQuantity(1.5f);
        product2.setUnit("kg");
        long productId2 = dataSource.createProduct(product2);

        dataSource.createListsProductsItem(listId_Products, productId2);//связка  со списком


        // Orange -  внесен  в список
        Product product3 = new Product();
        product3.setName("Orange");
        product3.setCategory("Fruit");
        product3.setPriority(false);
        product3.setQuantity(2.5f);
        product3.setUnit("kg");
        long productId3 = dataSource.createProduct(product3);
        dataSource.createListsProductsItem(listId_Products, productId3);//связка  со списком

        //System.out.println(productId);
        alreadyCreated =true;
    }
}
