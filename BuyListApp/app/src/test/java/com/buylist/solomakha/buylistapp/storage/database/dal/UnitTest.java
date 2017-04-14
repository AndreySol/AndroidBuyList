package com.buylist.solomakha.buylistapp.storage.database.dal;

import android.content.Context;
import android.util.SparseArray;

import com.buylist.solomakha.buylistapp.storage.database.entities.Unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Created by asolomakha on 8/9/2016.
 */
@RunWith(RobolectricTestRunner.class)
public class UnitTest
{
    /*private Context context = RuntimeEnvironment.application.getBaseContext();

    @Test
    public void testUnitEquals()
    {
        SparseArray<Integer> test = new SparseArray<>();

        Unit unit1 = new Unit(0, "Test");
        Unit unit2 = new Unit(0, "Test");

        Unit unit3 = new Unit(1, "Test");
        Unit unit4 = new Unit(0, "Test1");

        assertEquals(unit1, unit2);
        assertFalse(unit1.equals(unit3));
        assertFalse(unit1.equals(unit4));
    }

    @Test
    public void testGetCategories()
    {
        Unit unit1 = DataBaseStorage.getInstance(context).createUnit("My first unit");
        Unit unit2 = DataBaseStorage.getInstance(context).createUnit("My second unit");
        Unit unit3 = DataBaseStorage.getInstance(context).createUnit("My third unit");

        List<Unit> units = DataBaseStorage.getInstance(context).getUnits();

        assertEquals(units.size(), 3);

        assertEquals(units.get(0).getName(), "My first unit");
        assertEquals(units.get(1).getName(), "My second unit");
        assertEquals(units.get(2).getName(), "My third unit");

        assertEquals(unit1, units.get(0));
        assertEquals(unit2, units.get(1));
        assertEquals(unit3, units.get(2));
    }*/
}
