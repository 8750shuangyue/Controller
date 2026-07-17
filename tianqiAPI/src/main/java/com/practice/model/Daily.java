package com.youkeda.practice.model;

public class Daily {
    private String date;
    private String text_day;
    private Integer code_day;
    private String text_night;
    private Integer code_night;
    private Integer high;
    private Integer low;
    private Double rainfall;
    private Double precip;
    private String wind_direction;
    private Integer wind_direction_degree;
    private Double wind_speed;
    private Integer wind_scale;
    private Integer humidity;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText_day() {
        return text_day;
    }

    public void setText_day(String text_day) {
        this.text_day = text_day;
    }

    public Integer getCode_day() {
        return code_day;
    }

    public void setCode_day(Integer code_day) {
        this.code_day = code_day;
    }

    public String getText_night() {
        return text_night;
    }

    public void setText_night(String text_night) {
        this.text_night = text_night;
    }

    public Integer getCode_night() {
        return code_night;
    }

    public void setCode_night(Integer code_night) {
        this.code_night = code_night;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public Double getRainfall() {
        return rainfall;
    }

    public void setRainfall(Double rainfall) {
        this.rainfall = rainfall;
    }

    public Double getPrecip() {
        return precip;
    }

    public void setPrecip(Double precip) {
        this.precip = precip;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public Integer getWind_direction_degree() {
        return wind_direction_degree;
    }

    public void setWind_direction_degree(Integer wind_direction_degree) {
        this.wind_direction_degree = wind_direction_degree;
    }

    public Double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(Double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public Integer getWind_scale() {
        return wind_scale;
    }

    public void setWind_scale(Integer wind_scale) {
        this.wind_scale = wind_scale;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }
}