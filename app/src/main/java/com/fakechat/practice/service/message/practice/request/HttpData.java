package com.fakechat.practice.service.message.practice.request;


public class HttpData<T> {

    /** 返回码 */
    private int errorCode;
    /** 提示语 */
    private String errorMsg;
    /** 数据 */
    private T data;

    public int getCode() {
        return errorCode;
    }

    public String getMessage() {
        return errorMsg;
    }

    public T getData() {
        return data;
    }

    /**
     * 是否请求成功
     */
    public boolean isRequestSucceed() {
        return errorCode == 0;
    }

    /**
     * 是否 Token 失效
     */
    public boolean isTokenFailure() {
        return errorCode == 1001;
    }
}