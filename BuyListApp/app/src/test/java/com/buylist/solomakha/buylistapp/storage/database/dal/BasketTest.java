package com.buylist.solomakha.buylistapp.storage.database.dal;

import android.content.Context;

import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertNull;

/**
 * Created by asolomakha on 8/8/2016.
 */

@RunWith(RobolectricTestRunner.class)
public class BasketTest
{
    private Context context = RuntimeEnvironment.application.getBaseContext();

    /*@Test
    public void testBasketEquals()
    {
        Basket basket1 = new Basket(0, "Test");
        Basket basket2 = new Basket(0, "Test");

        Basket basket3 = new Basket(1, "Test");
        Basket basket4 = new Basket(0, "Test1");

        Basket basket5 = new Basket(0, null);
        Basket basket6 = new Basket(1, null);
        Basket basket7 = new Basket(0, null);

        assertEquals(basket1, basket2);
        assertEquals(basket5, basket7);
        assertNotSame(basket1, basket3);
        assertNotSame(basket1, basket4);
        assertNotSame(basket1, basket5);
        assertNotSame(basket5, basket6);
        assertNotSame(basket1, basket5);
    }

    @Test
    public void testCreateAndGetBaskets()
    {
        Basket basket1 = DataBaseStorage.getInstance(context).createBasket("My first createAndGetBasket");
        Basket basket2 = DataBaseStorage.getInstance(context).createBasket("My second createAndGetBasket");
        Basket basket3 = DataBaseStorage.getInstance(context).createBasket("My third createAndGetBasket");

        List<Basket> baskets = DataBaseStorage.getInstance(context).getBaskets();

        assertEquals(baskets.size(), 3);

        assertEquals(basket1, baskets.get(0));
        assertEquals(basket2, baskets.get(1));
        assertEquals(basket3, baskets.get(2));
    }

    @Test
    public void testCreateNullBasket()
    {
        Basket nullBasket = DataBaseStorage.getInstance(context).createBasket(null);
        List<Basket> baskets = DataBaseStorage.getInstance(context).getBaskets();

        assertNull(nullBasket);
        assertEquals(baskets.size(), 0);
    }

    @Test
    public void testCreateDuplicateBasket()
    {
        Basket createdBasket = DataBaseStorage.getInstance(context).createBasket("My first duplicateBasket");
        Basket duplicateBasket = DataBaseStorage.getInstance(context).createBasket("My first duplicateBasket");

        List<Basket> baskets = DataBaseStorage.getInstance(context).getBaskets();

        assertEquals(baskets.size(), 1);
        assertEquals(createdBasket, baskets.get(0));
        assertNull(duplicateBasket);
    }

    @Test
    public void testDeleteBasket()
    {
        Basket basket = DataBaseStorage.getInstance(context).createBasket("My first deleteBasket");
        Basket notDeletedBasket = DataBaseStorage.getInstance(context).createBasket("My second deleteBasket");

        int deletedRowsNumber = DataBaseStorage.getInstance(context).deleteBasket(basket.getId());

        assertEquals(deletedRowsNumber, 1);
        assertEquals(DataBaseStorage.getInstance(context).getBaskets().size(), 1);
        assertEquals(DataBaseStorage.getInstance(context).getBaskets().get(0), notDeletedBasket);
    }

    @Test
    public void testDeleteDoesNotExistBasket()
    {
        Basket doesNotExistBasket = new Basket(2, "My first doesNotExistBasket");
        Basket existBasket = DataBaseStorage.getInstance(context).createBasket("My second doesNotExistBasket");

        int deletedRowsNumber = DataBaseStorage.getInstance(context).deleteBasket(doesNotExistBasket.getId());

        assertEquals(deletedRowsNumber, 0);
        assertEquals(DataBaseStorage.getInstance(context).getBaskets().size(), 1);
        assertEquals(DataBaseStorage.getInstance(context).getBaskets().get(0), existBasket);
    }

    @Test
    public void testUpdateBasket()
    {
        Basket updatedBasket = DataBaseStorage.getInstance(context).createBasket("My first updatedBasket");
        updatedBasket.setName("My first updated updatedBasket");
        Basket basket = DataBaseStorage.getInstance(context).createBasket("My second updatedBasket");

        int updatedRowsNumber = DataBaseStorage.getInstance(context).editBasket(updatedBasket);
        List<Basket> baskets = DataBaseStorage.getInstance(context).getBaskets();

        assertEquals(updatedRowsNumber, 1);
        assertEquals(baskets.size(), 2);
        assertEquals(baskets.get(0), updatedBasket);
        assertEquals(baskets.get(1), basket);
    }

    @Test
    public void testUpdateDoesNotExistBasket()
    {
        Basket doesNotExistBasket = new Basket(11, "Test updateDoesNotExistBasket");
        int updatedRowsNumber = DataBaseStorage.getInstance(context).editBasket(doesNotExistBasket);

        assertEquals(updatedRowsNumber, 0);
    }*/
}
