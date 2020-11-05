package com.mrwish.mybox.presenters;

import android.os.AsyncTask;

import com.mrwish.mybox.Api.ApiHelper;
import com.mrwish.mybox.presenters.viewinterface.AdView;
import com.mrwish.mybox.presenters.viewinterface.LoginView;


public class LoginHelper extends  Presenter{

    private LoginView loginView;

    public LoginHelper(LoginView view) {
        this.loginView = view;
    }


    public void getLogin(String tenantId,String mobile,String code){
        TestTask testTask = new TestTask();
        testTask.executeOnExecutor(LIMITED_TASK_EXECUTOR,tenantId,mobile,code);
    }

    class TestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            return ApiHelper.getInstance().login(params[0],params[1],params[2]);
        }

        @Override
        protected void onPostExecute(String s) {
            if (loginView != null) {
                loginView.onLogin(s);
            }
        }
    }


    @Override
    public void onDestory() {

    }
}

