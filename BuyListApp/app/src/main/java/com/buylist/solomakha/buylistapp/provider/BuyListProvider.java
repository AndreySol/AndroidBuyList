package com.buylist.solomakha.buylistapp.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.buylist.solomakha.buylistapp.storage.database.DataBaseHelper;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class BuyListProvider extends ContentProvider {

    private DataBaseHelper database;

    private static final int ITEMS = 10;
    private static final int ITEM_ID = 20;

    private static final String AUTHORITY = "buylistprovider";

    private static final String PRODUCT_PATH = "prodacts";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PRODUCT_PATH);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/items";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/item";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, PRODUCT_PATH, ITEMS);
        sURIMatcher.addURI(AUTHORITY, PRODUCT_PATH + "/#", ITEM_ID);
    }

    @Override
    public boolean onCreate()
    {
        database = new DataBaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri)
    {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        return 0;
    }
}
