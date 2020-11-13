package com.macie.dto;

import com.macie.common.ResponseCode;

/**
 * json数据包装类
 * @author Macie
 * @date 2020/10/29 -11:20
 */
public class JsonResponse<T> {
    private Integer code;
    private String message;
    private T data;

    private JsonResponse(Integer code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    private JsonResponse(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    private JsonResponse(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public JsonResponse(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> JsonResponse<T> responseOK() {
        return new JsonResponse<T>(ResponseCode.CODE_SUCCESS);
    }
    public static <T> JsonResponse<T> responseOK(T data) {
        return new JsonResponse<T>(ResponseCode.CODE_SUCCESS, data);
    }

    public static <T> JsonResponse<T> responseFailed(String msg) {
        return new JsonResponse<T>(ResponseCode.CODE_FAILED, msg);
    }
    public static <T> JsonResponse<T> responseFailed(String msg, T data) {
        return new JsonResponse<T>(ResponseCode.CODE_FAILED, msg, data);
    }

    public static <T> JsonResponse<T> response(Integer code, String msg, T data) {
        return new JsonResponse<T>(code, msg, data);
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "code=" + code +
                ", msg='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
