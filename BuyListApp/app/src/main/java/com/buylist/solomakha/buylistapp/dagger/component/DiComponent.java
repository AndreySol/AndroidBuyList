package com.buylist.solomakha.buylistapp.dagger.component;

import com.buylist.solomakha.buylistapp.dagger.module.BasketModule;
import com.buylist.solomakha.buylistapp.dagger.module.DbModule;
import com.buylist.solomakha.buylistapp.mvp.models.BasketListModelImpl;
import com.buylist.solomakha.buylistapp.mvp.presentors.BasketListPresenterImpl;
import com.buylist.solomakha.buylistapp.ui.ProductListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {BasketModule.class, DbModule.class})
public interface DiComponent
{
    void inject(BasketListModelImpl comp);

    void inject(BasketListPresenterImpl comp);

    void inject(ProductListActivity comp);
}
