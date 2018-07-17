package com.mobileapps.training.androidmultithreading.workers;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.mobileapps.training.androidmultithreading.utils.Tagger;
import com.mobileapps.training.androidmultithreading.utils.TaskCreator;

public class MyAsyncTask extends AsyncTask<String,Integer, String> {

    TextView tvMain;

    public MyAsyncTask(TextView textView) {
        this.tvMain = textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        tvMain.setText("Task starting");
        Log.d(Tagger.get(this), "onPreExecute: Thread: " + Thread.currentThread().getName());
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.d(Tagger.get(this), "doInBackground " + strings[0]);
        Log.d(Tagger.get(this),"doInBackground: Thread: " + Thread.currentThread().getName());
        publishProgress(1);

        try {
            TaskCreator.creatSimpleTask(this);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "Task completed";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d(Tagger.get(this),"onProgressUpdate: Thread: " + Thread.currentThread().getName());
        tvMain.setText("Task in progress");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(Tagger.get(this),"onPostExecute: Thread: " + Thread.currentThread().getName());
        tvMain.setText(s);
    }
}
