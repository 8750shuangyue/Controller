package com.youkeda.practice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.youkeda.practice.config.ConfigLoader;
import com.youkeda.practice.exception.WeatherServiceException;
import com.youkeda.practice.http.HttpClient;
import com.youkeda.practice.model.Weather;
import com.youkeda.practice.service.WeatherService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WeatherServiceImpl implements WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Override
    public Weather getWeatherByCity(String city) {
        if (StringUtils.isBlank(city)) {
            logger.warn("城市名称为空");
            throw new WeatherServiceException("INVALID_PARAM", "城市名称不能为空");
        }

        String apiKey = ConfigLoader.getApiKey();
        String baseUrl = ConfigLoader.getBaseUrl();

        if (StringUtils.isBlank(apiKey)) {
            logger.error("API Key 未配置");
            throw new WeatherServiceException("CONFIG_ERROR", "API Key 未配置");
        }

        String url = String.format("%s?key=%s&location=%s", baseUrl, apiKey, city);
        logger.info("发起天气查询请求，城市：{}", city);

        OkHttpClient client = HttpClient.getInstance();
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "WeatherService/1.0")
                .build();

        try (Response response = client.newCall(request).execute()) {
            logger.info("请求返回状态码：{}", response.code());

            if (!response.isSuccessful()) {
                String errorMessage = String.format("HTTP请求失败，状态码：%d", response.code());
                logger.error(errorMessage);
                throw new WeatherServiceException("HTTP_ERROR", errorMessage);
            }

            ResponseBody body = response.body();
            if (body == null) {
                logger.error("HTTP响应体为空");
                throw new WeatherServiceException("EMPTY_RESPONSE", "HTTP响应体为空");
            }

            String responseBody = body.string();
            logger.debug("响应内容：{}", responseBody);

            Weather weather = parseWeatherResponse(responseBody);
            if (weather == null || weather.getResults() == null || weather.getResults().isEmpty()) {
                logger.warn("天气数据为空，城市：{}", city);
                throw new WeatherServiceException("NO_DATA", "未查询到天气数据");
            }

            logger.info("天气查询成功，城市：{}，更新时间：{}",
                    weather.getResults().get(0).getLocation().getName(),
                    weather.getResults().get(0).getLast_update());

            return weather;

        } catch (IOException e) {
            logger.error("网络请求异常，城市：{}", city, e);
            throw new WeatherServiceException("NETWORK_ERROR", "网络请求异常", e);
        }
    }

    private Weather parseWeatherResponse(String responseBody) {
        try {
            return JSON.parseObject(responseBody, Weather.class);
        } catch (JSONException e) {
            logger.error("JSON解析异常", e);
            throw new WeatherServiceException("PARSE_ERROR", "JSON解析异常", e);
        }
    }
}