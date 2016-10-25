package com.buylist.solomakha.buylistapp.storage.database.dal;

import android.content.Context;

import com.buylist.solomakha.buylistapp.storage.database.entities.Category;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Created by asolomakha on 8/8/2016.
 */
@RunWith(RobolectricTestRunner.class)
public class CategoryTest
{
    private Context context = RuntimeEnvironment.application.getBaseContext();

    @Test
    public void testCategoryEquals()
    {
        Category category1 = new Category(0, "Test");
        Category category2 = new Category(0, "Test");

        Category category3 = new Category(1, "Test");
        Category category4 = new Category(0, "Test1");

        assertEquals(category1, category2);
        assertFalse(category1.equals(category3));
        assertFalse(category1.equals(category4));
    }

    @Test
    public void testGetCategories()
    {
        Category category1 = DataBase.getInstance(context).createCategory("My first category");
        Category category2 = DataBase.getInstance(context).createCategory("My second category");
        Category category3 = DataBase.getInstance(context).createCategory("My third category");

        List<Category> categories = DataBase.getInstance(context).getCategories();

        assertEquals(categories.size(), 3);

        assertEquals(categories.get(0).getName(), "My first category");
        assertEquals(categories.get(1).getName(), "My second category");
        assertEquals(categories.get(2).getName(), "My third category");

        assertEquals(category1, categories.get(0));
        assertEquals(category2, categories.get(1));
        assertEquals(category3, categories.get(2));
    }
}

