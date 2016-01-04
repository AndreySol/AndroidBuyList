package com.buylist.solomakha.buylistapp.storage.database.tables;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by mariy on 1/4/2016.
 */
public class Categories_ProductsTable {
    public final static String TABLE_NAME = "CategoriesProducts";
    public final static String COLUMN_ID = "Id";
    public final static String COLUMN_CATEGORY_ID = "GroupId";
    public final static String COLUMN_PRODUCT_ID = "ProductId";
    private final static String CREATE_TABLE = " create table " +
            TABLE_NAME + "( " +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_CATEGORY_ID + " integer, " +
            COLUMN_PRODUCT_ID + " integer " +
            ");";

    public static void create(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    public static void upgrade(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(CREATE_TABLE);
    }
}
