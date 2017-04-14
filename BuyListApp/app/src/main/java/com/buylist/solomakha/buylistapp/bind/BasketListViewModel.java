package com.buylist.solomakha.buylistapp.bind;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.view.View;

import com.buylist.solomakha.buylistapp.mvp.presentors.BasketListPresenter;
import com.buylist.solomakha.buylistapp.ui.BasketDialogFragment;

/**
 * Created by asolomakha on 4/6/2017.
 */

public class BasketListViewModel extends BaseObservable
{
    private BasketListPresenter mPresenter;
    private Activity mParentActivity;

    public BasketListViewModel(BasketListPresenter mPresenter, Activity activity)
    {
        this.mPresenter = mPresenter;
        this.mParentActivity = activity;
    }

    public void createBasket(View view)
    {
        BasketDialogFragment basketDialog = new BasketDialogFragment();
        basketDialog.setPresenter(mPresenter);
        basketDialog.show(mParentActivity.getFragmentManager(), "");
    }

    public void fillDataBase(View view)
    {
        mPresenter.fillDbWithTestValues();
    }
}
