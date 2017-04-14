package com.buylist.solomakha.buylistapp.storage.database.dal;

import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;
import com.buylist.solomakha.buylistapp.storage.database.entities.Category;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;

import com.buylist.solomakha.buylistapp.storage.database.entities.Unit;


import java.util.List;

/**
 * Created by asolomakha on 1/4/2016.
 */
public interface Storage
{
    Category createCategory(String categoryName);
    List<Category> getCategories();
    Category getCategoryById(int id);

    Unit createUnit(String unit);
    List<Unit> getUnits();

    Product createProduct(Product product);
    List<Product> getProducts();

    Basket createBasket(String basketName);
    int deleteBasket(long basketId);
    int editBasket(Basket basket);
    List<Basket> getBaskets();

    List<Product> getProductsFromBasket(long basketId);

    long assignProductToBasket(long basketId, long productId);

    long markProductAsBought(long basketId, long productId, boolean bought);
}
