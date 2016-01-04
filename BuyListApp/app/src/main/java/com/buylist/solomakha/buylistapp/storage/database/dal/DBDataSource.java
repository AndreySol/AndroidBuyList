package com.buylist.solomakha.buylistapp.storage.database.dal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.buylist.solomakha.buylistapp.storage.database.DataBaseHelper;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.storage.database.entities.ProductsList;
import com.buylist.solomakha.buylistapp.storage.database.tables.CategoriesTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.ListsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.Lists_ProductsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.ProductsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.UnitsTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asolomakha on 1/4/2016.
 */
public class DBDataSource implements DataSource {

    DataBaseHelper dbHelper;

    public DBDataSource(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    @Override
    public long createCategory(String categoryName) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = -1;

        try {
            ContentValues values = new ContentValues();
            values.put(CategoriesTable.COLUMN_NAME, categoryName);

            id = db.insert(CategoriesTable.TABLE_NAME, null, values);
        } finally {
            dbHelper.close();
        }
        return id;
    }

    @Override
    public long createUnit(String unit) {
        long createdUnitId = -1;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(UnitsTable.COLUMN_NAME, unit);

            createdUnitId = db.insert(UnitsTable.TABLE_NAME, null, values);
        } finally {
            dbHelper.close();
        }
        return createdUnitId;
    }

    @Override
    public long createProduct(Product product) {
        long createdProductionId = -1;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            ContentValues productValues = new ContentValues();
            productValues.put(ProductsTable.COLUMN_NAME, product.getName());
            productValues.put(ProductsTable.COLUMN_PRIORITY, product.isPriority());
            productValues.put(ProductsTable.COLUMN_QUANTITY, product.getQuantity());
            productValues.put(ProductsTable.COLUMN_UNIT_ID, product.getUnit());
            productValues.put(ProductsTable.COLUMN_CATEGORY_ID, product.getCategory());
            createdProductionId = db.insert(ProductsTable.TABLE_NAME, null, productValues);
        } finally {
            dbHelper.close();
        }

        return createdProductionId;
    }

    @Override
    public long createList(String name) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long createdListId = -1;
        try {
            ContentValues listValues = new ContentValues();
            listValues.put(ListsTable.COLUMN_NAME, name);

            createdListId = db.insert(ListsTable.TABLE_NAME, null, listValues);
        } finally {
            dbHelper.close();
        }
        return createdListId;
    }

    @Override
    public long createListsProductsItem(long listId, long productId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long createdId = -1;
        try {
            ContentValues listValues = new ContentValues();
            listValues.put(Lists_ProductsTable.COLUMN_LIST_ID, listId);
            listValues.put(Lists_ProductsTable.COLUMN_PRODUCT_ID, productId);

            createdId = db.insert(Lists_ProductsTable.TABLE_NAME, null, listValues);
        } finally {
            dbHelper.close();
        }
        return createdId;
    }

    @Override
    public List<ProductsList> getAllProductsList() {
        List<ProductsList> productsLists = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(ListsTable.TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        ProductsList pl = new ProductsList();
                        pl.setId(cursor.getInt(cursor.getColumnIndex(ListsTable.COLUMN_ID)));
                        pl.setName(cursor.getString(cursor.getColumnIndex(ListsTable.COLUMN_NAME)));
                        productsLists.add(pl);
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dbHelper.close();
        }
        return productsLists;
    }

    public List<Product> getAllProductsFromList(int listId) {
        List<Product> productLists = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<String> proructsIdList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query(Lists_ProductsTable.TABLE_NAME, new String[]{Lists_ProductsTable.COLUMN_PRODUCT_ID}, Lists_ProductsTable.COLUMN_LIST_ID + " = " + listId, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        proructsIdList.add(cursor.getString(cursor.getColumnIndex(Lists_ProductsTable.COLUMN_PRODUCT_ID)));
                    } while (cursor.moveToNext());
                }
            }
            if (!proructsIdList.isEmpty()) {
                cursor = db.query(ProductsTable.TABLE_NAME, null, ProductsTable.COLUMN_ID + String.format(" IN (" + TextUtils.join(", ", proructsIdList) + ")"), null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        Product product = new Product();
                        product.setName(cursor.getString(cursor.getColumnIndex(ProductsTable.COLUMN_NAME)));
                        int priority = cursor.getInt(cursor.getColumnIndex(ProductsTable.COLUMN_PRIORITY));
                        product.setPriority(priority > 0 ? true : false);
                        product.setQuantity(cursor.getInt(cursor.getColumnIndex(ProductsTable.COLUMN_QUANTITY)));
                        product.setImage(cursor.getString(cursor.getColumnIndex(ProductsTable.COLUMN_IMAGE)));
                        productLists.add(product);
                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dbHelper.close();
        }
        return productLists;
    }
}
