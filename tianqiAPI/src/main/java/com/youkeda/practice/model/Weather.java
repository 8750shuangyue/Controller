package com.youkeda.practice.model;

import java.util.List;

/**
 * 天气模型
 */
public class Weather {

    // 天气详情
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
