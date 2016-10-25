package com.buylist.solomakha.buylistapp.storage.database.dal;

import android.app.IntentService;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.Toast;

import com.buylist.solomakha.buylistapp.storage.database.entities.Product;

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

    @Test
    public void testProductEquals()
    {
       /* Product product1 = new Product(0, "Test", false, 1, null, null, null, false);
        Product product2 = new Product(0, "Test");

        Product product3 = new Product(1, "Test");
        Product product4 = new Product(0, "Test1");

        assertEquals(product1, product2);
        assertFalse(product1.equals(product3));
        assertFalse(product1.equals(product4));*/
    }
}
