package com.buylist.solomakha.buylistapp.storage.database.dal;

import com.buylist.solomakha.buylistapp.storage.database.entities.Category;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.storage.database.entities.ProductsList;
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
    ProductsList createList(String product);
    int deleteList(long listId);
    int updateList(ProductsList pl);

    long assignProductToList(long listId, long productId);

    List<ProductsList> getLists();
    List<Product> getProductsFromList(long listId);
}
