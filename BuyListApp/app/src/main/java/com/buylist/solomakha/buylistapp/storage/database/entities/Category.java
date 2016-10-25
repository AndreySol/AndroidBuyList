package com.buylist.solomakha.buylistapp.storage.database.entities;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class Category extends BaseEntity {
    public Category(){}

    public Category(long id, String name) {
        setId(id);
        setName(name);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object categoryObj)
    {
        boolean result = false;
        if (this == categoryObj)
        {
            result = true;
        }
        else if (categoryObj instanceof Category)
        {
            Category category = (Category) categoryObj;
            if (getId() == category.getId() && getName().equals(category.getName()))
            {
                result = true;
            }
        }
        return result;
    }
}
