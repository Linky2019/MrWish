package com.mrwish.mybox.model;


import androidx.fragment.app.Fragment;

import java.io.Serializable;

public class FragmentInfo implements Serializable {
    private String image;
    private String id;
    private String title;
    private int viewpageIndex;
    private Fragment fragment;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViewpageIndex() {
        return viewpageIndex;
    }

    public void setViewpageIndex(int viewpageIndex) {
        this.viewpageIndex = viewpageIndex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
