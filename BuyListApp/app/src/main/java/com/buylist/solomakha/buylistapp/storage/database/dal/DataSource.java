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
public interface DataSource
{
    Category createCategory(String categoryName);
    List<Category> getCategories();
    Category getCategoryById(int id);

    Unit createUnit(String unit);

    Product createProduct(Product product);

    Basket createBasket(String product);
    int deleteBasket(long basketId);
    int updateBasket(Basket basket);
    List<Basket> getBaskets();

    List<Product> getProductsFromBasket(long basketId);

    long assignProductToBasket(long basketId, long productId);

    void markProductAsBought(long basketId, long productId, boolean bought);
}
