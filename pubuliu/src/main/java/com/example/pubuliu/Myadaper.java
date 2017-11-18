package com.example.pubuliu;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by M on 2017/11/18.
 */

public class Myadaper extends RecyclerView.Adapter<Myadaper.MyviewHolder> {
    private ArrayList<String> dataList = new ArrayList<>();
    Context context;
    public  Myadaper(Context context){
        this.context=context;
    }

    public void replaceAll(ArrayList<String> list) {
        dataList.clear();
        if (list != null && list.size() > 0) {
            dataList.addAll(list);
        }
        notifyDataSetChanged();
    }
    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.one,null);
          MyviewHolder holder=new MyviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {

        ImageLoader.getInstance().displayImage(dataList.get(position),holder.iv);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class  MyviewHolder extends RecyclerView.ViewHolder{
         ImageView iv;
        public MyviewHolder(View itemView) {
            super(itemView);
             iv= (ImageView) itemView.findViewById(R.id.ivImage);
        }

    }

}
