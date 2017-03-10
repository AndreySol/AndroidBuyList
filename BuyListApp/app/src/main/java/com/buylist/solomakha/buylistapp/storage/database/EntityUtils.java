package com.buylist.solomakha.buylistapp.storage.database;

import com.buylist.solomakha.buylistapp.storage.database.entities.BaseEntity;

/**
 * Created by asolomakha on 3/9/2017.
 */

public class EntityUtils
{
    public static boolean compareBaseEntities(BaseEntity entity1, BaseEntity entity2)
    {
        if (entity1 == entity2)
        {
            return true;
        }
        if (entity1 == null || entity2 == null)
        {
            return false;
        }
        return entity1.equals(entity2);
    }

}
