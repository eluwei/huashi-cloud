package com.huashi.cloud.common.result;

public enum ResultState {

    OK(200,"成功"),
    INTERNAL_SERVER_ERROR(500,"服务异常"),
    PARAMETER_INVALID(410,"参数校验出错"),
    BUSINESS_ERROR(420,"业务处理异常"),
    FIELDACCESS_ERROR(430,"实体字段处理异常"),
    NEWINSTANCE_ERROR(440,"实例化实体异常");

    private int stateCode;
    private String description;


    ResultState(int stateCode, String description){
        this.stateCode = stateCode;
        this.description = description;
    }

    public int getStateCode() {
        return stateCode;
    }

    @Override
    public String toString() {
        return Integer.toString(this.stateCode);
    }
}


