package com.buylist.solomakha.buylistapp.storage.database.dal;

import android.content.Context;

import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by asolomakha on 3/10/2017.
 */

@RunWith(RobolectricTestRunner.class)
public class BasketsProductsTest
{
    private Context context = RuntimeEnvironment.application.getBaseContext();

    @Test
    public void testBasketProductsCreateAndGet()
    {
        Storage store = null;//DataBaseStorage.getInstance(context);

        Basket basket = store.createBasket("Test basket");

        Product product1 = store.createProduct(new Product("Test product1"));
        Product product2 = store.createProduct(new Product("Test product2"));
        Product product3 = store.createProduct(new Product("Test product3"));

        store.assignProductToBasket(basket.getId(), product1.getId());
        store.assignProductToBasket(basket.getId(), product2.getId());
        store.assignProductToBasket(basket.getId(), product3.getId());

        List<Product> products = store.getProductsFromBasket(basket.getId());

        assertThat(products.size(), equalTo(3));
    }

}
