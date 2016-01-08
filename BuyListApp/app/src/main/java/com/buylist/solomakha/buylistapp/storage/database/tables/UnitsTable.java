package com.buylist.solomakha.buylistapp.storage.database.tables;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class UnitsTable {

    public final static String TABLE_NAME = "Unit";
    public final static String COLUMN_ID = "Id";
    public final static String COLUMN_NAME = "Name";

    private final static String CREATE_TABLE = " create table " +
            TABLE_NAME + "( " +
            COLUMN_ID + " integer  primary key autoincrement, " +
            COLUMN_NAME + " text not null " +
            ");";

    public static void create(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    public static void upgrade(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(CREATE_TABLE);
    }
}
