package com.buylist.solomakha.buylistapp.mvp.models;

import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;

import java.util.List;

import rx.Single;

/**
 * Created by asolomakha on 3/22/2017.
 */

public interface BasketListModel
{
    Single<List<Basket>> getBasketList();

    long deleteBasket(long id);

    int editBasket(Basket basket);

    void createTestValues();

    Basket createBasket(String basketName);
}
