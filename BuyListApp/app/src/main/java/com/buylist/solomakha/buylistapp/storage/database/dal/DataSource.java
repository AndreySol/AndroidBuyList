package com.buylist.solomakha.buylistapp.storage.database.dal;

import com.buylist.solomakha.buylistapp.storage.database.entities.Product;

/**
 * Created by asolomakha on 1/4/2016.
 */
public interface DataSource
{
    long createGroup(String groupName);

    long createProduct(Product product);
}
