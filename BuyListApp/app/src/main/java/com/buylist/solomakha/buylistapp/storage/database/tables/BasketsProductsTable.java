package com.buylist.solomakha.buylistapp.storage.database.tables;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class BasketsProductsTable {
    public final static String TABLE_NAME = "BasketsProducts";
    public final static String COLUMN_ID = "Id";
    public final static String COLUMN_BASKET_ID = "BasketId";
    public final static String COLUMN_PRODUCT_ID = "ProductId";
    public final static String COLUMN_BOUGHT = "Bought";

    private final static String CREATE_TABLE = " create table " +
            TABLE_NAME + "( " +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_BASKET_ID + " integer REFERENCES "+ BasketsTable.TABLE_NAME + " ( " + BasketsTable.COLUMN_ID + " ) " +
            "  ON DELETE CASCADE " +
            "  ON UPDATE CASCADE ," +
            COLUMN_PRODUCT_ID + " integer  REFERENCES "+ ProductsTable.TABLE_NAME + " ( " + ProductsTable.COLUMN_ID + " ) " +
            "  ON DELETE CASCADE " +
            "  ON UPDATE CASCADE ," +
            COLUMN_BOUGHT + " integer DEFAULT 0 " +
            ");";

    public static void create(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    public static void upgrade(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(CREATE_TABLE);
    }
}
