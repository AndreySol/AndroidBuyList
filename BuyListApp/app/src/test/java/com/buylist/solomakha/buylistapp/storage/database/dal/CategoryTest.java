package com.buylist.solomakha.buylistapp.storage.database.dal;

import android.content.Context;

import com.buylist.solomakha.buylistapp.storage.database.entities.Category;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;

/**
 * Created by asolomakha on 8/8/2016.
 */
@RunWith(RobolectricTestRunner.class)
public class CategoryTest
{
    /*private Context context = RuntimeEnvironment.application.getBaseContext();

    @Test
    public void testBasketEquals()
    {
        Category category1 = new Category(0, "Test");
        Category category2 = new Category(0, "Test");

        Category category3 = new Category(1, "Test");
        Category category4 = new Category(0, "Test1");

        Category category5 = new Category(0, null);
        Category category6 = new Category(1, null);
        Category category7 = new Category(0, null);

        assertEquals(category1, category2);
        assertEquals(category5, category7);
        assertNotSame(category1, category3);
        assertNotSame(category1, category4);
        assertNotSame(category1, category5);
        assertNotSame(category5, category6);
        assertNotSame(category1, category5);
    }

    @Test
    public void testCreateAndGetBaskets()
    {
        Category category1 = DataBaseStorage.getInstance(context).createCategory("My first createAndGetCategory");
        Category category2 = DataBaseStorage.getInstance(context).createCategory("My second createAndGetCategory");
        Category category3 = DataBaseStorage.getInstance(context).createCategory("My third createAndGetCategory");

        List<Category> categories = DataBaseStorage.getInstance(context).getCategories();

        assertEquals(categories.size(), 3);

        assertEquals(category1, categories.get(0));
        assertEquals(category2, categories.get(1));
        assertEquals(category3, categories.get(2));
    }*/
}

