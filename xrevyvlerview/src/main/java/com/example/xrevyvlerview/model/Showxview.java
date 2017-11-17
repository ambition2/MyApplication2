package com.example.xrevyvlerview.model;


import com.example.xrevyvlerview.okhttp.OkHttpUtils;

import okhttp3.Callback;

/**
 * Created by M on 2017/11/14.
 */

public class Showxview implements  IShowxview {
    //实现定义的model接口
    @Override
    public void Getdata(String keywords, String page, Callback callback) {
        //写网络请求  字符串拼接
        OkHttpUtils.getInstance().doGet("http://120.27.23.105/product/searchProducts?keywords="+keywords+"&page="+page+"",callback);
    }
}
