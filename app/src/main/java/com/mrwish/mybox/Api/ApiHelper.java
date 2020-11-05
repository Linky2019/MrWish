package com.mrwish.mybox.Api;

import android.util.Log;

import com.baselibrary.utils.AbSharedUtil;
import com.baselibrary.utils.AbStrUtil;
import com.baselibrary.utils.AbToastUtil;
import com.mrwish.mybox.utils.App;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 网络请求类
 */
public class ApiHelper {
    private static final String TAG = ApiHelper.class.getSimpleName();
    private static ApiHelper instance = null;
    private static String HEADER_PREFIX = "Bearer "; //header前缀
    public static String HOST_URL = Constants.HOST_URL;

    private final String CONTENT_TYPE = "application/json";
    private final String ENCODING = "utf-8";

    public static ApiHelper getInstance() {
        if (instance == null) {
            instance = new ApiHelper();
        }
        return instance;
    }


    public static final MediaType MEDIA_TYPE_JSON
            = MediaType.parse("application/json; charset=utf-8");

    private static final OkHttpClient client = new OkHttpClient();


    /**
     * Post请求  已json传输数据
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String postJson(String url, String jsondata) throws IOException {

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsondata);
        Request request = new Request.Builder()
                .url(HOST_URL + url)
                .addHeader("Authorization",HEADER_PREFIX+  AbSharedUtil.getString(App.getContext(), Constants.BOX_TOKEN))
                .post(requestBody)
                .build();
        Log.i(TAG, "post response url:" + HOST_URL + url + jsondata);
        Log.i(TAG,  "Authorization:" + HEADER_PREFIX+  AbSharedUtil.getString(App.getContext(), Constants.BOX_TOKEN));
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            Log.i(TAG, "response:" + responseBody);
            try {
                JSONObject dataobj = new JSONObject(responseBody);
                int code = dataobj.optInt("code");
//                if (code == 400 && first) {
//                    first = false;
//                    LoginoutTask loginoutTask = new LoginoutTask();
//                    loginoutTask.executeOnExecutor(Presenter.LIMITED_TASK_EXECUTOR);
//                    CookieSyncManager.createInstance(App.getContext());
//                    CookieManager.getInstance().removeAllCookie();
//                    MySelfInfo.getInstance().clearCache(App.getContext());
//                    MySelfInfo.getInstance().setToken("");
//                    Log.i("未登录", "400");
//                    Intent intent = new Intent(App.getInstance(), LoginActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    App.getInstance().startActivity(intent);
//
//                }
            } catch (Exception e) {
            }
            return responseBody;
        } else {
            Log.i(TAG, "response error" + response);
            return "";
        }
    }

    /**
     * Post请求 已表单格式传输
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String post(String url, FormBody.Builder builder, boolean istoken) throws IOException {
        FormBody body = builder.build();
        Request request;

        if (istoken) {
            request = new Request.Builder()
                    .url(HOST_URL + url)
                    .addHeader("Authorization", AbSharedUtil.getString(App.getContext(), Constants.BOX_TOKEN) == null ? "" : AbSharedUtil.getString(App.getContext(), Constants.BOX_TOKEN))
                    .post(body)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(HOST_URL + url)
                    .post(body)
                    .build();
        }
        Log.i(TAG, "post response url:" + HOST_URL + url);
        Log.i(TAG, "IS----" + istoken + " BOX_TOKEN:" + AbSharedUtil.getString(App.getContext(), Constants.BOX_TOKEN));
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            Log.i(TAG, "response:" + responseBody);
//            try {
//                JSONObject dataobj = new JSONObject(responseBody);
//                int code = dataobj.optInt("code");
//                if (code == 501 && first) {
//                    first = false;
//                    LoginoutTask loginoutTask = new LoginoutTask();
//                    loginoutTask.executeOnExecutor(Presenter.LIMITED_TASK_EXECUTOR);
//                    CookieSyncManager.createInstance(App.getContext());
//                    CookieManager.getInstance().removeAllCookie();
//                    MySelfInfo.getInstance().clearCache(App.getContext());
//                    MySelfInfo.getInstance().setToken("");
//                    Log.i("未登录", "501");
//                    Intent intent = new Intent(App.getInstance(), LoginActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    App.getInstance().startActivity(intent);
//
//                }
//            } catch (Exception e) {
//            }
            return responseBody;
        } else {
            Log.i(TAG, "response error" + response);
            return "";
        }
    }


    /**
     * Get请求
     *
     * @param actionUrl
     * @param paramsMap
     * @return 一般情况下我们选用HashMap 效率高 但是接口要sign验签要排序 只能用TreeMap
     * @throws IOException
     */
    public String get(String actionUrl, HashMap<String, String> paramsMap, boolean istoken) throws IOException {

        StringBuilder tempParams = new StringBuilder();
        try {
            //处理参数
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                //对参数进行URLEncoder
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get
                        (key), "utf-8")));
                pos++;
            }
            String requestUrl = String.format("%s%s?%s", HOST_URL, actionUrl, tempParams.toString
                    ());
            Log.i(TAG, "IS----" + istoken + " BOX_TOKEN:" + AbSharedUtil.getString(App.getContext(), Constants.BOX_TOKEN));
            Log.i(TAG, "get request:" + requestUrl);
            Request request;
            if (istoken) {
                request = new Request.Builder()
                        .url(requestUrl)
                        .addHeader("Authorization", HEADER_PREFIX+AbSharedUtil.getString(App.getContext(), Constants.BOX_TOKEN))
                        .get()
                        .build();
            } else {
                request = new Request.Builder()
                        .url(requestUrl)
                        .get()
                        .build();
            }
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                Log.i(TAG, "response:" + responseBody);
                //code==501 需要登录 清除本地登录缓存 跳登录页面
//                try {
//                    JSONObject dataobj = new JSONObject(responseBody);
//                    int code = dataobj.optInt("code");
//                    if (code == 501 && first) {
//                        first = false;
//                        LoginoutTask loginoutTask = new LoginoutTask();
//                        loginoutTask.executeOnExecutor(Presenter.LIMITED_TASK_EXECUTOR);
//                        CookieSyncManager.createInstance(App.getContext());
//                        CookieManager.getInstance().removeAllCookie();
//                        MySelfInfo.getInstance().clearCache(App.getContext());
//                        MySelfInfo.getInstance().setToken("");
//                        Log.i("未登录", "400");
//                        Intent intent = new Intent(App.getInstance(), LoginActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        App.getInstance().startActivity(intent);
//
//                    }
//                } catch (Exception e) {
//                }
                return responseBody;
            } else {
                Log.i(TAG, "response error:" + response);
                return "";
            }

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return "";

    }

    /**
     * Post请求 图片上传
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String postImg(String url, boolean istoken, String name, String imgurl) throws IOException {
        File file = new File(imgurl);
        RequestBody image = RequestBody.create(MediaType.parse("image/png"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(name, imgurl, image)
                .build();

        Request request;

        if (istoken) {
            request = new Request.Builder()
                    .url(HOST_URL + url)
                    .addHeader("Authorization", AbSharedUtil.getString(App.getContext(), Constants.BOX_TOKEN) == null ? "" : AbSharedUtil.getString(App.getContext(), Constants.BOX_TOKEN))
                    .post(requestBody)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(HOST_URL + url)
                    .post(requestBody)
                    .build();
        }

        Log.i(TAG, "post response url:" + HOST_URL + url);
        Log.i(TAG, "IS----" + istoken + " BOX_TOKEN:" + AbSharedUtil.getString(App.getContext(), Constants.BOX_TOKEN));
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            Log.i(TAG, "response:" + responseBody);
//            try {
//                JSONObject dataobj = new JSONObject(responseBody);
//                int code = dataobj.optInt("code");
//                if (code == 501 && first) {
//                    first = false;
//                    LoginoutTask loginoutTask = new LoginoutTask();
//                    loginoutTask.executeOnExecutor(Presenter.LIMITED_TASK_EXECUTOR);
//                    CookieSyncManager.createInstance(App.getContext());
//                    CookieManager.getInstance().removeAllCookie();
//                    MySelfInfo.getInstance().clearCache(App.getContext());
//                    MySelfInfo.getInstance().setToken("");
//                    Log.i("未登录", "501");
//                    Intent intent = new Intent(App.getInstance(), LoginActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    App.getInstance().startActivity(intent);
//
//                }
//            } catch (Exception e) {
//            }
            return responseBody;
        } else {
            Log.i(TAG, "response error" + response);
            return "";
        }
    }

    /**
     * Post请求 图片上传 多参数
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String postImg_type(String url, boolean istoken, String name, String imgurl, String typename, String typevalue) throws IOException {


        File file = new File(imgurl);
        RequestBody image = RequestBody.create(MediaType.parse("image/png"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(typename, typevalue)//多参数
                .addFormDataPart(name, imgurl, image)
                .build();

        Request request;

        if (istoken) {
            request = new Request.Builder()
                    .url(HOST_URL + url)
                    .addHeader("Authorization", AbSharedUtil.getString(App.getContext(), Constants.BOX_TOKEN) == null ? "" : AbSharedUtil.getString(App.getContext(), Constants.BOX_TOKEN))
                    .post(requestBody)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(HOST_URL + url)
                    .post(requestBody)
                    .build();
        }

        Log.i(TAG, "post response url:" + HOST_URL + url);
        Log.i(TAG, "IS----" + istoken + " BOX_TOKEN:" + AbSharedUtil.getString(App.getContext(), Constants.BOX_TOKEN));
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            Log.i(TAG, "response:" + responseBody);
//            try {
//                JSONObject dataobj = new JSONObject(responseBody);
//                int code = dataobj.optInt("code");
//                if (code == 501 && first) {
//                    first = false;
//                    LoginoutTask loginoutTask = new LoginoutTask();
//                    loginoutTask.executeOnExecutor(Presenter.LIMITED_TASK_EXECUTOR);
//                    CookieSyncManager.createInstance(App.getContext());
//                    CookieManager.getInstance().removeAllCookie();
//                    MySelfInfo.getInstance().clearCache(App.getContext());
//                    MySelfInfo.getInstance().setToken("");
//                    Log.i("未登录", "501");
//                    Intent intent = new Intent(App.getInstance(), LoginActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    App.getInstance().startActivity(intent);
//
//                }
//            } catch (Exception e) {
//            }
            return responseBody;
        } else {
            Log.i(TAG, "response error" + response);
            return "";
        }
    }

    /***************************接口请求*********************************/

    //注册
    public String registeraccount(String gender, String nickName) {
        try {
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("gender", gender);
            builder.add("nickName", nickName);
            String response = ApiHelper.getInstance().post(Constants.REGISTERACCOUNT, builder, false);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //冻结账户
    public String lockaccount(String username, String skyId, String password, String realName, String idNumber) {
        try {
            HashMap<String, String> paramsMap = new HashMap<>();
            paramsMap.put("username", username);
            paramsMap.put("skyId", skyId);
            paramsMap.put("password", password);
            paramsMap.put("realName", realName);
            paramsMap.put("idNumber", idNumber);
            String response = ApiHelper.getInstance().get(Constants.REGISTERACCOUNT, paramsMap, false);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //登录
    public String login(String tenantId,String mobile, String code) {
        try {
//            FormBody.Builder builder = new FormBody.Builder();
//            builder.add("mobile", mobile);
//            builder.add("code", code);
            JSONObject data = new JSONObject();
            data.put("tenantId",tenantId);
            data.put("mobile",mobile);
            data.put("code",code);
            String response = ApiHelper.getInstance().postJson(Constants.LOGIN, data.toString());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //banner
    public String banners() {
        try {
            HashMap<String, String> paramsMap = new HashMap<>();
            String response = ApiHelper.getInstance().get(Constants.BANNERS, paramsMap,true);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String banners_count() {
        try {
            JSONObject data = new JSONObject();
            String response = ApiHelper.getInstance().postJson(Constants.BANNERS_COUNT, data.toString());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String box_categories() {
        try {
            HashMap<String, String> paramsMap = new HashMap<>();
            String response = ApiHelper.getInstance().get(Constants.BOX_CATEGORIES, paramsMap, true);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
