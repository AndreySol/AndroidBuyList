package com.buylist.solomakha.buylistapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.buylist.solomakha.buylistapp.R;
import com.buylist.solomakha.buylistapp.bind.BasketListViewModel;
import com.buylist.solomakha.buylistapp.databinding.BasketListActivityBinding;
import com.buylist.solomakha.buylistapp.mvp.presentors.BasketListPresenter;
import com.buylist.solomakha.buylistapp.mvp.presentors.BasketListPresenterImpl;
import com.buylist.solomakha.buylistapp.mvp.views.BasketListView;
import com.buylist.solomakha.buylistapp.storage.database.entities.Basket;
import com.buylist.solomakha.buylistapp.ui.adapter.BasketAdapter;
import com.buylist.solomakha.buylistapp.ui.helper.OnClickListener;

import java.util.ArrayList;
import java.util.List;

public class BasketListActivity extends Activity implements OnClickListener, BasketListView
{
    private static final String LOG_TAG = "myLogs";

    private BasketAdapter mBasketAdapter;
    private List<Basket> mBasketList = new ArrayList<>();

    private BasketListPresenter mPresenter;
    private BasketListActivityBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.basket_list_activity);
        mPresenter = new BasketListPresenterImpl(this);
        binding.setViewModel(new BasketListViewModel(mPresenter, this));
        initListView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mPresenter.loadBasketList();
    }

    @Override
    public void OnListItemClickListener(View v, int position)
    {
        Basket productsList = mBasketList.get(position);
        startActivity(new Intent(getApplicationContext(), ProductListActivity.class).putExtra("Id", productsList.getId()));
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
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
    }

    private void initListView()
    {
        binding.basketRecyclerList.setLayoutManager(new LinearLayoutManager(this));
        mBasketAdapter = new BasketAdapter(this, this);
        binding.basketRecyclerList.setAdapter(mBasketAdapter);
    }
}
