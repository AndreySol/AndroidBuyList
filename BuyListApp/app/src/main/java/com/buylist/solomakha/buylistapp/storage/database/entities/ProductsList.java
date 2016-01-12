package com.buylist.solomakha.buylistapp.storage.database.entities;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class ProductsList extends BaseEntity
{
    public ProductsList(){}

    public ProductsList(long id, String name) {
        setId(id);
        setName(name);
    }

    @Override
    public String toString()
    {
        return getName();
    }
}
