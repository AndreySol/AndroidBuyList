package com.buylist.solomakha.buylistapp.storage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.buylist.solomakha.buylistapp.storage.database.tables.CategoriesTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.Categories_ProductsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.ProductsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.ListsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.Lists_ProductsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.UnitsTable;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "BuyListDB.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        ProductsTable.create(db);
        UnitsTable.create(db);
        CategoriesTable.create(db);
        ListsTable.create(db);
        Lists_ProductsTable.create(db);
        Categories_ProductsTable.create(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        ProductsTable.upgrade(db);
        UnitsTable.upgrade(db);
        CategoriesTable.upgrade(db);
        ListsTable.upgrade(db);
        Lists_ProductsTable.upgrade(db);
    }
}
