package com.buylist.solomakha.buylistapp.ui.adapter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.buylist.solomakha.buylistapp.mvp.presentors.BasketListPresenter;
import com.buylist.solomakha.buylistapp.mvp.presentors.BasketListPresenterImpl;
import com.buylist.solomakha.buylistapp.ui.BasketListActivity;

/**
 * Created by asolomakha on 6/12/2017.
 */

public class FragmentWorker extends Fragment
{
    private BasketListPresenter mPresenter = new BasketListPresenterImpl();

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        mPresenter.setView((BasketListActivity) context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public BasketListPresenter getPresenter()
    {
        return mPresenter;
    }
}
