package com.buylist.solomakha.buylistapp.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.buylist.solomakha.buylistapp.R;
import com.buylist.solomakha.buylistapp.storage.database.dal.DataBase;
import com.buylist.solomakha.buylistapp.storage.database.dal.DataSource;
import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;
import com.buylist.solomakha.buylistapp.storage.database.entities.Category;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.storage.database.entities.Unit;
import com.buylist.solomakha.buylistapp.ui.adapter.Basket_Dialog;
import com.buylist.solomakha.buylistapp.ui.adapter.EntityAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class MainActivity extends Activity implements DialogInterface.OnClickListener
{

    private static final String LOG_TAG = "myLogs";
    boolean alreadyCreated = false;
    ListView listView;

    Basket_Dialog basket_dialog;
    EntityAdapter listAdapter;

    List<Basket> productsListList = new ArrayList<>();

    private static final int MENU_CONTEXT_EDIT_ID = 0;
    private static final int MENU_CONTEXT_DELETE_ID = 1;


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        // outState.set
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basket_dialog = new Basket_Dialog();

        basket_dialog.set_listener(this);
        Log.d(LOG_TAG, "set_listener");
        //if(savedInstanceState!=null) {
        //restoreProgress(savedInstanceState);
        //}

        listView = (ListView) findViewById(R.id.productList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Basket productsList = productsListList.get(position);
                startActivity(new Intent(getApplicationContext(), ProductsActivity.class).putExtra("Id", productsList.getId()));
            }
        });


        registerForContextMenu(listView);


        DataSource dataSource = DataBase.getInstance(this);
        productsListList = dataSource.getBaskets();
        // listAdapter = new ArrayAdapter<Basket>(this, R.layout.list_item, R.id.listItem, productsListList);

        listAdapter = new EntityAdapter((ArrayList) productsListList, this);
        listView.setAdapter(listAdapter);


        Button b = (Button) findViewById(R.id.but_fill);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!alreadyCreated)
                {
                    fillDBDefaultValues();
                }
            }
        });

        Button createListButton = (Button) findViewById(R.id.create_list_button);
        createListButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                basket_dialog.show(getFragmentManager(), "");
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        refreshList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        if (v.getId() == R.id.productList)
        {
            menu.add(Menu.NONE, MENU_CONTEXT_EDIT_ID, Menu.NONE, "Edit");
            menu.add(Menu.NONE, MENU_CONTEXT_DELETE_ID, Menu.NONE, "Delete");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case MENU_CONTEXT_EDIT_ID:
                showEditListDialog(productsListList.get(info.position));
                return true;
            case MENU_CONTEXT_DELETE_ID:
                DataBase.getInstance(this).deleteBasket(productsListList.get(info.position).getId());
                refreshList();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void refreshList()
    {
        productsListList = DataBase.getInstance(this).getBaskets();
        listAdapter.refresh(productsListList);

    }

    private void showEditListDialog(final Basket pl)
    {

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.create_list_dialog, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Edit list");
        builder.setView(dialogView);

        EditText editText = (EditText) dialogView.findViewById(R.id.list_name_textedit);
        editText.setText(pl.getName());

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                EditText edit = (EditText) ((AlertDialog) dialog).findViewById(R.id.list_name_textedit);
                pl.setName(edit.getText().toString());
                DataBase.getInstance(MainActivity.this).updateBasket(pl);
                refreshList();
            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void fillDBDefaultValues()
    {
        DataSource dataSource = DataBase.getInstance(this);
        Category categoryOthers = dataSource.createCategory("Others");
        Category categoryDairy = dataSource.createCategory("Dairy");
        Category categoryMeat = dataSource.createCategory("Meat");
        Category categoryFruits = dataSource.createCategory("Fruit");
        Category categoryAppliances = dataSource.createCategory("Appliances");

        Unit unitKg = dataSource.createUnit("kg");
        Unit unitL = dataSource.createUnit("L.");
        Unit unitPcs = dataSource.createUnit("pcs");

        Basket listPostProducts = dataSource.createBasket("PostProducts");
        Basket listPostClothes = dataSource.createBasket("PostClothes");
        Basket listPostAppliances = dataSource.createBasket("PostAppliances");

        //ПРОДУКТ:
        //продукты  привязка к UNIT and CATEGORY
        //Apple - не внесен  в список
        Product product = new Product();
        product.setName("Apple");
        product.setCategory(categoryFruits);
        product.setPriority(false);
        product.setQuantity(2);
        product.setUnit(unitKg);
        Product productId = dataSource.createProduct(product);
        dataSource.assignProductToBasket(listPostProducts.getId(), productId.getId());//связка  со списком

        // Kettle - не внесен  в список
        Product product1 = new Product();
        product1.setName("Kettle");
        product1.setCategory(categoryAppliances);
        product1.setPriority(false);
        product1.setQuantity(1);
        product1.setUnit(unitPcs);
        Product productId1 = dataSource.createProduct(product1);
        dataSource.assignProductToBasket(listPostProducts.getId(), productId1.getId());//связка  со списком
        dataSource.assignProductToBasket(listPostClothes.getId(), productId1.getId());//связка  со списком

        // Banana -  внесен  в список
        Product product2 = new Product();
        product2.setName("Banana");
        product2.setCategory(categoryFruits);
        product2.setPriority(false);
        product2.setQuantity(1.5f);
        product2.setUnit(unitKg);
        Product productId2 = dataSource.createProduct(product2);
        dataSource.assignProductToBasket(listPostProducts.getId(), productId2.getId());//связка  со списком

        // Orange -  внесен  в список
        Product product3 = new Product();
        product3.setName("Orange");
        product3.setCategory(categoryFruits);
        product3.setPriority(false);
        product3.setQuantity(2.5f);
        product3.setUnit(unitKg);
        Product productId3 = dataSource.createProduct(product3);
        dataSource.assignProductToBasket(listPostProducts.getId(), productId3.getId());//связка  со списком

        alreadyCreated = true;

        refreshList();
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        Log.d(LOG_TAG, "in onClick");
        EditText edit = (EditText) ((AlertDialog) dialog).findViewById(R.id.list_name_textedit);
        String listName = edit.getText().toString();
        Log.d(LOG_TAG, "listName is " + listName);
        DataBase.getInstance(this).createBasket(listName);
        refreshList();

    }
}
