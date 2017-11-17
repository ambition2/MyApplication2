package com.example.xrevyvlerview.adaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.xrevyvlerview.R;
import com.example.xrevyvlerview.bean.NetDataBean;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by M on 2017/11/14.
 */

public class MytwoAdaper extends RecyclerView.Adapter<MytwoAdaper.MyTwoholder>{
    //传入上下文 bean
    //重写这个方法
    Context context;
    NetDataBean bean;
    public  MytwoAdaper(Context context,NetDataBean bean){
        this.context=context;
        this.bean=bean;
    }


    @Override
    public MyTwoholder onCreateViewHolder(ViewGroup parent, int viewType) {
        //找布局
        View view=View.inflate(context, R.layout.itemtwo,null);
        //holder类new出来
        MyTwoholder myTwoholder=new MyTwoholder(view);
        //返回这个holder
        return myTwoholder;
    }

    @Override
    public void onBindViewHolder(MyTwoholder holder, int position) {
        //给控件赋值
        //裁剪字符串，因为这个图片的url是好几个，所以要分割，得到每一个
        String[] img = bean.getData().get(position).getImages().split("!");
       // System.out.println("图片"+img[0]);
        ImageLoader.getInstance().displayImage(img[0], holder.ivv1);
        holder.tvv1.setText(bean.getData().get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        //判断是够为空，返回数据的size
        return bean.getData() == null ? 0 : bean.getData().size();
    }

    class MyTwoholder extends RecyclerView.ViewHolder{
        //找控件
      ImageView ivv1;
        TextView tvv1;
        public MyTwoholder(View itemView) {
            super(itemView);
           ivv1= (ImageView) itemView.findViewById(R.id.ivv1);
            tvv1= (TextView) itemView.findViewById(R.id.tvv1);
        }
    }
}
