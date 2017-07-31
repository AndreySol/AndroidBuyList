package com.buylist.solomakha.buylistapp.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class BasketProduct
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    @ForeignKey(entity = Basket.class, childColumns = "basketId", parentColumns = "id")
    private int basketId;
    @ForeignKey(entity = Product.class, childColumns = "productId", parentColumns = "id")
    private int productId;
    private boolean bought;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getBasketId()
    {
        return basketId;
    }

    public void setBasketId(int basketId)
    {
        this.basketId = basketId;
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public boolean isBought()
    {
        return bought;
    }

    public void setBought(boolean bought)
    {
        this.bought = bought;
    }
}
