package com.example.bannerokhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bannerokhttp.bean.UserBena;
import com.example.bannerokhttp.presenter.Showdatapresenter;
import com.example.bannerokhttp.utilds.Myappgg;
import com.example.bannerokhttp.view.IShowdatata;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IShowdatata {
   Banner banner;
    TextView tv1,tv2;
    Showdatapresenter showdatapresenter;
    List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banner= (Banner) findViewById(R.id.banner);
        tv1= (TextView) findViewById(R.id.tv1);
        tv2= (TextView) findViewById(R.id.tv2);
        showdatapresenter=new Showdatapresenter(MainActivity.this,MainActivity.this);
        showdatapresenter.Showshowdata(71);
    }


    @Override
    public void showview(UserBena bena) {
        String qq=bena.getData().getImages();
        Log.i("------------", qq.toString());

        //进行的分割一定要牢记分隔符\\
        String[] img=qq.split("\\|");
        Log.i("++++++++", img[1]);

//          list.addAll()
        for(int i=0;i<img.length;i++)
        {
            list.add(img[i]);
        }
        banner.isAutoPlay(true);
        //将图片集合放入，加载图片
        banner.setImages(list);

        //每隔3秒切换一次
      banner.setDelayTime(3000);
        // 加载
        banner.setImageLoader(new Myappgg());

        //样式小圆点
        banner.setBannerStyle(Banner.ACCESSIBILITY_LIVE_REGION_POLITE);

        banner.start();

        tv1.setText(bena.getData().getTitle());
        tv2.setText(bena.getData().getSubhead());
    }
}
