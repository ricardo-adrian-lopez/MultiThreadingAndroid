package com.mobileapps.training.androidmultithreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mobileapps.training.androidmultithreading.model.event.HelloEvent;
import com.mobileapps.training.androidmultithreading.utils.Tagger;
import com.mobileapps.training.androidmultithreading.workers.MyAsyncTask;
import com.mobileapps.training.androidmultithreading.workers.MyRunnable;
import com.mobileapps.training.androidmultithreading.workers.MyThread;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements Handler.Callback{
    TextView tvMain, tvEventBus;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(Tagger.get(this), "onCreate: " + Thread.currentThread().getName());

        tvMain = findViewById(R.id.tvMain);
        tvEventBus = findViewById(R.id.tvEventBus);
        handler =  new Handler(this);
    }

    public void onMultithreading(View view) {
        Log.d(Tagger.get(this), "onMultiThreading: " + Thread.currentThread().getName());

        switch (view.getId()) {
            case R.id.btnThread:
                MyThread myThread = new MyThread(tvMain);
                myThread.start();
                break;
            case R.id.btnRunnable:
                MyRunnable myRunnable = new MyRunnable(handler);
                //myRunnable.run();// Dont start a new Thread
                Thread thread = new Thread(myRunnable);
                thread.start(); //execute runnable in a new thread
                break;
            case R.id.btnAsyncTask:
                MyAsyncTask myAsyncTask =  new MyAsyncTask(tvMain);
                myAsyncTask.execute("Params passed from main");
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        tvMain.setText(msg.getData().getString("data"));
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventReceived(HelloEvent event){
        tvEventBus.setText(event.getData());
    }
}
