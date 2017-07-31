package com.buylist.solomakha.buylistapp.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Product
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private boolean priority;
    private float quantity;
    private String image;
    @ForeignKey(entity = Unit.class, childColumns = "unitId", parentColumns = "id")
    private int unitId;
    @ForeignKey(entity = Category.class, childColumns = "categoryId", parentColumns = "id")
    private int categoryId;
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

    public int getUnitId()
    {
        return unitId;
    }

    public void setUnitId(int unitId)
    {
        this.unitId = unitId;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
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
