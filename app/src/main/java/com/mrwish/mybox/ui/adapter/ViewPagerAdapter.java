package com.mrwish.mybox.ui.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mrwish.mybox.model.FragmentInfo;

import java.util.ArrayList;


public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<FragmentInfo> fragments;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<FragmentInfo> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    /**
     * 根据位置返回对应的Fragment
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * 得到页面的标题
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
