package com.buylist.solomakha.buylistapp.storage.database.entities;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class Product extends BaseEntity
{

    private boolean priority;
    private float quantity;
    private String image;
    private long unitId;
    private long categoryId;

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

    public long getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(long groupId)
    {
        this.categoryId = groupId;
    }

    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }
}
