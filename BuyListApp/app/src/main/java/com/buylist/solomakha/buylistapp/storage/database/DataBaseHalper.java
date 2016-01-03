package com.buylist.solomakha.buylistapp.storage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.buylist.solomakha.buylistapp.storage.database.tables.GroupTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.ProductTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.ProductsListTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.ProductsList_ProductTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.UnitTable;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class DataBaseHalper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "BuyListDB.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHalper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        ProductTable.create(db);
        UnitTable.create(db);
        GroupTable.create(db);
        ProductsListTable.create(db);
        ProductsList_ProductTable.create(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        ProductTable.upgrade(db);
        UnitTable.upgrade(db);
        GroupTable.upgrade(db);
        ProductsListTable.upgrade(db);
        ProductsList_ProductTable.upgrade(db);
    }
}
