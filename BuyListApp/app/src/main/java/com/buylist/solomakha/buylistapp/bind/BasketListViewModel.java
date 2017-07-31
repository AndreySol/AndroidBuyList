package com.buylist.solomakha.buylistapp.bind;

import android.databinding.BaseObservable;

import com.buylist.solomakha.buylistapp.mvp.presentors.BasketListPresenter;

/**
 * Created by asolomakha on 4/6/2017.
 */

public class BasketListViewModel extends BaseObservable
{
    private BasketListPresenter mPresenter;

    public BasketListViewModel(BasketListPresenter mPresenter)
    {
        this.mPresenter = mPresenter;
    }

    public void createBasket()
    {
        mPresenter.openCreateBasketDialog();
    }

    public void fillDataBase()
    {
        mPresenter.fillDbWithTestValues(true);
    }
}
