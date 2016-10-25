package com.buylist.solomakha.buylistapp.ui;

import android.app.IntentService;
import android.content.Intent;
import android.os.HandlerThread;

import java.util.logging.Logger;

/**
 * Created by asolomakha on 8/23/2016.
 */
public class MyIntentService extends IntentService
{
    public MyIntentService()
    {
        super("MyIntentServiceTest");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        Logger.getLogger("TestService").info("onHandleIntent: " + Thread.currentThread().getId());
        try
        {
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        Logger.getLogger("TestService").info("Done");



        new HandlerThread("AnotherTestThread").start();
    }
}
