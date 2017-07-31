package com.buylist.solomakha.buylistapp.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.buylist.solomakha.buylistapp.db.model.Category;

import java.util.List;

/**
 * Created by asolomakha on 7/31/2017.
 */

public interface CategoryDao
{
    @Query("SELECT * FROM category")
    LiveData<List<Category>> getAll();

    @Query("SELECT * FROM basket WHERE id == :categoryId")
    Category getById(int categoryId);

    @Insert
    void insert(Category category);

    @Delete
    void delete(Category category);
}
