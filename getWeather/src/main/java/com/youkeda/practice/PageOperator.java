package com.youkeda.practice;

import com.alibaba.fastjson.JSON;
import com.youkeda.practice.model.AmapLive;
import com.youkeda.practice.model.AmapWeather;
import java.io.IOException;
import java.util.Scanner;
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

  // API 基础地址
  private static final String BASE_URL = "https://restapi.amap.com/v3/weather/weatherInfo";
  // API 访问密钥
  private static final String API_KEY = "5ec02b7dab5fec7b5187f2f8c88da3b0";

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("请输入要查询的城市名：");
    String location = scanner.nextLine();

    PageOperator po = new PageOperator();
    String url = BASE_URL + "?key=" + API_KEY + "&city=" + location;
    String content = po.getPageContentSync(url);
    AmapWeather amapWeather = JSON.parseObject(content, AmapWeather.class);

    if (amapWeather.getLives() == null || amapWeather.getLives().isEmpty()) {
      System.out.println("未查询到天气数据，请检查城市名是否正确。");

      return;
    }

    AmapLive live = amapWeather.getLives().get(0);

    System.out.println("======================天气查询结果======================");
    System.out.println("城市：" + live.getCity());
    System.out.println("今日天气：" + live.getWeather());
    System.out.println("温度：" + live.getTemperature() + "℃");
    System.out.println("风力：" + live.getWinddirection() + "风" + live.getWindpower() + "级");
    System.out.println("湿度：" + live.getHumidity() + "%");
    System.out.println("======================================================");
  }
}
