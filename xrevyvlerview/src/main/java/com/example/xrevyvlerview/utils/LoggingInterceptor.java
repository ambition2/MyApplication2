package com.example.xrevyvlerview.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class LoggingInterceptor implements Interceptor {
  @Override public Response intercept(Chain chain) throws IOException {
      //这里是网咯拦截器修改的时候把公共参数修改一下  然后在okhttputils里面的client方法中add增加拦截器
    Request request = chain.request();
         String url=request.url().toString()+"&source=android";
         Request.Builder builder = request.newBuilder();
         builder.get().url(url);
         Request request1=builder.build();
//到这里
    long t1 = System.nanoTime();
//    logger.info(String.format("Sending request %s on %s%n%s",
//        request.url(), chain.connection(), request.headers()));

    Response response = chain.proceed(request1);

    long t2 = System.nanoTime();
//    logger.info(String.format("Received response for %s in %.1fms%n%s",
//        response.request().url(), (t2 - t1) / 1e6d, response.headers()));

    System.out.println("t2 = " + (t2-t1));
    return response;
  }
}