package com.buylist.solomakha.buylistapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.buylist.solomakha.buylistapp.R;
import com.buylist.solomakha.buylistapp.storage.database.dal.DBDataSource;
import com.buylist.solomakha.buylistapp.storage.database.dal.DataSource;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.ui.adapter.ExpandableProductsAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductsActivity extends AppCompatActivity
{

    private ExpandableProductsAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<Product>> listDataChild;

    private DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        dataSource = new DBDataSource(this);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.products);

        listAdapter = new ExpandableProductsAdapter(this);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        prepareListData();
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Product>>();

        List<Product> productList = dataSource.getAllProductsFromList(getIntent().getIntExtra("Id", -1));

        listDataHeader.add("Others");
        listDataChild.put(listDataHeader.get(0), productList);

        listAdapter.setValues(listDataHeader, listDataChild);
    }
}
