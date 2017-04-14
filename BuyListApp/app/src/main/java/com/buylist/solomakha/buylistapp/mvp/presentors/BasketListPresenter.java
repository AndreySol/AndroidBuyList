package com.buylist.solomakha.buylistapp.mvp.presentors;

import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;

/**
 * Created by asolomakha on 3/22/2017.
 */

public interface BasketListPresenter
{
    void loadBasketList();

    void deleteBasket(long id);

    void editBasket(Basket pl);

    void fillDbWithTestValues();

    void createBasket(String basketName);
}
