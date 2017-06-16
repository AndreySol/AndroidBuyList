package com.buylist.solomakha.buylistapp.storage.database.entities;

import android.text.TextUtils;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class Basket extends BaseEntity
{
    public Basket()
    {
    }

    public Basket(Basket basket)
    {
        setId(basket.getId());
        setName(basket.getName());
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
            return true;
        }
        else if (basketObj instanceof Basket)
        {
            Basket basket = (Basket) basketObj;
            if (!TextUtils.equals(getName(), basket.getName()))
            {
                return false;
            }
            if (getId() == basket.getId())
            {
                result = true;
            }
        }
        return result;
    }
}
