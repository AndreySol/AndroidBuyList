package com.buylist.solomakha.buylistapp.storage.database.entities;

import android.text.TextUtils;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class Category extends BaseEntity {
    public Category(){}

    public Category(String name) {
        setName(name);
    }

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
            return true;
        }
        else if (categoryObj instanceof Category)
        {
            Category category = (Category) categoryObj;
            if (!TextUtils.equals(getName(), category.getName()))
            {
                return false;
            }
            if (getId() == category.getId())
            {
                result = true;
            }
        }
        return result;
    }
}
