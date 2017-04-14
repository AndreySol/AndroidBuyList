package com.buylist.solomakha.buylistapp;

import android.app.Application;

import com.buylist.solomakha.buylistapp.dagger.component.DaggerDiComponent;
import com.buylist.solomakha.buylistapp.dagger.component.DiComponent;
import com.buylist.solomakha.buylistapp.dagger.module.BasketModule;
import com.buylist.solomakha.buylistapp.dagger.module.DbModule;

public class MainApp extends Application
{
    private static DiComponent component;

    @Override
    public void onCreate()
    {
        super.onCreate();
        component = DaggerDiComponent
                .builder()
                .dbModule(new DbModule(this))
                .basketModule(new BasketModule(this))
                .build();
    }

    public static DiComponent getComponent()
    {
        return component;
    }
}
