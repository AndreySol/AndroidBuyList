package com.buylist.solomakha.buylistapp.storage.database.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.buylist.solomakha.buylistapp.storage.database.DataBaseHelper;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.storage.database.tables.CategoriesTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.ProductsTable;

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

        ContentValues values = new ContentValues();
        values.put(CategoriesTable.COLUMN_NAME, categoryName);

        long id = db.insert(CategoriesTable.TABLE_NAME, null, values);

        dbHelper.close();

        return id;
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
            productValues.put(ProductsTable.COLUMN_UNIT_ID, product.getGroupId());
            createdProductionId = db.insert(ProductsTable.TABLE_NAME, null, productValues);
        } finally {
            dbHelper.close();
        }

        return createdProductionId;
    }
}
