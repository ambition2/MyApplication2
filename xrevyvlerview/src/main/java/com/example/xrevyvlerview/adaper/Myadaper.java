package com.example.xrevyvlerview.adaper;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.xrevyvlerview.MainActivity;
import com.example.xrevyvlerview.R;
import com.example.xrevyvlerview.bean.NetDataBean;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by M on 2017/11/14.
 */

public class Myadaper extends RecyclerView.Adapter<Myadaper.MyviewHolder> {
    //上下文 bean
    Context context;
    NetDataBean bean;
   //重写这个方法
    public Myadaper(MainActivity context, NetDataBean bean) {
        this.context = context;
        this.bean = bean;
    }


    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //找布局
        View view = View.inflate(context, R.layout.items, null);
        //holder类new出来
        MyviewHolder holder = new MyviewHolder(view);
        //返回这个holder
        return holder;
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
        //给控件赋值
        //裁剪字符串，因为这个图片的url是好几个，所以要分割，得到每一个
        String[] img = bean.getData().get(position).getImages().split("!");
        System.out.println("图片"+img[0]);
        //赋值
        ImageLoader.getInstance().displayImage(img[0], holder.ivv);
        holder.tvv.setText(bean.getData().get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        //判断是够为空，返回数据的size
        return bean.getData() == null ? 0 : bean.getData().size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder {
        //找控件
        ImageView ivv;
        TextView tvv;

        public MyviewHolder(View itemView) {
            super(itemView);
            ivv = (ImageView) itemView.findViewById(R.id.ivv);
            tvv = (TextView) itemView.findViewById(R.id.tvv);
        }
    }
}
