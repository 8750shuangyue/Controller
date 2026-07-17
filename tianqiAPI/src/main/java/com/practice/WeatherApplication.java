package com.youkeda.practice;

import com.youkeda.practice.exception.WeatherServiceException;
import com.youkeda.practice.model.Daily;
import com.youkeda.practice.model.Result;
import com.youkeda.practice.model.Weather;
import com.youkeda.practice.service.WeatherService;
import com.youkeda.practice.service.impl.WeatherServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherApplication {

    private static final Logger logger = LoggerFactory.getLogger(WeatherApplication.class);

    public static void main(String[] args) {
        logger.info("天气服务启动");

        WeatherService weatherService = new WeatherServiceImpl();

        String city = "hangzhou";
        try {
            Weather weather = weatherService.getWeatherByCity(city);

            Result result = weather.getResults().get(0);
            Daily today = result.getDaily().get(0);

            StringBuilder sb = new StringBuilder();
            sb.append(result.getLocation().getName())
                    .append("（").append(result.getLocation().getId()).append("）")
                    .append(today.getDate())
                    .append(" 白天").append(today.getText_day())
                    .append("，晚间").append(today.getText_night())
                    .append("，最高温").append(today.getHigh())
                    .append("℃，最低温").append(today.getLow()).append("℃");

            System.out.println(sb.toString());
            logger.info("天气查询结果：{}", sb.toString());

        } catch (WeatherServiceException e) {
            logger.error("天气查询失败，城市：{}，错误码：{}，原因：{}",
                    city, e.getErrorCode(), e.getMessage());
            System.err.println("查询失败：" + e.getMessage());
        } catch (Exception e) {
            logger.error("天气查询异常，城市：{}", city, e);
            System.err.println("查询异常：" + e.getMessage());
        }

        logger.info("天气服务结束");
    }
}