package com.buylist.solomakha.buylistapp.storage.database.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.buylist.solomakha.buylistapp.storage.database.DataBaseHelper;
import com.buylist.solomakha.buylistapp.storage.database.entities.Category;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.storage.database.entities.ProductsList;
import com.buylist.solomakha.buylistapp.storage.database.entities.Unit;
import com.buylist.solomakha.buylistapp.storage.database.tables.CategoriesTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.ListsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.Lists_ProductsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.ProductsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.UnitsTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asolomakha on 1/4/2016.
 */
public class DBDataSource implements DataSource {


    private static DBDataSource dbDataSource;

    public static DBDataSource getIns(Context context) {
        if (dbDataSource == null) {
            return new DBDataSource(context);
        } else {
            return dbDataSource;
        }
    }

    private DataBaseHelper dbHelper;

    private DBDataSource(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    @Override
    public Category createCategory(String name) {
        Category category = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(CategoriesTable.COLUMN_NAME, name);

            long id = db.insert(CategoriesTable.TABLE_NAME, null, values);
            if (id > -1) {
                category = new Category(id, name);
            }
        } finally {
            dbHelper.close();
        }
        return category;
    }

    @Override
    public List<Category> getCategories() {
        List<Category> categoryListList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(CategoriesTable.TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Category category = new Category();
                        category.setId(cursor.getInt(cursor.getColumnIndex(ListsTable.COLUMN_ID)));
                        category.setName(cursor.getString(cursor.getColumnIndex(ListsTable.COLUMN_NAME)));
                        categoryListList.add(category);
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dbHelper.close();
        }
        return categoryListList;
    }

    @Override
    public Category getCategoryById(int id) {
        Category category = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(CategoriesTable.TABLE_NAME, null, CategoriesTable.COLUMN_ID + "=" + id, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    category = new Category();
                    category.setId(cursor.getInt(cursor.getColumnIndex(ListsTable.COLUMN_ID)));
                    category.setName(cursor.getString(cursor.getColumnIndex(ListsTable.COLUMN_NAME)));
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dbHelper.close();
        }
        return category;
    }

    @Override
    public Unit createUnit(String name) {
        Unit unit = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put(UnitsTable.COLUMN_NAME, name);

            long id = db.insert(UnitsTable.TABLE_NAME, null, values);
            if (id > -1) {
                unit = new Unit(id, name);
            }
        } finally {
            dbHelper.close();
        }
        return unit;
    }

    public List<Unit> getUnits() {
        List<Unit> unitList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(UnitsTable.TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Unit unit = new Unit();
                        unit.setId(cursor.getInt(cursor.getColumnIndex(ListsTable.COLUMN_ID)));
                        unit.setName(cursor.getString(cursor.getColumnIndex(ListsTable.COLUMN_NAME)));
                        unitList.add(unit);
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dbHelper.close();
        }
        return unitList;
    }

    public Unit getUnitById(int id) {
        Unit unit = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(UnitsTable.TABLE_NAME, null, UnitsTable.COLUMN_ID + " = " + id, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    unit = new Unit();
                    unit.setId(cursor.getInt(cursor.getColumnIndex(ListsTable.COLUMN_ID)));
                    unit.setName(cursor.getString(cursor.getColumnIndex(ListsTable.COLUMN_NAME)));
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dbHelper.close();
        }
        return unit;
    }

    @Override
    public Product createProduct(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            ContentValues productValues = new ContentValues();
            productValues.put(ProductsTable.COLUMN_NAME, product.getName());
            productValues.put(ProductsTable.COLUMN_PRIORITY, product.isPriority());
            productValues.put(ProductsTable.COLUMN_QUANTITY, product.getQuantity());
            productValues.put(ProductsTable.COLUMN_UNIT_ID, product.getUnit().getId());
            productValues.put(ProductsTable.COLUMN_CATEGORY_ID, product.getCategory().getId());
            long id = db.insert(ProductsTable.TABLE_NAME, null, productValues);
            if (id > -1) {
                product.setId(id);
            }
            else {
                product = null;
            }
        } finally {
            dbHelper.close();
        }
        return product;
    }

    @Override
    public ProductsList createList(String name) {
        ProductsList productsList = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            ContentValues listValues = new ContentValues();
            listValues.put(ListsTable.COLUMN_NAME, name);

            long id = db.insert(ListsTable.TABLE_NAME, null, listValues);
            if (id > -1) {
                productsList = new ProductsList(id, name);
            }
        } finally {
            dbHelper.close();
        }
        return productsList;
    }

    @Override
    public List<ProductsList> getLists() {
        List<ProductsList> productsLists = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(ListsTable.TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        ProductsList pl = new ProductsList();
                        pl.setId(cursor.getInt(cursor.getColumnIndex(ListsTable.COLUMN_ID)));
                        pl.setName(cursor.getString(cursor.getColumnIndex(ListsTable.COLUMN_NAME)));
                        productsLists.add(pl);
                    } while (cursor.moveToNext());
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dbHelper.close();
        }
        return productsLists;
    }

    @Override
    public int updateList(ProductsList pl) {
        int updatedRowsNumber = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            ContentValues listValues = new ContentValues();
            listValues.put(ListsTable.COLUMN_NAME, pl.getName());
            updatedRowsNumber = db.update(ListsTable.TABLE_NAME, listValues, ListsTable.COLUMN_ID + " = " + pl.getId(), null);
        } finally {
            dbHelper.close();
        }
        return updatedRowsNumber;
    }

    @Override
    public int deleteList(long listId) {
        int deletedRowsNumber = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            deletedRowsNumber = db.delete(ListsTable.TABLE_NAME, ListsTable.COLUMN_ID + " = " + listId, null);
        } finally {
            dbHelper.close();
        }
        return deletedRowsNumber;
    }

    @Override
    public long assignProductToList(long listId, long productId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long createdId = -1;
        try {
            ContentValues listValues = new ContentValues();
            listValues.put(Lists_ProductsTable.COLUMN_LIST_ID, listId);
            listValues.put(Lists_ProductsTable.COLUMN_PRODUCT_ID, productId);

            createdId = db.insert(Lists_ProductsTable.TABLE_NAME, null, listValues);
        } finally {
            dbHelper.close();
        }
        return createdId;
    }

    public List<Product> getProductsFromList(long listId) {
        List<Product> productLists = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<String> proructsIdList = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query(Lists_ProductsTable.TABLE_NAME, new String[]{Lists_ProductsTable.COLUMN_PRODUCT_ID}, Lists_ProductsTable.COLUMN_LIST_ID + " = " + listId, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        proructsIdList.add(cursor.getString(cursor.getColumnIndex(Lists_ProductsTable.COLUMN_PRODUCT_ID)));
                    } while (cursor.moveToNext());
                }
            }
            if (!proructsIdList.isEmpty()) {
                cursor = db.query(ProductsTable.TABLE_NAME, null, ProductsTable.COLUMN_ID + String.format(" IN (" + TextUtils.join(", ", proructsIdList) + ")"), null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        Product product = new Product();
                        product.setName(cursor.getString(cursor.getColumnIndex(ProductsTable.COLUMN_NAME)));
                        int priority = cursor.getInt(cursor.getColumnIndex(ProductsTable.COLUMN_PRIORITY));
                        product.setPriority(priority > 0 ? true : false);
                        product.setQuantity(cursor.getInt(cursor.getColumnIndex(ProductsTable.COLUMN_QUANTITY)));
                        product.setImage(cursor.getString(cursor.getColumnIndex(ProductsTable.COLUMN_IMAGE)));
                        product.setCategory(getCategoryById(cursor.getInt(cursor.getColumnIndex(ProductsTable.COLUMN_CATEGORY_ID))));
                        product.setUnit(getUnitById(cursor.getInt(cursor.getColumnIndex(ProductsTable.COLUMN_UNIT_ID))));
                        productLists.add(product);
                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            dbHelper.close();
        }
        return productLists;
    }
}
