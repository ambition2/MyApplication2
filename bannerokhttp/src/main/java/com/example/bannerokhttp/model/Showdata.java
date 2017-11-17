package com.example.bannerokhttp.model;

import com.example.bannerokhttp.okhttp.OkHttpUtils;

import okhttp3.Callback;

/**
 * Created by M on 2017/11/17.
 */

public class Showdata implements IShowdata {
    @Override
    public void Showdatadata(int pid, Callback callback) {
        OkHttpUtils.getInstance().doGet("https://www.zhaoapi.cn/product/getProductDetail?pid="+pid+"",callback);
    }
}
