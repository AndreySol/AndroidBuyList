package com.buylist.solomakha.buylistapp.storage.database.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.buylist.solomakha.buylistapp.storage.database.DataBaseHelper;
import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;
import com.buylist.solomakha.buylistapp.storage.database.entities.BasketProduct;
import com.buylist.solomakha.buylistapp.storage.database.entities.Category;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.storage.database.entities.Unit;
import com.buylist.solomakha.buylistapp.storage.database.tables.BasketsProductsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.BasketsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.CategoriesTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.ProductsTable;
import com.buylist.solomakha.buylistapp.storage.database.tables.UnitsTable;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by asolomakha on 1/4/2016.
 */
public class DataBaseStorage implements Storage
{
    DataBaseHelper dbHelper;

    public DataBaseStorage(DataBaseHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    @Override
    public Category createCategory(String name)
    {
        Category category = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try
        {
            ContentValues values = new ContentValues();
            values.put(CategoriesTable.COLUMN_NAME, name);

            long id = db.insert(CategoriesTable.TABLE_NAME, null, values);
            if (id > -1)
            {
                category = new Category(id, name);
            }
        }
        finally
        {
            dbHelper.close();
        }
        return category;
    }

    @Override
    public List<Category> getCategories()
    {
        List<Category> categoryList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try
        {
            cursor = db.query(CategoriesTable.TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst())
            {
                do
                {
                    Category category = new Category();
                    category.setId(cursor.getInt(cursor.getColumnIndex(BasketsTable.COLUMN_ID)));
                    category.setName(cursor.getString(cursor.getColumnIndex(BasketsTable.COLUMN_NAME)));
                    categoryList.add(category);
                } while (cursor.moveToNext());
            }
        }
        finally
        {
            releaseDBConnection(cursor);
        }
        return categoryList;
    }

    @Override
    public Category getCategoryById(int id)
    {
        Category category = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try
        {
            cursor = db.query(CategoriesTable.TABLE_NAME, null, CategoriesTable.COLUMN_ID + "=" + id, null, null, null, null);
            if (cursor != null && cursor.moveToFirst())
            {
                category = new Category();
                category.setId(cursor.getInt(cursor.getColumnIndex(BasketsTable.COLUMN_ID)));
                category.setName(cursor.getString(cursor.getColumnIndex(BasketsTable.COLUMN_NAME)));
            }
        }
        finally
        {
            releaseDBConnection(cursor);
        }
        return category;
    }

    @Override
    public Unit createUnit(String name)
    {
        Unit unit = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try
        {
            ContentValues values = new ContentValues();
            values.put(UnitsTable.COLUMN_NAME, name);

            long id = db.insert(UnitsTable.TABLE_NAME, null, values);
            if (id > -1)
            {
                unit = new Unit(id, name);
            }
        }
        finally
        {
            dbHelper.close();
        }
        return unit;
    }

    @Override
    public List<Unit> getUnits()
    {
        List<Unit> unitList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try
        {
            cursor = db.query(UnitsTable.TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst())
            {
                do
                {
                    Unit unit = new Unit();
                    unit.setId(cursor.getInt(cursor.getColumnIndex(BasketsTable.COLUMN_ID)));
                    unit.setName(cursor.getString(cursor.getColumnIndex(BasketsTable.COLUMN_NAME)));
                    unitList.add(unit);
                } while (cursor.moveToNext());
            }
        }
        finally
        {
            releaseDBConnection(cursor);
        }
        return unitList;
    }

    public Unit getUnitById(int id)
    {
        Unit unit = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try
        {
            cursor = db.query(UnitsTable.TABLE_NAME, null, UnitsTable.COLUMN_ID + " = " + id, null, null, null, null);
            if (cursor != null && cursor.moveToFirst())
            {
                unit = new Unit();
                unit.setId(cursor.getInt(cursor.getColumnIndex(BasketsTable.COLUMN_ID)));
                unit.setName(cursor.getString(cursor.getColumnIndex(BasketsTable.COLUMN_NAME)));
            }
        }
        finally
        {
            releaseDBConnection(cursor);
        }
        return unit;
    }

    @Override
    public Product createProduct(Product product)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try
        {
            ContentValues productValues = new ContentValues();
            productValues.put(ProductsTable.COLUMN_NAME, product.getName());
            productValues.put(ProductsTable.COLUMN_PRIORITY, product.isPriority());
            productValues.put(ProductsTable.COLUMN_QUANTITY, product.getQuantity());
            productValues.put(ProductsTable.COLUMN_IMAGE, product.getImage());
            productValues.put(ProductsTable.COLUMN_UNIT_ID, product.getUnit() == null ? null : product.getUnit().getId());
            productValues.put(ProductsTable.COLUMN_CATEGORY_ID, product.getCategory() == null ? null : product.getCategory().getId());
            long id = db.insert(ProductsTable.TABLE_NAME, null, productValues);
            if (id > -1)
            {
                product.setId(id);
            }
            else
            {
                product = null;
            }
        }
        finally
        {
            dbHelper.close();
        }
        return product;
    }

    @Override
    public Basket createBasket(String name)
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        Basket basket = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try
        {
            ContentValues listValues = new ContentValues();
            listValues.put(BasketsTable.COLUMN_NAME, name);
            long id = db.insert(BasketsTable.TABLE_NAME, null, listValues);
            if (id > -1)
            {
                basket = new Basket(id, name);
            }
        }
        finally
        {
            dbHelper.close();
        }
        return basket;
    }

    @Override
    public List<Basket> getBaskets()
    {
        List<Basket> basketList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try
        {
            cursor = db.query(BasketsTable.TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst())
            {
                do
                {
                    Basket pl = new Basket();
                    pl.setId(cursor.getInt(cursor.getColumnIndex(BasketsTable.COLUMN_ID)));
                    pl.setName(cursor.getString(cursor.getColumnIndex(BasketsTable.COLUMN_NAME)));
                    basketList.add(pl);
                } while (cursor.moveToNext());

            }
        }
        finally
        {
            releaseDBConnection(cursor);
        }
        return basketList;
    }

    @Override
    public int editBasket(Basket basket)
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        int updatedRowsNumber = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try
        {
            ContentValues listValues = new ContentValues();
            listValues.put(BasketsTable.COLUMN_NAME, basket.getName());
            updatedRowsNumber = db.update(BasketsTable.TABLE_NAME, listValues, BasketsTable.COLUMN_ID + " = " + basket.getId(), null);
        }
        finally
        {
            dbHelper.close();
        }
        return updatedRowsNumber;
    }

    @Override
    public int deleteBasket(long basketId)
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        int deletedRowsNumber = 0;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try
        {
            deletedRowsNumber = db.delete(BasketsTable.TABLE_NAME, BasketsTable.COLUMN_ID + " = " + basketId, null);
        }
        finally
        {
            dbHelper.close();
        }
        return deletedRowsNumber;
    }

    @Override
    public long assignProductToBasket(long basketId, long productId)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long createdId = -1;
        try
        {
            ContentValues listValues = new ContentValues();
            listValues.put(BasketsProductsTable.COLUMN_BASKET_ID, basketId);
            listValues.put(BasketsProductsTable.COLUMN_PRODUCT_ID, productId);
            createdId = db.insert(BasketsProductsTable.TABLE_NAME, null, listValues);
        }
        finally
        {
            dbHelper.close();
        }
        return createdId;
    }

    public List<Product> getProducts()
    {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        try
        {
            cursor = db.query(ProductsTable.TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst())
            {
                do
                {
                    Product product = new Product();
                    product.setId(cursor.getLong(cursor.getColumnIndex(ProductsTable.COLUMN_ID)));
                    product.setName(cursor.getString(cursor.getColumnIndex(ProductsTable.COLUMN_NAME)));
                    int priority = cursor.getInt(cursor.getColumnIndex(ProductsTable.COLUMN_PRIORITY));
                    product.setPriority(priority > 0);
                    product.setQuantity(cursor.getInt(cursor.getColumnIndex(ProductsTable.COLUMN_QUANTITY)));
                    product.setImage(cursor.getString(cursor.getColumnIndex(ProductsTable.COLUMN_IMAGE)));
                    product.setCategory(getCategoryById(cursor.getInt(cursor.getColumnIndex(ProductsTable.COLUMN_CATEGORY_ID))));
                    product.setUnit(getUnitById(cursor.getInt(cursor.getColumnIndex(ProductsTable.COLUMN_UNIT_ID))));
                    product.setBought(false);
                    productList.add(product);
                } while (cursor.moveToNext());
            }
        }
        finally
        {
            releaseDBConnection(cursor);
        }
        return productList;
    }


    public List<Product> getProductsFromBasket(long basketId)
    {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Map<Long, BasketProduct> basketProductMap = new Hashtable<>();
        Cursor cursor = null;
        try
        {
            cursor = db.query(BasketsProductsTable.TABLE_NAME, null, BasketsProductsTable.COLUMN_BASKET_ID + " = " + basketId, null, null, null, null);
            if (cursor != null && cursor.moveToFirst())
            {
                do
                {
                    BasketProduct basketProduct = new BasketProduct();
                    basketProduct.setBought(cursor.getInt(cursor.getColumnIndex(BasketsProductsTable.COLUMN_BOUGHT)) > 0);
                    basketProductMap.put(cursor.getLong(cursor.getColumnIndex(BasketsProductsTable.COLUMN_PRODUCT_ID)), basketProduct);
                } while (cursor.moveToNext());
            }
            if (!basketProductMap.isEmpty())
            {
                cursor = db.query(ProductsTable.TABLE_NAME, null, ProductsTable.COLUMN_ID + String.format(" IN (" + TextUtils.join(", ", basketProductMap.keySet()) + ")"), null, null, null, null);
                if (cursor != null && cursor.moveToFirst())
                {
                    do
                    {
                        Product product = new Product();
                        product.setId(cursor.getLong(cursor.getColumnIndex(ProductsTable.COLUMN_ID)));
                        product.setName(cursor.getString(cursor.getColumnIndex(ProductsTable.COLUMN_NAME)));
                        int priority = cursor.getInt(cursor.getColumnIndex(ProductsTable.COLUMN_PRIORITY));
                        product.setPriority(priority > 0);
                        product.setQuantity(cursor.getInt(cursor.getColumnIndex(ProductsTable.COLUMN_QUANTITY)));
                        product.setImage(cursor.getString(cursor.getColumnIndex(ProductsTable.COLUMN_IMAGE)));
                        product.setCategory(getCategoryById(cursor.getInt(cursor.getColumnIndex(ProductsTable.COLUMN_CATEGORY_ID))));
                        product.setUnit(getUnitById(cursor.getInt(cursor.getColumnIndex(ProductsTable.COLUMN_UNIT_ID))));
                        product.setBought(basketProductMap.get(cursor.getLong(cursor.getColumnIndex(ProductsTable.COLUMN_ID))).isBought());
                        productList.add(product);
                    } while (cursor.moveToNext());
                }
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            releaseDBConnection(cursor);
        }
        return productList;
    }

    @Override
    public long markProductAsBought(long basketId, long productId, boolean bought)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long updatedRowsNumber = -1;
        try
        {
            ContentValues listValues = new ContentValues();
            listValues.put(BasketsProductsTable.COLUMN_BOUGHT, bought ? 1 : 0);
            String query = BasketsProductsTable.COLUMN_BASKET_ID + " = " + basketId + " AND " + BasketsProductsTable.COLUMN_PRODUCT_ID + " = " + productId;
            updatedRowsNumber = db.update(BasketsProductsTable.TABLE_NAME, listValues, query, null);
        }
        finally
        {
            dbHelper.close();
        }
        return updatedRowsNumber;
    }

    private void releaseDBConnection(Cursor cursor)
    {
        if (cursor != null)
        {
            cursor.close();
        }
        dbHelper.close();
    }

}
