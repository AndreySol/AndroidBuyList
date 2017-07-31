package com.buylist.solomakha.buylistapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.buylist.solomakha.buylistapp.db.dao.BasketDao;
import com.buylist.solomakha.buylistapp.db.dao.CategoryDao;
import com.buylist.solomakha.buylistapp.db.model.Basket;
import com.buylist.solomakha.buylistapp.db.model.BasketProduct;
import com.buylist.solomakha.buylistapp.db.model.Category;
import com.buylist.solomakha.buylistapp.db.model.Product;
import com.buylist.solomakha.buylistapp.db.model.Unit;


@Database(entities = {Basket.class, BasketProduct.class, Category.class, Product.class, Unit.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "buy-database").build();
        }
        return INSTANCE;
    }

    public abstract BasketDao basketDao();
    public abstract CategoryDao categoryDao();
}
