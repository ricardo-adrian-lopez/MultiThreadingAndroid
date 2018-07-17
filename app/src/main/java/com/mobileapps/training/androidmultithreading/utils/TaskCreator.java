package com.mobileapps.training.androidmultithreading.utils;

import android.util.Log;

public class TaskCreator {
    public static void creatSimpleTask(Object object) throws InterruptedException {
        for(int i = 0; i < 5; i++){
            Thread.sleep(500);
            //log values
            Log.d(Tagger.get(object), "createSimpleTask: " + i + " Thread:" + Thread.currentThread().getName());
        }
    }
}
