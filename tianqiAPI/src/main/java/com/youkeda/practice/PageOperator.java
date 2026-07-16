package com.youkeda.practice;

import com.alibaba.fastjson.JSON;
import com.youkeda.practice.model.Daily;
import com.youkeda.practice.model.Result;
import com.youkeda.practice.model.Weather;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;

public class PageOperator {

  // okHttpClient 实例
  private OkHttpClient okHttpClient;

  public PageOperator() {
    //1. 构建 okHttpClient 实例
    okHttpClient = new OkHttpClient();
  }

  /**
   * 根据输入的url，读取页面内容并返回
   */
  public String getPageContentSync(String url) {
    // 参数判断，未输入参数则直接返回
    if (StringUtils.isBlank(url)) {
      return null;
    }

    //2.定义一个request
    Request request = new Request.Builder().url(url).build();
    //3.使用client去请求
    Call call = okHttpClient.newCall(request);
    String result = null;
    try {
      //4.获得返回结果
      result = call.execute().body().string();
    } catch (IOException e) {
      System.out.println("request " + url + " error . ");
      e.printStackTrace();
    }

    return result;
  }

  public static void main(String[] args) {
    PageOperator po = new PageOperator();
    String content = po.getPageContentSync("https://api.seniverse.com/v3/weather/daily.json?key=SCYrvkytJze9qyzOh&location=hangzhou");
    Weather weather = JSON.parseObject(content, Weather.class);

    Result result = weather.getResults().get(0);
    Daily today = result.getDaily().get(0);
    System.out.println(today.getDate() + result.getLocation().getName() + "（" + result.getLocation().getId() + "）白天" + today.getText_day() + "，晚间" + today.getText_night());
  }
}
