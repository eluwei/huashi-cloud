package com.huashi.cloud.common.result;



/**
 * 微服务返回数据封装
 * @author peter
 * @version V1.0 创建时间：2015年10月22日 下午4:38:25
 *          Copyright 2015 by PreTang
 */
public class ResultData {

    private static String NOAUTH_CODE = "-1";
    private static String NOAUTH_INFO = "非法访问";
    private static String FAIL_CODE = "0";
    private static String FAIL_INFO = "请求失败";
    private static String SUCCESS_CODE = "1";
    private static String SUCCESS_INFO = "请求成功";
    private static String NOPERMISSIONS_CODE = "-2";
    private static String NOPERMISSIONS_INFO = "无操作权限";

    private String code;
    private String info;
    private Object data;
    public ResultData() {
        this.code = ResultData.SUCCESS_CODE;
        this.info = ResultData.SUCCESS_INFO;
    }
    public ResultData(Object data) {
        this.code = ResultData.SUCCESS_CODE;
        this.info = ResultData.SUCCESS_INFO;
        this.data = data;
    }
    public static ResultData SUCCESS() {
        ResultData appJsonData = new ResultData();
        appJsonData.code = ResultData.SUCCESS_CODE;
        appJsonData.info = ResultData.SUCCESS_INFO;
        return appJsonData;
    }
    public static ResultData SUCCESS(String successMsg) {
        ResultData appJsonData = new ResultData();
        appJsonData.code = ResultData.SUCCESS_CODE;
        appJsonData.info = successMsg;
        return appJsonData;
    }
    public static ResultData FAIL() {
        ResultData appJsonData = new ResultData();
        appJsonData.code = ResultData.FAIL_CODE;
        appJsonData.info = ResultData.FAIL_INFO;
        return appJsonData;
    }
    public static ResultData FAIL(String failMsg) {
        ResultData appJsonData = new ResultData();
        appJsonData.code = ResultData.FAIL_CODE;
        appJsonData.info = failMsg;
        return appJsonData;
    }
    public static ResultData DATA(Object data) {
        ResultData appJsonData = new ResultData();
        appJsonData.code = ResultData.SUCCESS_CODE;
        appJsonData.info = ResultData.SUCCESS_INFO;
        appJsonData.data = data;
        return appJsonData;
    }
    public static ResultData NOAUTH() {
        ResultData appJsonData = new ResultData();
        appJsonData.code = ResultData.NOAUTH_CODE;
        appJsonData.info = ResultData.NOAUTH_INFO;
        return appJsonData;
    }

    public static ResultData NOPERMISSIONS() {
        ResultData appJsonData = new ResultData();
        appJsonData.code = ResultData.NOPERMISSIONS_CODE;
        appJsonData.info = ResultData.NOPERMISSIONS_INFO;
        return appJsonData;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    public boolean successOrFailure(){
        return SUCCESS_CODE.equals(getCode()) ? true : false;
    }
}