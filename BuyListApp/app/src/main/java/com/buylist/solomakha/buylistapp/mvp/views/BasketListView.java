package com.buylist.solomakha.buylistapp.mvp.views;

import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;

import java.util.List;

/**
 * Created by asolomakha on 3/22/2017.
 */

public interface BasketListView
{
    void showBasketList(List<Basket> basketList);

    void showError(String errorMsg);

    void showMessage(String message);

    void showProductsByBasket(long basketId);

    void showProgress(boolean show);

    void showCreateBasketDialog();

    void showEditBasketDialog(Basket basket);
}
