package com.buylist.solomakha.buylistapp.storage.database.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.buylist.solomakha.buylistapp.storage.database.DataBaseHelper;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.storage.database.tables.CategoriesTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.Categories_ProductsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.ListsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.Lists_ProductsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.ProductsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.UnitsTable;

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
        long id=-1;



        try {
            ContentValues values = new ContentValues();
            values.put(CategoriesTable.COLUMN_NAME, categoryName);

             id = db.insert(CategoriesTable.TABLE_NAME, null, values);
        }

        finally {
            dbHelper.close();
        }
        return id;
    }

    @Override
    public long createUnit(String unit) {
        long createdUnitId = -1;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            ContentValues values= new ContentValues();
            values.put(UnitsTable.COLUMN_NAME,unit);

            createdUnitId= db.insert(UnitsTable.TABLE_NAME,null,values);
        }

        finally {
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
            productValues.put(ProductsTable.COLUMN_UNIT_ID, product.getCategoryId());
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
    public long createProductsCategoriesItem(long categoryId, long productId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long createdId = -1;
        try {
            ContentValues listValues = new ContentValues();
            listValues.put(Categories_ProductsTable.COLUMN_CATEGORY_ID, categoryId);
            listValues.put(Categories_ProductsTable.COLUMN_PRODUCT_ID, productId);

            createdId = db.insert(Categories_ProductsTable.TABLE_NAME, null, listValues);
        } finally {
            dbHelper.close();
        }
        return createdId;
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
}
