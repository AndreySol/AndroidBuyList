package com.buylist.solomakha.buylistapp.storage.database.tables;

import android.database.sqlite.SQLiteDatabase;

import com.buylist.solomakha.buylistapp.storage.database.entities.Category;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class ProductsTable
{
    public final static String TABLE_NAME = "Product";
    public final static String COLUMN_ID = "Id";
    public final static String COLUMN_UNIT_ID = "UnitId";
    public final static String COLUMN_NAME = "Name";
    public final static String COLUMN_PRIORITY = "Priority";
    public final static String COLUMN_QUANTITY = "Quantity";
    public final static String COLUMN_IMAGE = "Image";
    public final static String COLUMN_CATEGORY_ID = "CategoryId";
    public final static String COLUMN_TEMP = "Temp";

    private final static String CREATE_TABLE = " create table " +
            TABLE_NAME + "( " +
            COLUMN_ID + " integer primary key autoincrement  , " +
            COLUMN_UNIT_ID + " integer  DEFAULT 1 REFERENCES "+ UnitsTable.TABLE_NAME + " ( " + UnitsTable.COLUMN_ID + " ) " +
            "  ON DELETE SET DEFAULT " +
            "  ON UPDATE CASCADE , " +
            COLUMN_CATEGORY_ID + " integer  DEFAULT 1 REFERENCES "+ CategoriesTable.TABLE_NAME + " ( " + CategoriesTable.COLUMN_ID + " ) " +
            "  ON DELETE SET DEFAULT " +
            "  ON UPDATE CASCADE , " +
            COLUMN_NAME + " text not null, " +
            COLUMN_PRIORITY + " integer DEFAULT 0, " +
            COLUMN_QUANTITY + " real, " +
            COLUMN_IMAGE + " text, " +
            COLUMN_TEMP + " integer  not null DEFAULT 0 " +
            ");";

    public static void create(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
}

    public static void upgrade(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(CREATE_TABLE);
    }
}
