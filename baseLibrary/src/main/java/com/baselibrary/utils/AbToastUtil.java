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
package com.baselibrary.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;


public class AbToastUtil {

    /** 显示Toast. */
    public static final int SHOW_TOAST = 0;
    

    
    /**
     * 描述：Toast提示文本.
     * @param text  文本
     */
	public static void showToast(Context context,String text) {
		if(!AbStrUtil.isEmpty(text)){
//			Toast.makeText(context,text, Toast.LENGTH_SHORT).show();
			Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		}
	}
	
	/**
     * 描述：Toast提示文本.
     * @param resId  文本的资源ID
     */
	public static void showToast(Context context,int resId) {
		Toast.makeText(context,""+context.getResources().getText(resId), Toast.LENGTH_SHORT).show();
	}
    

    

}
