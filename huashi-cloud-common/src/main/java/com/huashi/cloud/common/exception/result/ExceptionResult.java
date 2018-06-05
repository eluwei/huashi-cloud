package com.huashi.cloud.common.exception.result;

import com.huashi.cloud.common.result.ResultState;

public class ExceptionResult<T>{

    ResultState state;
    String description;
    T detail;

    public ResultState getState() {
        return state;
    }

    public void setState(ResultState state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getDetail() {
        return detail;
    }

    public void setDetail(T detail) {
        this.detail = detail;
    }

    public ExceptionResult(ResultState state, String description, T detail) {
        this.state = state;
        this.description = description;
        this.detail = detail;
    }
}