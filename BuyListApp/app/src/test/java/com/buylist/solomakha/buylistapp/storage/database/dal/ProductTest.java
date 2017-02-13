package com.buylist.solomakha.buylistapp.storage.database.dal;

import android.app.IntentService;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.Toast;

import com.buylist.solomakha.buylistapp.storage.database.entities.Category;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.storage.database.entities.Unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Created by asolomakha on 8/9/2016.
 */
@RunWith(RobolectricTestRunner.class)
public class ProductTest
{
    private Context context = RuntimeEnvironment.application.getBaseContext();

    @Test
    public void testProductEquals()
    {
        Unit unit = new Unit(0, "kg");
        Category category1 = new Category(0, "Fruits");
        Category category2 = new Category(0, "Meat");

        Product product1 = new Product(0, "Test", false, 1, null, unit, category1, false);
        Product product2 = new Product(0, "Test", false, 1, null, unit, category1, false);
        Product product3 = new Product(0, "Test", false, 1, "Not null image", unit, category1, false);
        Product product4 = new Product(1, "Test", false, 1, "Not null image", unit, category1, false);
        Product product5 = new Product(1, "Test", false, 1, "Not null image", unit, category2, false);

        assertEquals(product1, product2);
        assertFalse(product1.equals(product3));
        assertFalse(product3.equals(product1));
        assertFalse(product1.equals(product4));
        assertFalse(product1.equals(product5));
    }

    void testCreateProduct()
    {
    }
}
