package com.mobileapps.training.androidmultithreading.workers;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.mobileapps.training.androidmultithreading.utils.TaskCreator;

public class MyRunnable implements Runnable {

    Handler handler;

    public MyRunnable(Handler handler){
        this.handler=handler;
    }

    @Override
    public void run() {

        Message message = new Message();
        Message message1 = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("data", "Task starting");
        message.setData(bundle);

        //before the task is starting
        handler.sendMessage(message);
        try {
            TaskCreator.creatSimpleTask(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //After the task is completed
        bundle.putString("data", "Task completed");
        message1.setData(bundle);
        handler.sendMessage(message1);
    }
}
