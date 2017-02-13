package com.buylist.solomakha.buylistapp.storage.database.entities;

import android.text.TextUtils;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class Product extends BaseEntity
{

    private boolean priority;
    private float quantity;
    private String image;
    private Unit unit;
    private Category category;
    private boolean bought;

    public Product()
    {
    }

    public Product(int id, String name, boolean priority, float quantity, String image, Unit unit, Category category, boolean bought)
    {
        setId(id);
        setName(name);
        this.priority = priority;
        this.quantity = quantity;
        this.image = image;
        this.unit = unit;
        this.category = category;
        this.bought = bought;
    }

    public boolean isPriority()
    {
        return priority;
    }

    public void setPriority(boolean priority)
    {
        this.priority = priority;
    }

    public float getQuantity()
    {
        return quantity;
    }

    public void setQuantity(float quantity)
    {
        this.quantity = quantity;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public Unit getUnit()
    {
        return unit;
    }

    public void setUnit(Unit unit)
    {
        this.unit = unit;
    }

    public boolean isBought()
    {
        return bought;
    }

    public void setBought(boolean bought)
    {
        this.bought = bought;
    }

    @Override
    public boolean equals(Object productObj)
    {
        boolean result = false;
        if (this == productObj)
        {
            return true;
        }
        else if (productObj instanceof Product)
        {
            Product product = (Product) productObj;
            if (getId() == product.getId()
                    && getQuantity() == product.getQuantity()
                    && isPriority() == product.isPriority()
                    && isBought() == product.isBought()
                    && getName().equals(product.getName())
                    && getCategory().equals(product.getCategory())
                    && getUnit().equals(product.getUnit()))
            {
                result = true;
            }
            else
            {
                return false;
            }

            if (!TextUtils.equals(getImage(), product.getImage()))
            {
                return false;
            }
            else
            {
                result = true;
            }
        }
        return result;
    }
}
