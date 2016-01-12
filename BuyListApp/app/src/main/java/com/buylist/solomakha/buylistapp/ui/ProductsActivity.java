package com.buylist.solomakha.buylistapp.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.buylist.solomakha.buylistapp.R;
import com.buylist.solomakha.buylistapp.storage.database.dal.DBDataSource;
import com.buylist.solomakha.buylistapp.storage.database.entities.Category;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.storage.database.entities.Unit;
import com.buylist.solomakha.buylistapp.ui.adapter.ExpandableProductsAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {
    private ExpandableProductsAdapter listAdapter;
    private ExpandableListView expListView;

    private long listId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        listId = getIntent().getLongExtra("Id", -1);

        expListView = (ExpandableListView) findViewById(R.id.products);
        listAdapter = new ExpandableProductsAdapter(this);
        expListView.setAdapter(listAdapter);

        Button addProductButton = (Button) findViewById(R.id.add_production_button);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddProductDialog();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refrashList();
    }

    private void showAddProductDialog() {

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_product_dialog, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(ProductsActivity.this);
        builder.setTitle("Add product");
        builder.setView(dialogView);

        List<Unit> unitList = DBDataSource.getIns(this).getUnits();
        ArrayAdapter<Unit> unityAdapter = new ArrayAdapter<Unit>(this, android.R.layout.simple_spinner_item, unitList);
        unityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner quantitySpinner = (Spinner) dialogView.findViewById(R.id.product_quantity_spinner);
        quantitySpinner.setAdapter(unityAdapter);

        List<Category> categoryList = DBDataSource.getIns(this).getCategories();
        ArrayAdapter<Category> categoryAdapter = new ArrayAdapter<Category>(this, android.R.layout.simple_spinner_item, categoryList);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner categorySpinner = (Spinner) dialogView.findViewById(R.id.product_category_spinner);
        categorySpinner.setAdapter(categoryAdapter);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText productNameEditText = (EditText) dialogView.findViewById(R.id.product_name_edittext);
                EditText productQuantityEditText = (EditText) dialogView.findViewById(R.id.product_quantity_edittext);
                Unit unit = (Unit) quantitySpinner.getSelectedItem();
                Category category = (Category) categorySpinner.getSelectedItem();

                Product product = new Product();
                product.setName(productNameEditText.getText().toString());
                product.setQuantity(Float.valueOf(productQuantityEditText.getText().toString()));
                product.setUnit(unit);
                product.setCategory(category);

                DBDataSource.getIns(ProductsActivity.this).createProduct(product);
                DBDataSource.getIns(ProductsActivity.this).assignProductToList(listId, product.getId());

                refrashList();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void refrashList() {
        HashMap<String, List<Product>> listDataChild = new HashMap<String, List<Product>>();
        List<Product> productList = DBDataSource.getIns(this).getProductsFromList(listId);

        for (Product product : productList) {
            if (listDataChild.containsKey(product.getCategory().getName())) {
                listDataChild.get(product.getCategory().getName()).add(product);
            } else {
                List<Product> products = new ArrayList<>();
                products.add(product);
                listDataChild.put(product.getCategory().getName(), products);
            }
        }

        List<String> listDataHeader = new ArrayList<String>(listDataChild.keySet());

        listAdapter.setValues(listDataHeader, listDataChild);
        for (int i = 0; i < listDataHeader.size(); i++) {
            expListView.expandGroup(i);
        }
    }
}
