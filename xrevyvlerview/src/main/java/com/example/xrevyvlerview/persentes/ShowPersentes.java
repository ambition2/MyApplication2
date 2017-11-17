package com.example.xrevyvlerview.persentes;

import android.content.Context;


import com.example.xrevyvlerview.bean.NetDataBean;
import com.example.xrevyvlerview.model.IShowxview;
import com.example.xrevyvlerview.model.Showxview;
import com.example.xrevyvlerview.okhttp.OnUiCallback;
import com.example.xrevyvlerview.view.IShowViewView;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by M on 2017/11/14.
 */

public class ShowPersentes {
    //中转站 需要有上下文，model的new出来，因为要调用它的方法，view显示的数据，
    Context context;
    IShowxview model;
    IShowViewView view;
    //重写这个方法，传入上下文，view
    public ShowPersentes(Context context,IShowViewView view){
        this.context=context;
        this.view=view;
        //modelnew的时候前面是接口，后面是实现这个接口的类
        model=new Showxview();
    }
    //定义一个方法，方便在Activiti里面调用
    public  void Showdatas(String keywords,String page){
        //得到model的方法
         model.Getdata(keywords, page, new OnUiCallback() {
             @Override
             public void onFailed(Call call, IOException e) {

             }

             @Override
             public void onSuccess(String result) {
                 //解析数据
                 Gson gson=new Gson();
                 NetDataBean bean= gson.fromJson(result, NetDataBean.class);
                 //解析到的bean显示到view上
                 view.Showview(bean);
             }
         });
    }
    //防止内存溢出在activity里面调用，把当前的view等于空
    public  void destory(){
        this.view=null;
    }
}
