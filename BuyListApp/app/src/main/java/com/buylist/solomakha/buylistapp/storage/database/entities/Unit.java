package com.buylist.solomakha.buylistapp.storage.database.entities;

/**
 * Created by asolomakha on 1/3/2016.
 */
public class Unit extends BaseEntity
{
    public Unit() {}

    public Unit(long id, String name) {
        setId(id);
        setName(name);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object unitObj)
    {
        boolean result = false;
        if (this == unitObj)
        {
            result = true;
        }
        else if (unitObj instanceof Unit)
        {
            Unit unit = (Unit) unitObj;
            if (getId() == unit.getId() && getName().equals(unit.getName()))
            {
                result = true;
            }
        }
        return result;
    }
}
