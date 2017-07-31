package com.buylist.solomakha.buylistapp.mvp.presentors;

import com.buylist.solomakha.buylistapp.mvp.views.BasketListView;
import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;

/**
 * Created by asolomakha on 3/22/2017.
 */

public interface BasketListPresenter
{
    void setView(BasketListView view);

    void loadBasketList(boolean showProgress);

    void deleteBasket(long id, boolean showProgress);

    void editBasket(Basket basket, boolean showProgress);

    void fillDbWithTestValues(boolean showProgress);

    void createBasket(String basketName, boolean showProgress);

    void openProductsInBasket(long basketId);

    void openCreateBasketDialog();

    void openEditBasketDialog(Basket basket);
}
