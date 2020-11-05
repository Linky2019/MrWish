/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mrwish.mybox.utils;


import java.util.List;


public class AbResult {
    
    /** 返回码：成功. */
    public static final int RESULT_OK =200;
    
    /** 返回码：失败. */
    public static final int RESULT_ERROR = 1;
    /** 返回异常码 */
    public static final int RESULT_ANOMALY = 401;
  //登录状态失效
    public static final int RESULT_NO_SESSION= 400;

    /** 返回码：token超时. */
    public static final int RESULT_TOKEN_TIMEOUT = -1;
    
    /** 返回码：0 成功. 1 失败 */
    private int code;
    
    /** 结果 message. */
    private String msg;

    private String token;

    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /** 数据. */
    private List<?> data;

    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public List<?> getData() {
        return data;
    }
    
    public void setData(List<?> data) {
        this.data = data;
    }
    
    /**
     * 
     * 描述：转换成json.
     * 
     * @return
     */
//    public String toJson() {
//        return AbJsonUtil.toJson(this);
//    }
    
}
