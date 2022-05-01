package com.webdemo.weapi.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
    private static Gson gson;


    static{
        gson = new GsonBuilder()
                .setDateFormat("yyy-MM-dd HH:mm:ss")
                .disableHtmlEscaping()
                .create();
    }
    private GsonUtil(){ }

    public static Gson getInstance(){
        return gson;
    }

}
