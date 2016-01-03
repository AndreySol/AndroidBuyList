package com.buylist.solomakha.buylistapp.storage.database.tables;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class ProductTable
{
    public final static String TABLE_NAME = "Product";
    public final static String COLUMN_ID = "Id";
    public final static String COLUMN_GROUP_ID = "GroupId";
    public final static String COLUMN_NAME = "Name";
    public final static String COLUMN_PRIORITY = "Priority";
    public final static String COLUMN_QUANTITY = "Quantity";
    public final static String COLUMN_IMAGE = "Image";

    private final static String CREATE_TABLE = " create table " +
            TABLE_NAME + "( " +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_GROUP_ID + " integer, " +
            COLUMN_NAME + " text not null, " +
            COLUMN_PRIORITY + " integer, " +
            COLUMN_QUANTITY + " integer, " +
            COLUMN_IMAGE + " text " +
            ");";

    public static void create(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
}

    public static void upgrade(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(CREATE_TABLE);
    }
}
