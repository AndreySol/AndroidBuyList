package com.buylist.solomakha.buylistapp.storage.database.dal;

import android.content.Context;

import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;

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
public class BasketTest
{
    private Context context = RuntimeEnvironment.application.getBaseContext();

    @Test
    public void testBasketEquals()
    {
        Basket basket1 = new Basket(0, "Test");
        Basket basket2 = new Basket(0, "Test");

        Basket basket3 = new Basket(1, "Test");
        Basket basket4 = new Basket(0, "Test1");

        assertEquals(basket1, basket2);
        assertFalse(basket1.equals(basket3));
        assertFalse(basket1.equals(basket4));
    }

    @Test
    public void testGetBaskets()
    {
        Basket basket1 = DataBaseStorage.getInstance(context).createBasket("My first basket");
        Basket basket2 = DataBaseStorage.getInstance(context).createBasket("My second basket");
        Basket basket3 = DataBaseStorage.getInstance(context).createBasket("My third basket");

        List<Basket> baskets = DataBaseStorage.getInstance(context).getBaskets();

        assertEquals(baskets.size(), 3);

        assertEquals(baskets.get(0).getName(), "My first basket");
        assertEquals(baskets.get(1).getName(), "My second basket");
        assertEquals(baskets.get(2).getName(), "My third basket");

        assertEquals(basket1, baskets.get(0));
        assertEquals(basket2, baskets.get(1));
        assertEquals(basket3, baskets.get(2));
    }

    @Test
    public void testCreateBaskets()
    {
        DataBaseStorage.getInstance(context).createBasket("My first basket");
        DataBaseStorage.getInstance(context).createBasket("My second basket");
        List<Basket> baskets = DataBaseStorage.getInstance(context).getBaskets();
        assertEquals(baskets.get(0).getName(), "My first basket");
        assertEquals(baskets.get(1).getName(), "My second basket");
    }

    @Test
    public void testCreateNullBasket()
    {
        DataBaseStorage.getInstance(context).createBasket(null);
        List<Basket> baskets = DataBaseStorage.getInstance(context).getBaskets();
        assertEquals(baskets.size(), 0);
    }

    @Test
    public void testCreateDuplicateBasket()
    {
        DataBaseStorage.getInstance(context).createBasket("My first basket");
        DataBaseStorage.getInstance(context).createBasket("My first basket");
        List<Basket> baskets = DataBaseStorage.getInstance(context).getBaskets();
        assertEquals(baskets.size(), 1);
    }

    @Test
    public void testDeleteBasket()
    {
        Basket basket = DataBaseStorage.getInstance(context).createBasket("My first basket");
        int deletedRowsNumber = DataBaseStorage.getInstance(context).deleteBasket(basket.getId());
        assertEquals(deletedRowsNumber, 1);
        assertEquals(DataBaseStorage.getInstance(context).getBaskets().size(), 0);
    }

    @Test
    public void testDeleteDoesNotExistBasket()
    {
        Basket basket = DataBaseStorage.getInstance(context).createBasket("My first basket");
        DataBaseStorage.getInstance(context).deleteBasket(basket.getId());
        int deletedRowsNumber = DataBaseStorage.getInstance(context).deleteBasket(basket.getId());
        assertEquals(deletedRowsNumber, 0);
        assertEquals(DataBaseStorage.getInstance(context).getBaskets().size(), 0);
    }

    @Test
    public void testUpdateBasket()
    {
        Basket basket = DataBaseStorage.getInstance(context).createBasket("My first basket");
        basket.setName("My first updated basket");
        int updatedRowsNumber = DataBaseStorage.getInstance(context).updateBasket(basket);

        List<Basket> baskets = DataBaseStorage.getInstance(context).getBaskets();

        assertEquals(updatedRowsNumber, 1);
        assertEquals(baskets.size(), 1);
        assertEquals(baskets.get(0).getName(), "My first updated basket");
    }

    @Test
    public void testUpdateDoesNotExistBasket()
    {
        Basket basket = new Basket(11, "Test Basket");
        basket.setName("My first updated basket");
        int updatedRowsNumber = DataBaseStorage.getInstance(context).updateBasket(basket);

        assertEquals(updatedRowsNumber, 0);
    }
}
