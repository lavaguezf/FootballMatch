package com.example.footballmatch.util;

/**
 * Created by U310 on 2016/8/4.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
