package com.buylist.solomakha.buylistapp.storage.database.dal;

import com.buylist.solomakha.buylistapp.storage.database.entities.Product;

/**
 * Created by asolomakha on 1/4/2016.
 */
public interface DataSource
{
    long createCategory(String categoryName);

    long createProduct(Product product);
}
