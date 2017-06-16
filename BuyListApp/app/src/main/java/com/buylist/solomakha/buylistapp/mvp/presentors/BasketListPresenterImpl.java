package com.buylist.solomakha.buylistapp.mvp.presentors;

import android.util.Log;

import com.buylist.solomakha.buylistapp.MainApp;
import com.buylist.solomakha.buylistapp.mvp.models.BasketListModel;
import com.buylist.solomakha.buylistapp.mvp.views.BasketListView;
import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import rx.Notification;
import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class BasketListPresenterImpl implements BasketListPresenter
{
    @Inject
    BasketListModel basketListModel;

    private WeakReference<BasketListView> basketListView;

    public BasketListPresenterImpl()
    {
        MainApp.getComponent().inject(this);
    }

    public void setView(BasketListView view)
    {
        basketListView = new WeakReference<>(view);
    }

    @Override
    public void loadBasketList(final boolean showProgress)
    {
        if (showProgress)
        {
            basketListView.get().showProgress(true);
            Logger.getLogger("TestOrCh").log(Level.INFO, "showProgress: " + basketListView);
        }

        basketListModel.getBasketList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<List<Basket>>()
                {
                    @Override
                    public void onSuccess(List<Basket> value)
                    {
                        Log.i("TestRx", "subscribe Thread: " + Thread.currentThread().getId());
                        basketListView.get().showBasketList(value);
                        if (showProgress)
                        {
                            basketListView.get().showProgress(false);
                            Logger.getLogger("TestOrCh").log(Level.INFO, "closeProgress: " + basketListView);
                        }
                    }

                    @Override
                    public void onError(Throwable error)
                    {
                        basketListView.get().showError(error.getMessage());
                        if (showProgress)
                        {
                            basketListView.get().showProgress(false);
                        }
                    }
                });
    }

    @Override
    public void deleteBasket(long id, final boolean showProgress)
    {
        if (showProgress)
        {
            basketListView.get().showProgress(true);
        }
        Single.concat(basketListModel.deleteBasket(id), basketListModel.getBasketList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .last()
                .subscribe(new Action1<Object>()
                {
                    @Override
                    public void call(Object o)
                    {
                        basketListView.get().showBasketList((List<Basket>)o);
                        if (showProgress)
                        {
                            basketListView.get().showProgress(false);
                        }
                    }
                });
    }

    @Override
    public void editBasket(Basket basket, final boolean showProgress)
    {
        if (showProgress)
        {
            basketListView.get().showProgress(true);
        }

        Single.concat(basketListModel.editBasket(basket), basketListModel.getBasketList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .last()
                .subscribe(new Subscriber<Object>()
                {
                    @Override
                    public void onCompleted()
                    {
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        basketListView.get().showError("Cannot edit basket: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Object o)
                    {
                        if (showProgress)
                        {
                            basketListView.get().showProgress(false);
                        }
                        basketListView.get().showBasketList((List<Basket>)o);
                    }
                });


        if (showProgress)
        {
            basketListView.get().showProgress(false);
        }
    }

    @Override
    public void fillDbWithTestValues()
    {
        basketListModel.createTestValues();
        loadBasketList(true);
    }

    @Override
    public void createBasket(String basketName, final boolean showProgress)
    {
        if (showProgress)
        {
            basketListView.get().showProgress(true);
        }
        Single.concat(basketListModel.createBasket(basketName), basketListModel.getBasketList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .last()
                .subscribe(new Action1<Object>()
                {
                    @Override
                    public void call(Object o)
                    {
                        if (showProgress)
                        {
                            basketListView.get().showProgress(false);
                        }
                        basketListView.get().showBasketList((List<Basket>)o);
                    }
                });
    }

    @Override
    public void openProductsInBasket(long basketId)
    {
        basketListView.get().showProductsByBasket(basketId);
    }

    @Override
    public void openCreateBasketDialog()
    {
        basketListView.get().showCreateBasketDialog();
    }

    @Override
    public void openEditBasketDialog(Basket basket)
    {
        basketListView.get().showEditBasketDialog(basket);
    }
}
