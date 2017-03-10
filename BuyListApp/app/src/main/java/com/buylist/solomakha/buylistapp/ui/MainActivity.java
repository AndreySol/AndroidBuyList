package com.buylist.solomakha.buylistapp.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.buylist.solomakha.buylistapp.R;
import com.buylist.solomakha.buylistapp.storage.database.dal.DataBaseStorage;
import com.buylist.solomakha.buylistapp.storage.database.dal.Storage;
import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;
import com.buylist.solomakha.buylistapp.storage.database.entities.Category;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.storage.database.entities.Unit;
import com.buylist.solomakha.buylistapp.ui.adapter.BasketAdapter;
import com.buylist.solomakha.buylistapp.ui.adapter.BasketDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements DialogInterface.OnClickListener, OnClickListener
{
    private static final String LOG_TAG = "myLogs";
    private boolean mAlreadyCreated = false;

    private RecyclerView mBasketListView;
    private BasketAdapter mBasketAdapter;

    private BasketDialog mBasketDialog;

    private List<Basket> basketList = new ArrayList<>();

    private static final int MENU_CONTEXT_EDIT_ID = 0;
    private static final int MENU_CONTEXT_DELETE_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBasketDialog = new BasketDialog();
        mBasketDialog.setListener(this);
        Log.d(LOG_TAG, "setListener");

        //registerForContextMenu(mBasketListView);

        Storage storage = DataBaseStorage.getInstance(this);
        basketList = storage.getBaskets();


        mBasketListView = (RecyclerView) findViewById(R.id.basket_recycler_list);
        mBasketListView.setLayoutManager(new LinearLayoutManager(this));
        mBasketListView.setAdapter(new BasketAdapter(this, basketList, this));

        Button b = (Button) findViewById(R.id.but_fill);
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!mAlreadyCreated)
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
                mBasketDialog.show(getFragmentManager(), "");
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
        if (v.getId() == R.id.basket_recycler_list)
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
                showEditListDialog(basketList.get(info.position));
                return true;
            case MENU_CONTEXT_DELETE_ID:
                DataBaseStorage.getInstance(this).deleteBasket(basketList.get(info.position).getId());
                refreshList();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void refreshList()
    {
        /*basketList = DataBaseStorage.getInstance(this).getBaskets();
        mBasketAdapter.notifyDataSetChanged();*/
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
                DataBaseStorage.getInstance(MainActivity.this).updateBasket(pl);
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
        Storage storage = DataBaseStorage.getInstance(this);
        Category categoryOthers = storage.createCategory("Others");
        Category categoryDairy = storage.createCategory("Dairy");
        Category categoryMeat = storage.createCategory("Meat");
        Category categoryFruits = storage.createCategory("Fruit");
        Category categoryAppliances = storage.createCategory("Appliances");

        Unit unitKg = storage.createUnit("kg");
        Unit unitL = storage.createUnit("L.");
        Unit unitPcs = storage.createUnit("pcs");

        Basket listPostProducts = storage.createBasket("PostProducts");
        Basket listPostClothes = storage.createBasket("PostClothes");
        Basket listPostAppliances = storage.createBasket("PostAppliances");

        //ПРОДУКТ:
        //продукты  привязка к UNIT and CATEGORY
        //Apple - не внесен  в список
        Product product = new Product();
        product.setName("Apple");
        product.setCategory(categoryFruits);
        product.setPriority(false);
        product.setQuantity(2);
        product.setUnit(unitKg);
        Product productId = storage.createProduct(product);
        storage.assignProductToBasket(listPostProducts.getId(), productId.getId());//связка  со списком

        // Kettle - не внесен  в список
        Product product1 = new Product();
        product1.setName("Kettle");
        product1.setCategory(categoryAppliances);
        product1.setPriority(false);
        product1.setQuantity(1);
        product1.setUnit(unitPcs);
        Product productId1 = storage.createProduct(product1);
        storage.assignProductToBasket(listPostProducts.getId(), productId1.getId());//связка  со списком
        storage.assignProductToBasket(listPostClothes.getId(), productId1.getId());//связка  со списком

        // Banana -  внесен  в список
        Product product2 = new Product();
        product2.setName("Banana");
        product2.setCategory(categoryFruits);
        product2.setPriority(false);
        product2.setQuantity(1.5f);
        product2.setUnit(unitKg);
        Product productId2 = storage.createProduct(product2);
        storage.assignProductToBasket(listPostProducts.getId(), productId2.getId());//связка  со списком

        // Orange -  внесен  в список
        Product product3 = new Product();
        product3.setName("Orange");
        product3.setCategory(categoryFruits);
        product3.setPriority(false);
        product3.setQuantity(2.5f);
        product3.setUnit(unitKg);
        Product productId3 = storage.createProduct(product3);
        storage.assignProductToBasket(listPostProducts.getId(), productId3.getId());//связка  со списком

        mAlreadyCreated = true;

        refreshList();
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        Log.d(LOG_TAG, "in onClick");
        EditText edit = (EditText) ((AlertDialog) dialog).findViewById(R.id.list_name_textedit);
        String listName = edit.getText().toString();
        Log.d(LOG_TAG, "listName is " + listName);
        DataBaseStorage.getInstance(this).createBasket(listName);
        refreshList();

    }

    @Override
    public void onClick(View v, int position)
    {
        Basket productsList = basketList.get(position);
        startActivity(new Intent(getApplicationContext(), ProductsActivity.class).putExtra("Id", productsList.getId()));
    }
}
