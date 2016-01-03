package com.buylist.solomakha.buylistapp.storage.database.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.buylist.solomakha.buylistapp.storage.database.DataBaseHalper;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.storage.database.tables.GroupTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.ProductTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.ProductsList_ProductTable;

/**
 * Created by asolomakha on 1/4/2016.
 */
public class DBDataSource implements DataSource
{

    DataBaseHalper dbHelper;

    public DBDataSource(Context context)
    {
        dbHelper = new DataBaseHalper(context);
    }

    @Override
    public long createGroup(String groupName)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GroupTable.COLUMN_NAME, groupName);

        long id = db.insert(GroupTable.TABLE_NAME, null, values);

        dbHelper.close();

        return id;
    }

    @Override
    public long createProduct(Product product)
    {
        long createdProductionId = -1;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.beginTransaction();
        Cursor cursor = db.query(GroupTable.TABLE_NAME, new String[]{GroupTable.COLUMN_ID}, GroupTable.COLUMN_ID + " = " + product.getGroupId(), null, null, null, null);
        if (cursor.getCount() > 0)
        {
            ContentValues productValues = new ContentValues();
            productValues.put(ProductTable.COLUMN_NAME, product.getName());
            productValues.put(ProductTable.COLUMN_PRIORITY, product.isPriority());
            productValues.put(ProductTable.COLUMN_QUANTITY, product.getQuantity());
            productValues.put(ProductTable.COLUMN_GROUP_ID, product.getGroupId());
            createdProductionId = db.insert(ProductTable.TABLE_NAME, null, productValues);
            if (createdProductionId >= 0)
            {
                db.setTransactionSuccessful();
            }
        }
        db.endTransaction();
        dbHelper.close();
        return createdProductionId;
    }
}
