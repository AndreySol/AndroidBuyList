package com.buylist.solomakha.buylistapp.mvp.models;

import com.buylist.solomakha.buylistapp.MainApp;
import com.buylist.solomakha.buylistapp.storage.database.dal.Storage;
import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;
import com.buylist.solomakha.buylistapp.storage.database.entities.Category;
import com.buylist.solomakha.buylistapp.storage.database.entities.Product;
import com.buylist.solomakha.buylistapp.storage.database.entities.Unit;

import java.util.List;

import javax.inject.Inject;

import rx.Single;

/**
 * Created by asolomakha on 3/22/2017.
 */

public class BasketListModelImpl implements BasketListModel
{
    @Inject
    Storage storage;

    public BasketListModelImpl()
    {
        MainApp.getComponent().inject(this);
    }

    @Override
    public Single<List<Basket>> getBasketList()
    {
        return Single.just(storage.getBaskets());
    }

    @Override
    public long deleteBasket(long id)
    {
        return storage.deleteBasket(id);
    }

    @Override
    public int editBasket(Basket basket)
    {
        return storage.editBasket(basket);
    }

    @Override
    public void createTestValues()
    {
        Category categoryOthers = storage.createCategory("Others");
        Category categoryDairy = storage.createCategory("Dairy");
        Category categoryMeat = storage.createCategory("Meat");
        Category categoryFruits = storage.createCategory("Fruit");
        Category categoryAppliances = storage.createCategory("Appliances");

        Unit unitKg = storage.createUnit("kg");
        Unit unitL = storage.createUnit("L.");
        Unit unitPcs = storage.createUnit("pcs");

        Basket listPostProducts = storage.createBasket("PostProducts");
        Basket listPostClothes = storage.createBasket("PostClothes");
        Basket listPostAppliances = storage.createBasket("PostAppliances");

        //ПРОДУКТ:
        //продукты  привязка к UNIT and CATEGORY
        //Apple - не внесен  в список
        Product product = new Product();
        product.setName("Apple");
        product.setCategory(categoryFruits);
        product.setPriority(false);
        product.setQuantity(2);
        product.setUnit(unitKg);
        Product productId = storage.createProduct(product);
        storage.assignProductToBasket(listPostProducts.getId(), productId.getId());//связка  со списком

        // Kettle - не внесен  в список
        Product product1 = new Product();
        product1.setName("Kettle");
        product1.setCategory(categoryAppliances);
        product1.setPriority(false);
        product1.setQuantity(1);
        product1.setUnit(unitPcs);
        Product productId1 = storage.createProduct(product1);
        storage.assignProductToBasket(listPostProducts.getId(), productId1.getId());//связка  со списком
        storage.assignProductToBasket(listPostClothes.getId(), productId1.getId());//связка  со списком

        // Banana -  внесен  в список
        Product product2 = new Product();
        product2.setName("Banana");
        product2.setCategory(categoryFruits);
        product2.setPriority(false);
        product2.setQuantity(1.5f);
        product2.setUnit(unitKg);
        Product productId2 = storage.createProduct(product2);
        storage.assignProductToBasket(listPostProducts.getId(), productId2.getId());//связка  со списком

        // Orange -  внесен  в список
        Product product3 = new Product();
        product3.setName("Orange");
        product3.setCategory(categoryFruits);
        product3.setPriority(false);
        product3.setQuantity(2.5f);
        product3.setUnit(unitKg);
        Product productId3 = storage.createProduct(product3);
        storage.assignProductToBasket(listPostProducts.getId(), productId3.getId());//связка  со списком
    }

    @Override
    public Basket createBasket(String basketName)
    {
        return storage.createBasket(basketName);
    }
}
