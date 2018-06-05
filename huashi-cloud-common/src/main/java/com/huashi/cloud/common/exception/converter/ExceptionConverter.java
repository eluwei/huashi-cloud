package com.huashi.cloud.common.exception.converter;

public interface ExceptionConverter<T> {
    T convert2ResultData();
}
