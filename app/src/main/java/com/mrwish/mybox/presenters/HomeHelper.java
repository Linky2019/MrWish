package com.mrwish.mybox.presenters;

import android.os.AsyncTask;

import com.mrwish.mybox.Api.ApiHelper;
import com.mrwish.mybox.presenters.viewinterface.AdView;
import com.mrwish.mybox.presenters.viewinterface.HomeView;


public class HomeHelper extends  Presenter{

    private HomeView homeView;

    public HomeHelper(HomeView view) {
        this.homeView = view;
    }


    public void getBanner(){
        TestTask testTask = new TestTask();
        testTask.executeOnExecutor(LIMITED_TASK_EXECUTOR);
    }
    public void getCategory(){
        CategoryTask categoryTask = new CategoryTask();
        categoryTask.executeOnExecutor(LIMITED_TASK_EXECUTOR);
    }

    class TestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            return ApiHelper.getInstance().banners();
        }

        @Override
        protected void onPostExecute(String s) {
            if (homeView != null) {
                homeView.onBannerView(s);
            }
        }
    }

    class CategoryTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            return ApiHelper.getInstance().box_categories();
        }

        @Override
        protected void onPostExecute(String s) {
            if (homeView != null) {
                homeView.onCategoryView(s);
            }
        }
    }
    @Override
    public void onDestory() {

    }
}

