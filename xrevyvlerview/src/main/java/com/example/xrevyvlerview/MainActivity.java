package com.example.xrevyvlerview;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.xrevyvlerview.adaper.Myadaper;
import com.example.xrevyvlerview.adaper.MytwoAdaper;
import com.example.xrevyvlerview.bean.NetDataBean;
import com.example.xrevyvlerview.persentes.ShowPersentes;
import com.example.xrevyvlerview.view.IShowViewView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MainActivity extends AppCompatActivity implements IShowViewView {
    XRecyclerView xview;
    ShowPersentes persentes;
    Context context;
    int shuxin=1;//定义的变量，用来刷新加载用
    EditText ed;
   Button bt,bu1;
    boolean panduan=true;//先给点击事件一个boolean值，根据true和false来断定
    Handler handler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找控件
       xview= (XRecyclerView) findViewById(R.id.xview);
        ed= (EditText) findViewById(R.id.ed);
        bt= (Button) findViewById(R.id.bt);
        bu1= (Button) findViewById(R.id.bu1);
        //persentes new 出来 传俩个参数
        persentes=new ShowPersentes(this,this);
  //先给页面设一个初始的页面数据，调用persentes的方法
        persentes.Showdatas("笔记本","1");

    }

    @Override
    public void Showview(final NetDataBean bean) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //设置布局管理器
                LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this);
                //给控件附上
                xview.setLayoutManager(manager);
                //new 出来adaper 传入参数
                final Myadaper myadaper=new Myadaper(MainActivity.this,bean);
                //控件设置适配器
                xview.setAdapter(myadaper);
                //刷新
                myadaper.notifyDataSetChanged();
                xview.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //刷新
                                shuxin++;//变量++
                                //重新赋值，因为定义的变量是int，这里是字符串，所以要在后面+"";
                                persentes.Showdatas("笔记本",shuxin+"");
                                //刷新适配器
                                myadaper.notifyDataSetChanged();
                                //关闭
                                xview.refreshComplete();
                            }
                        },1000);

                    }

                    @Override
                    public void onLoadMore() {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //加载
                                shuxin++;
                                //重新赋值，因为定义的变量是int，这里是字符串，所以要在后面+"";
                                persentes.Showdatas("笔记本",shuxin+"");
                                myadaper.notifyDataSetChanged();
                                xview.loadMoreComplete();
                            }
                        },1000);

                    }
                });
            }
        });
        //给按钮设置点击事件来做搜索的功能
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框的值
                String qq=ed.getText().toString();
               //把值附到persentes的请求数据的方法里面
                persentes.Showdatas(qq,shuxin+"");

                 //设置布局管理器
                LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this);

                xview.setLayoutManager(manager);
                 //得到适配器，给控件，刷新适配器
                final Myadaper myadaper=new Myadaper(MainActivity.this,bean);
                xview.setAdapter(myadaper);
                myadaper.notifyDataSetChanged();

            }
        });
        //判断按钮切换布局的功能
        bu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果变量==true
                if(panduan==true){
                    //先给它个图片
                    bu1.setBackgroundResource(R.drawable.lv_icon);
                    //然后设置另一个的布局管理器  在创建一个新的adaper 新的布局
                    GridLayoutManager manager=new GridLayoutManager(MainActivity.this,2);
                    xview.setLayoutManager(manager);
                    //new出另一个布局的adaper
                    MytwoAdaper mytwoAdaper=new MytwoAdaper(MainActivity.this,bean);
                    xview.setAdapter(mytwoAdaper);
                    //刷新适配器
                    mytwoAdaper.notifyDataSetChanged();
                    //执行完以后把变量设置为false 如果不设置的切换的时候回切不过来
                    panduan=false;

                }else
                {
                    //先给它个图片
                    bu1.setBackgroundResource(R.drawable.grid_icon);
                    //设置管理器，写一开始的布局，创建适配器，刷新就可以了
                    LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this);
                    xview.setLayoutManager(manager);

                    final Myadaper myadaper=new Myadaper(MainActivity.this,bean);
                    xview.setAdapter(myadaper);
                    myadaper.notifyDataSetChanged();
                    //执行到在这里已经是false了，把它改成true，方便切换
                    panduan=true;
                }
            }
        });
    }

    @Override
    //实现persentes里面定义的方法，用来防止内存溢出
    protected void onDestroy() {
        super.onDestroy();
        persentes.destory();
    }
}
