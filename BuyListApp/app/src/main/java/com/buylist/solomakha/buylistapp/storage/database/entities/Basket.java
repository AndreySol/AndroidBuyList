package com.buylist.solomakha.buylistapp.storage.database.entities;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class Basket extends BaseEntity
{
    public Basket()
    {
    }

    public Basket(long id, String name)
    {
        setId(id);
        setName(name);
    }

    @Override
    public String toString()
    {
        return getName();
    }

    @Override
    public boolean equals(Object basketObj)
    {
        boolean result = false;
        if (this == basketObj)
        {
            result = true;
        }
        else if (basketObj instanceof Basket)
        {
            Basket basket = (Basket) basketObj;
            if (getId() == basket.getId() && getName().equals(basket.getName()))
            {
                result = true;
            }
        }
        return result;
    }
}
