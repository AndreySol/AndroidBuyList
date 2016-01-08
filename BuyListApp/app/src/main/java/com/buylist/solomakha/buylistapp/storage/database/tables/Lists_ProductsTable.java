package com.buylist.solomakha.buylistapp.storage.database.tables;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class Lists_ProductsTable {

    public final static String TABLE_NAME = "Lists_Products";
    public final static String COLUMN_ID = "Id";
    public final static String COLUMN_LIST_ID = "ListId";
    public final static String COLUMN_PRODUCT_ID = "ProductId";

    private final static String CREATE_TABLE = " create table " +
            TABLE_NAME + "( " +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_LIST_ID + " integer REFERENCES "+ ListsTable.TABLE_NAME + " ( " + ListsTable.COLUMN_ID + " ) " +
            "  ON DELETE CASCADE " +
            "  ON UPDATE CASCADE ," +
            COLUMN_PRODUCT_ID + " integer  REFERENCES "+ ProductsTable.TABLE_NAME + " ( " + ProductsTable.COLUMN_ID + " ) " +
            "  ON DELETE CASCADE " +
            "  ON UPDATE CASCADE " +
            ");";

    public static void create(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    public static void upgrade(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(CREATE_TABLE);
    }
}
