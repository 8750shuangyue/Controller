package com.youkeda.practice.service;

import com.youkeda.practice.model.Weather;

public interface WeatherService {

    Weather getWeatherByCity(String city);
}