package com.buylist.solomakha.buylistapp.dagger.module;

import android.content.Context;

import com.buylist.solomakha.buylistapp.storage.database.DataBaseHelper;
import com.buylist.solomakha.buylistapp.storage.database.dal.DataBaseStorage;
import com.buylist.solomakha.buylistapp.storage.database.dal.Storage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class DbModule
{
    Context context;

    public DbModule(Context context)
    {
        this.context = context;
    }

    @Provides
    @Singleton
    DataBaseHelper provideDbHelper()
    {
        return new DataBaseHelper(context);
    }

    @Provides
    @Singleton
    Storage provideStorage(DataBaseHelper dataBaseHelper)
    {
        return new DataBaseStorage(dataBaseHelper);
    }
}
