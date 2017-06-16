package com.buylist.solomakha.buylistapp.ui;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.buylist.solomakha.buylistapp.R;
import com.buylist.solomakha.buylistapp.bind.BasketListViewModel;
import com.buylist.solomakha.buylistapp.databinding.BasketListActivityBinding;
import com.buylist.solomakha.buylistapp.mvp.presentors.BasketListPresenter;
import com.buylist.solomakha.buylistapp.mvp.presentors.BasketListPresenterImpl;
import com.buylist.solomakha.buylistapp.mvp.views.BasketListView;
import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;
import com.buylist.solomakha.buylistapp.ui.adapter.BasketAdapter;
import com.buylist.solomakha.buylistapp.ui.adapter.FragmentWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.buylist.solomakha.buylistapp.ui.adapter.BasketAdapter.MENU_CONTEXT_DELETE_ID;
import static com.buylist.solomakha.buylistapp.ui.adapter.BasketAdapter.MENU_CONTEXT_EDIT_ID;

public class BasketListActivity extends Activity implements BasketListView
{
    private static final String LOG_TAG = "myLogs";

    private BasketAdapter mBasketAdapter;
    private List<Basket> mBasketList = new ArrayList<>();

    private BasketListPresenter mPresenter;

    private ProgressDialog progressDialog;
    private boolean mInProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        BasketListActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.basket_list_activity);
        binding.setViewModel(new BasketListViewModel(mPresenter));
        initListView(binding.basketRecyclerList);
        initProgressDialog();

        FragmentWorker fragmentWorker = (FragmentWorker) getFragmentManager().findFragmentByTag("Test");
        if (fragmentWorker == null)
        {
            fragmentWorker = new FragmentWorker();
            getFragmentManager().beginTransaction().add(fragmentWorker, "Test").commit();
        }

        mPresenter = fragmentWorker.getPresenter();
    }

    private void initListView(RecyclerView recyclerView)
    {
        mBasketAdapter = new BasketAdapter(this, mPresenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mBasketAdapter);
    }

    private void initProgressDialog()
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.please_wait));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
    }

    @Override
    protected void onResume()
    {
        Logger.getLogger("TestSave").log(Level.INFO, "onResume");
        super.onResume();
        if (!mInProgress)
        {
            mPresenter.loadBasketList(true);
        }
        else
        {
            showProgress(true);
        }
    }

    @Override
    public void showBasketList(List<Basket> basketList)
    {
        mBasketList = basketList;
        // Refresh listView
        mBasketAdapter.refresh(mBasketList);
    }

    @Override
    public void showError(String errorMsg)
    {
        showProgress(false);
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProductsByBasket(long basketId)
    {
        startActivity(new Intent(getApplicationContext(), ProductListActivity.class).putExtra("Id", basketId));
    }

    @Override
    public void showProgress(boolean show)
    {
        if (show)
        {
            mInProgress = true;
            progressDialog.show();
        }
        else
        {
            mInProgress = false;
            progressDialog.dismiss();
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case MENU_CONTEXT_EDIT_ID:
                mPresenter.openEditBasketDialog(new Basket(mBasketList.get(item.getOrder())));
                //showEditBasketDialog(mBasketList.get(item.getOrder()));
                return true;
            case MENU_CONTEXT_DELETE_ID:
                mPresenter.deleteBasket(mBasketList.get(item.getOrder()).getId(), true);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void showCreateBasketDialog()
    {
        BasketDialogFragment basketDialog = new BasketDialogFragment();
        basketDialog.setPresenter(mPresenter);
        basketDialog.show(getFragmentManager(), "");
    }

    @Override
    public void showEditBasketDialog(Basket basket)
    {
        BasketDialogFragment basketDialog = new BasketDialogFragment();
        basketDialog.setPresenter(mPresenter);
        basketDialog.setBasket(basket);
        basketDialog.show(getFragmentManager(), "");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        Logger.getLogger("TestSave").log(Level.INFO, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putBoolean("inProgress", mInProgress);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        Logger.getLogger("TestSave").log(Level.INFO, "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        mInProgress = savedInstanceState.getBoolean("inProgress");
    }
}
