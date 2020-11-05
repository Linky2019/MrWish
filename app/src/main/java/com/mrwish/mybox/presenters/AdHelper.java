package com.mrwish.mybox.presenters;

import android.os.AsyncTask;

import com.mrwish.mybox.Api.ApiHelper;
import com.mrwish.mybox.presenters.viewinterface.AdView;


public class AdHelper extends  Presenter{

    private AdView addressView;

    public AdHelper(AdView view) {
        this.addressView = view;
    }


    public void getTest(){
        TestTask testTask = new TestTask();
        testTask.executeOnExecutor(LIMITED_TASK_EXECUTOR);
    }

    class TestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            return ApiHelper.getInstance().registeraccount(params[0],params[1]);
        }

        @Override
        protected void onPostExecute(String s) {
            if (addressView != null) {
                addressView.onAdView(s);
            }
        }
    }


    @Override
    public void onDestory() {

    }
}

