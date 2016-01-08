package com.buylist.solomakha.buylistapp.storage.database.entities;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class Product extends BaseEntity
{

    private boolean priority;
    private float quantity;
    private String image;
    private String unit;
    private String category;

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

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String groupId)
    {
        this.category = groupId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unitId) {
        this.unit = unitId;
    }
}
