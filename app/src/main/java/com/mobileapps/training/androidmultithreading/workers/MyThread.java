package com.mobileapps.training.androidmultithreading.workers;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.mobileapps.training.androidmultithreading.model.event.HelloEvent;
import com.mobileapps.training.androidmultithreading.utils.TaskCreator;

import org.greenrobot.eventbus.EventBus;

public class MyThread extends Thread {

    TextView tvMain;
    Handler handler;

    public MyThread(TextView tvMain) {
        this.tvMain =  tvMain;
        this.handler =  new Handler(Looper.getMainLooper());
    }

    @Override
    public void run() {

        HelloEvent helloEvent = new HelloEvent("Before: This is the data");
        //Before the thask is started
        EventBus.getDefault().post(helloEvent);

        //Before the task is started
        handler.post(new Runnable() {
            @Override
            public void run() {
                tvMain.setText("Task starting");
            }
        });
        //Task in execution
        try {
            TaskCreator.creatSimpleTask(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Task completed
        handler.post(new Runnable() {
            @Override
            public void run() {
                tvMain.setText("Task completed");
            }
        });

        //EventBus data after
        helloEvent.setData("After: This is the new data");
        EventBus.getDefault().post(helloEvent);
    }
}
