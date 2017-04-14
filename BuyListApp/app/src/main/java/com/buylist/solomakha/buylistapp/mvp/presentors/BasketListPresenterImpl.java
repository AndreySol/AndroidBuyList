package com.buylist.solomakha.buylistapp.mvp.presentors;

import android.util.Log;

import com.buylist.solomakha.buylistapp.MainApp;
import com.buylist.solomakha.buylistapp.mvp.models.BasketListModel;
import com.buylist.solomakha.buylistapp.mvp.views.BasketListView;
import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;

import java.util.List;

import javax.inject.Inject;

import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BasketListPresenterImpl implements BasketListPresenter
{
    @Inject
    BasketListModel basketListModel;

    private BasketListView basketListView;

    public BasketListPresenterImpl(BasketListView basketListView)
    {
        MainApp.getComponent().inject(this);
        this.basketListView = basketListView;
    }

    @Override
    public void loadBasketList()
    {
        Subscription sub =
       basketListModel.getBasketList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<List<Basket>>()
                {
                    @Override
                    public void onSuccess(List<Basket> value)
                    {
                        Log.i("TestRx", "subscribe Thread: " + Thread.currentThread().getId());
                        basketListView.showBasketList(value);
                    }

                    @Override
                    public void onError(Throwable error)
                    {
                        basketListView.showError(error.getMessage());
                    }
                });

        sub.unsubscribe();
    }

    @Override
    public void deleteBasket(long id)
    {
        if (basketListModel.deleteBasket(id) != 0)
        {
            loadBasketList();
        }
    }

    @Override
    public void editBasket(Basket basket)
    {
        if (basketListModel.editBasket(basket) != 0)
        {
            loadBasketList();
        }
    }

    @Override
    public void fillDbWithTestValues()
    {
        basketListModel.createTestValues();
        loadBasketList();
    }

    @Override
    public void createBasket(String basketName)
    {
        if (basketListModel.createBasket(basketName) != null)
        {
            loadBasketList();
        }
    }
}
