package com.example.footballmatch.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by U310 on 2016/8/30.
 */
public class HttpUtil {
    public static void sendHttpClient(final  String address,final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                urlConnection=null;
                try {
                    URL url = new URL(address);
                    urlConnection=(HttpURLConnection)url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(8000);
                    urlConnection.setReadTimeout(8000);
                    InputStream in = urlConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line = null;
                    if((line = reader.readLine())!=null){
                        response.append(line);
                    }
                    if (listener!=null){
                        listener.onFinish(response.toString());
                    }
                } catch (IOException e) {
                    listener.onError(e);
                }
                finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }

                }
            }
        }).start();
    }
}
