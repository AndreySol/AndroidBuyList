package com.buylist.solomakha.buylistapp.bind;

import android.databinding.BaseObservable;

import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;

/**
 * Created by asolomakha on 3/30/2017.
 */

public class BasketViewModel extends BaseObservable
{
    private Basket basket;

    public BasketViewModel(Basket basket)
    {
        this.basket = basket;
    }

    public long getId()
    {
        return basket.getId();
    }

    public void setId(long id)
    {
        basket.setId(id);
    }

    public String getName()
    {
        return basket.getName();
    }

    public void setName(String name)
    {
        basket.setName(name);
    }

    public Basket getBasket()
    {
        return basket;
    }
}
