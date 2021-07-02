package com.amos.api.model;

import java.io.Serializable;

/**
 * CommonResponse
 *
 * @author <a href="mailto:daoyuan0626@gmail.com">amos.wang</a>
 * @date 2021/5/29
 */
public class CommonResponse<T> implements Serializable {

    private static final long serialVersionUID = -4523369753759006522L;

    private String code;
    private String message;
    private T body;

    public CommonResponse() {
        this(null);
    }

    public CommonResponse(T body) {
        this.code = "200";
        this.message = "成功!";
        this.body = body;
    }

    public CommonResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }
}
