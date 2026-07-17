package com.youkeda.practice;

import com.youkeda.practice.exception.WeatherServiceException;
import com.youkeda.practice.mail.Mail;
import com.youkeda.practice.model.Daily;
import com.youkeda.practice.model.Result;
import com.youkeda.practice.model.Weather;
import com.youkeda.practice.service.WeatherService;
import com.youkeda.practice.service.impl.WeatherServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherNotice {

    private static final Logger logger = LoggerFactory.getLogger(WeatherNotice.class);

    public static void main(String[] args) {
        logger.info("天气通知服务启动");

        WeatherService weatherService = new WeatherServiceImpl();
        String city = "hangzhou";

        try {
            Weather weather = weatherService.getWeatherByCity(city);
            Result result = weather.getResults().get(0);
            Daily today = result.getDaily().get(0);

            String cityName = result.getLocation().getName();
            Integer tempLow = today.getLow();
            Integer tempHigh = today.getHigh();
            String date = today.getDate();

            String title = "天气报告";
            StringBuilder mailContent = new StringBuilder();
            mailContent.append(cityName)
                    .append("，").append(date)
                    .append("，天气：").append(today.getText_day())
                    .append("转").append(today.getText_night())
                    .append("，气温：").append(tempLow)
                    .append(" ~ ").append(tempHigh).append("摄氏度。");

            if (tempLow != null && tempLow <= 20) {
                mailContent.append("请注意保暖。");
            } else if (tempLow != null && tempLow >= 35) {
                mailContent.append("请注意防暑。");
            } else {
                mailContent.append("请注意天气变化。");
            }

            System.out.println(mailContent.toString());
            logger.info("天气通知内容：{}", mailContent.toString());

            Mail mail = new Mail();
            mail.sendMail(title, mailContent.toString());

            logger.info("天气通知服务结束");
        } catch (WeatherServiceException e) {
            logger.error("天气查询失败，城市：{}，错误码：{}，原因：{}",
                    city, e.getErrorCode(), e.getMessage());
            System.err.println("查询失败：" + e.getMessage());
        } catch (Exception e) {
            logger.error("天气通知异常，城市：{}", city, e);
            System.err.println("通知异常：" + e.getMessage());
        }
    }
}