package com.buylist.solomakha.buylistapp.dagger.module;

import android.content.Context;

import com.buylist.solomakha.buylistapp.mvp.models.BasketListModel;
import com.buylist.solomakha.buylistapp.mvp.models.BasketListModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class BasketModule
{
    Context context;

    public BasketModule(Context context)
    {
        this.context = context;
    }

    @Provides
    @Singleton
    BasketListModel provideBasketListModel()
    {
        return new BasketListModelImpl();
    }
}
