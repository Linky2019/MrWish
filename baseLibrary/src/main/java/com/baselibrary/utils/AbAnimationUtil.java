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


import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;


public class AbAnimationUtil {
	
	/** 定义动画的时间. */
	public final static long aniDurationMillis = 1L;

	/**
	 * 用来改变当前选中区域的放大动画效果
	 * 从1.0f放大1.2f倍数
	 *
	 * @param view the view
	 * @param scale the scale
	 */
	public static void largerView(View view, float scale) {
		if (view == null)
			return;

		// 置于所有view最上层
		view.bringToFront();
		int width = view.getWidth();
		float animationSize = 1 + scale / width;
		scaleView(view, animationSize);
	}

	/**
	 * 用来还原当前选中区域的还原动画效果.
	 *
	 * @param view the view
	 * @param scale the scale
	 */
	public static void restoreLargerView(View view, float scale) {
		if (view == null)
			return;
		int width = view.getWidth();
		float toSize = 1 + scale / width;
		// 从1.2f缩小1.0f倍数
		scaleView(view, -1 * toSize);
	}

	/**
	 * 缩放View的显示.
	 *
	 * @param view 需要改变的View
	 * @param toSize 缩放的大小，其中正值代表放大，负值代表缩小，数值代表缩放的倍数
	 */
	public static void scaleView(final View view, float toSize) {
		ScaleAnimation scale = null;
		if (toSize == 0) {
			return;
		} else if (toSize > 0) {
			scale = new ScaleAnimation(1.0f, toSize, 1.0f, toSize,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
		} else {
			scale = new ScaleAnimation(toSize * (-1), 1.0f, toSize * (-1),
					1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
		}
		scale.setDuration(aniDurationMillis);
		scale.setInterpolator(new AccelerateDecelerateInterpolator());
		scale.setFillAfter(true);
		view.startAnimation(scale);
	}

	/**
	 * 跳动-跳起动画.
	 *
	 * @param view the view
	 * @param offsetY the offset y
	 */
	static boolean isstop;
    public static void playJumpAnimation(final View view, final float offsetY,final int time,final boolean stop) {
		Log.i("linky","stop--"+stop);
		float originalY = 0;
        float finalY = - offsetY;
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new TranslateAnimation(0, 0, originalY,finalY));
//         animationSet.setDuration(300);
        animationSet.setDuration(time);//完成时间
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.setFillAfter(true);

        animationSet.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				if (stop)return;
				playLandAnimation(view, offsetY,time,stop);
			}
		});
		if (stop){
			view.clearAnimation();
		}else {
			view.startAnimation(animationSet);
		}
    }

    /**
     * 跳动-落下动画.
     *
     * @param view the view
     * @param offsetY the offset y
     */
	public static void playLandAnimation(final View view, final float offsetY, final int time, final boolean stop) {
		Log.i("linky","stop2--"+stop);
		isstop = stop;
        float originalY =  - offsetY;
        float finalY = 0;
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new TranslateAnimation(0, 0, originalY,finalY));
//        animationSet.setDuration(200);
		animationSet.setDuration(time-100);
        animationSet.setInterpolator(new AccelerateInterpolator());
        animationSet.setFillAfter(true);

        animationSet.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
				if (isstop)return;
                //4秒后再调
                view.postDelayed(new Runnable() {

					@Override
					public void run() {
						Log.i("linky","runstop--"+isstop);
						playJumpAnimation(view, offsetY,time,isstop);
					}
				}, 4000);
            }
        });

		if (stop){
			view.clearAnimation();
		}else {
			view.startAnimation(animationSet);
		}
    }
    
    /**
     * 旋转动画
     * @param v
     * @param durationMillis 持续时间
     * @param repeatCount  Animation.INFINITE 重复计数
     * @param repeatMode  Animation.RESTART 重复模式
     */
    public static void playRotateAnimation(View v,long durationMillis,int repeatCount,int repeatMode) {
    	
        //创建AnimationSet对象
        AnimationSet animationSet = new AnimationSet(true);
        //创建RotateAnimation对象
        RotateAnimation rotateAnimation = new RotateAnimation(0f,+360f, 
					Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
        //设置动画持续
        rotateAnimation.setDuration(durationMillis);
        rotateAnimation.setRepeatCount(repeatCount);
        //Animation.RESTART
        rotateAnimation.setRepeatMode(repeatMode);
        //动画插入器
        rotateAnimation.setInterpolator(v.getContext(), android.R.anim.decelerate_interpolator);
        //添加到AnimationSet
        animationSet.addAnimation(rotateAnimation);
        
        //开始动画 
        v.startAnimation(animationSet);
	}
    

    /**
	 * 放大动画
	 */
    public static void playHeartbeatAnimation(final View view){
    	final float ZOOM_MAX = 4.0f;
    	final  float ZOOM_MIN = 1.0f;
    	
	    AnimationSet animationSet = new AnimationSet(true);
		animationSet.addAnimation(new ScaleAnimation(ZOOM_MAX, ZOOM_MIN, ZOOM_MAX, ZOOM_MIN, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f));
	   // animationSet.addAnimation(new AlphaAnimation(1.0f, 0.8f));
	
	    animationSet.setDuration(300);
	    animationSet.setInterpolator(new AccelerateInterpolator());
	    animationSet.setFillAfter(true);
	
	    animationSet.setAnimationListener(new AnimationListener() {
	        @Override
	        public void onAnimationStart(Animation animation) {
	        }
	
	        @Override
	        public void onAnimationRepeat(Animation animation) {
	        }
	
	        @Override
	        public void onAnimationEnd(Animation animation) {
	            /*AnimationSet animationSet = new AnimationSet(true);
	            animationSet.addAnimation(new ScaleAnimation(ZOOM_MAX, ZOOM_MIN, ZOOM_MAX,ZOOM_MIN, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f));
	            animationSet.addAnimation(new AlphaAnimation(2.5f, 1.0f));
	            animationSet.setDuration(100);
	            animationSet.setInterpolator(new DecelerateInterpolator());
	            animationSet.setFillAfter(false);
	            view.startAnimation(animationSet);*/
	        }
	    });
	     // 实现心跳的View
	    view.startAnimation(animationSet);
    } 

}
