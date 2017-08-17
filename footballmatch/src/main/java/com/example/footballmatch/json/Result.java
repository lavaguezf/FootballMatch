package com.example.footballmatch.json;

/**
 * Created by U310 on 2016/8/29.
 */
public class Result {
    private String key;
    private Tabs tabs;
    private Views views;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Tabs getTabs() {
        return tabs;
    }

    public void setTabs(Tabs tabs) {
        this.tabs = tabs;
    }

    public Views getViews() {
        return views;
    }

    public void setViews(Views views) {
        this.views = views;
    }
}
