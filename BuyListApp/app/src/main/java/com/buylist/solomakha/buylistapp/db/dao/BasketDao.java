package com.buylist.solomakha.buylistapp.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.buylist.solomakha.buylistapp.db.model.Basket;

import java.util.List;

@Dao
public interface BasketDao
{
    @Query("SELECT * FROM basket")
    LiveData<List<Basket>> getAll();

    @Query("SELECT * FROM basket WHERE id == :basketId")
    Basket getById(int basketId);

    @Insert
    void insert(Basket basket);

    @Delete
    void delete(Basket basket);
}
