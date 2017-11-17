package com.example.xrevyvlerview.utils;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Handler;


import com.example.xrevyvlerview.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

/**
 * Created by M on 2017/11/12.
 */

public class Myapp extends Application {
    ImageLoader imageLoader;
    @Override
    public void onCreate() {
        super.onCreate();
        File file = this.getCacheDir();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher) // 加载未完成时显示的自定义图片
                .showImageForEmptyUri(R.mipmap.ic_launcher) // 链接为空时的占位图
                .showImageOnFail(R.mipmap.ic_launcher_round) // 加载失败时显示自定义图片
                .resetViewBeforeLoading(false)  // 在加载前是否重置 view ,默认为false
                .delayBeforeLoading(1000)  //设置在开始加载前的延迟时间，单位为毫秒
                .cacheInMemory(true) // 是否启用内存缓存，默认为false
                .cacheOnDisk(true) // 是否启用磁盘缓存，默认为false
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // 图片的缩放类型
                .bitmapConfig(Bitmap.Config.ARGB_8888) // 图片的色彩格式
                .handler(new Handler()) // handler 对象,消息处理
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this)
                // max width, max height，即保存的每个缓存文件的最大长宽
                .memoryCacheExtraOptions(480, 800)
                // 线程池内加载的数量
                .threadPoolSize(3)
                // 线程优先级
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .defaultDisplayImageOptions(options)
                // You can pass your own memory cache implementation你可以通过自己的内存缓存实现
                // .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                // .memoryCacheSize(2 * 1024 * 1024)
                //硬盘缓存50MB
                .diskCacheSize(50 * 1024 * 1024)
                //将保存的时候的URI名称用MD5
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                // 加密
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())//将保存的时候的URI名称用HASHCODE加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCacheFileCount(100) //缓存的File数量
                .diskCache(new UnlimitedDiscCache(file))// 自定义缓存路径
                // .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                // .imageDownloader(new BaseImageDownloader(context, 5 * 1000,
                // 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();

        ImageLoader.getInstance().init(config);
    }




}
