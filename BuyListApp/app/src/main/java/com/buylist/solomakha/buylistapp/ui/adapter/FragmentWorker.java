package com.buylist.solomakha.buylistapp.ui.adapter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.buylist.solomakha.buylistapp.mvp.presentors.BasketListPresenter;
import com.buylist.solomakha.buylistapp.mvp.presentors.BasketListPresenterImpl;
import com.buylist.solomakha.buylistapp.ui.BasketListActivity;

import java.util.logging.Level;
import java.util.logging.Logger;

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
        Logger.getLogger("TestMyLogger").log(Level.INFO, "FragmentWorker onAttach");
        mPresenter.setView((BasketListActivity)context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //mPresenter = new BasketListPresenterImpl(this);

        setRetainInstance(true);
        Logger.getLogger("TestMyLogger").log(Level.INFO, "FragmentWorker onCreate");
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Logger.getLogger("TestMyLogger").log(Level.INFO, "FragmentWorker onStart");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Logger.getLogger("TestMyLogger").log(Level.INFO, "FragmentWorker onResume");
    }

    public BasketListPresenter getPresenter()
    {
        return mPresenter;
    }
}
