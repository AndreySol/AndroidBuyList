package com.buylist.solomakha.buylistapp.storage.database.dal;

import android.content.Context;

import com.buylist.solomakha.buylistapp.storage.database.entities.Category;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.storage.database.entities.Unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by asolomakha on 8/9/2016.
 */
@RunWith(RobolectricTestRunner.class)
public class ProductTest
{
    /*private Context context = RuntimeEnvironment.application.getBaseContext();

    @Test
    public void testProductEquals()
    {
        Unit unit1 = new Unit(0, "kg");
        Unit unit2 = new Unit(2, "L");
        Category category1 = new Category(0, "Fruits");
        Category category2 = new Category(0, "Meat");

        Product product1 = new Product(0, "Test", false, 1, null, null, null, false);
        Product product2 = new Product(0, "Test", false, 1, null, null, null, false);

        Product product3 = new Product(0, "Test1", false, 1, null, null, null, false);
        Product product4 = new Product(0, "Test", true, 1, null, null, null, false);
        Product product5 = new Product(0, "Test", false, 2, null, null, null, false);
        Product product6 = new Product(0, "Test", false, 2, "Not null image", null, null, false);
        Product product7 = new Product(0, "Test", false, 2, null, unit1, null, false);
        Product product8 = new Product(0, "Test", false, 2, null, null, category1, false);
        Product product9 = new Product(0, "Test", false, 2, null, null, null, true);

        Product product10 = new Product(0, "Test1", true, 2, "Not null image", unit1, category1, true);
        Product product11 = new Product(0, "Test1", true, 2, "Not null image", unit1, category1, true);

        Product product12 = new Product(0, "Test1", true, 2, "Not null image", unit2, category1, true);
        Product product13 = new Product(0, "Test1", true, 2, "Not null image", unit1, category2, true);
        Product product14 = new Product(0, "Test1", true, 2, "Not null image 1", unit1, category1, true);

        assertThat(product1, is(product2));
        assertThat(product1, not(equalTo(product3)));
        assertThat(product1, not(equalTo(product4)));
        assertThat(product1, not(equalTo(product5)));
        assertThat(product1, not(equalTo(product6)));
        assertThat(product1, not(equalTo(product7)));
        assertThat(product1, not(equalTo(product8)));
        assertThat(product1, not(equalTo(product9)));
        assertThat(product10, is(product11));
        assertThat(product11, not(equalTo(product12)));
        assertThat(product11, not(equalTo(product13)));
        assertThat(product11, not(equalTo(product14)));
    }

    @Test
    public void testCreateAndGetProducts()
    {
        Unit unit = DataBaseStorage.getInstance(context).createUnit("Test unit");
        Category category = DataBaseStorage.getInstance(context).createCategory("Test category");

        Product createdProduct1 = DataBaseStorage.getInstance(context)
                .createProduct(new Product(-1, "productName", false, 0, null, null, null, false));

        Product createdProduct2 = DataBaseStorage.getInstance(context)
                .createProduct(new Product(-1, "productName", true, 3, "some image", unit, category, false));

        List<Product> products = DataBaseStorage.getInstance(context).getProducts();

        assertThat(products.size(), equalTo(2));
        assertThat(products.get(0), equalTo(createdProduct1));
        assertThat(products.get(1), equalTo(createdProduct2));
        assertThat(products.get(0), not(equalTo(products.get(1))));
    }

    @Test
    public void testCreateProductWithNullName()
    {
        Product createdProduct1 = DataBaseStorage.getInstance(context)
                .createProduct(new Product(-1, null, false, 0, null, null, null, false));

        assertThat(createdProduct1, nullValue());
    }*/

}
