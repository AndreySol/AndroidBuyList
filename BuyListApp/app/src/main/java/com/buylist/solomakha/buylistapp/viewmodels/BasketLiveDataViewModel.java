package com.buylist.solomakha.buylistapp.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.buylist.solomakha.buylistapp.db.AppDatabase;
import com.buylist.solomakha.buylistapp.db.model.Basket;

import java.util.List;

public class BasketLiveDataViewModel extends AndroidViewModel
{
    private AppDatabase appDatabase;

    public BasketLiveDataViewModel(Application application)
    {
        super(application);
        appDatabase = AppDatabase.getDatabase(application);
    }

    public LiveData<List<Basket>> getList()
    {
        return appDatabase.basketDao().getAll();
    }
}
