package com.example.bannerokhttp.presenter;

import android.content.Context;

import com.example.bannerokhttp.bean.UserBena;
import com.example.bannerokhttp.model.IShowdata;
import com.example.bannerokhttp.model.Showdata;
import com.example.bannerokhttp.okhttp.OnUiCallback;
import com.example.bannerokhttp.view.IShowdatata;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by M on 2017/11/17.
 */

public class Showdatapresenter {
      Context context;
      IShowdata model;
    IShowdatata view;
    public  Showdatapresenter(Context context,IShowdatata view){
        this.context=context;
        this.view=view;
        model=new Showdata();

    }
    public  void Showshowdata(int pid){
        model.Showdatadata(pid, new OnUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                UserBena bean=gson.fromJson(result, UserBena.class);
                view.showview(bean);
            }
        });
    }
}
