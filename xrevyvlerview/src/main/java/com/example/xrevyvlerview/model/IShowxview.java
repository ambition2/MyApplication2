package com.example.xrevyvlerview.model;

import okhttp3.Callback;

/**
 * Created by M on 2017/11/14.
 */

public interface IShowxview {
    //定义一个接口 里面有一个方法，传入俩个变量，然后callback接口回调
    public  void Getdata(String keywords, String page, Callback callback);
}
