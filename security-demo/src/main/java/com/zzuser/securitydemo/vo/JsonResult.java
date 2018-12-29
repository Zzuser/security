package com.zzuser.securitydemo.vo;

import lombok.Data;

/**
 * Json数据统一包装类
 * @author zzhaoctr
 */
@Data
public class JsonResult {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态文本
     */
    private String status;
    /**
     * 消息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;

    private JsonResult(Integer code, String status, String msg, Object data) {
        this.code = code;
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult ok() {
        return new JsonResult(1, "ok", "success", null);
    }

    public static JsonResult ok(String msg) {
        return new JsonResult(1, "ok", msg, null);
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(1, "ok", "success", data);
    }

    public static JsonResult ok(String msg,Object data) {
        return new JsonResult(1, "ok", msg, data);
    }

    public static JsonResult error() {
        return new JsonResult(0, "error", "error", null);
    }

    public static JsonResult error(String msg) {
        return new JsonResult(0, "error", msg, null);
    }


}
