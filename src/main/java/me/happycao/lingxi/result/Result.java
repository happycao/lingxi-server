package me.happycao.lingxi.result;

import me.happycao.lingxi.constant.Constant;

/**
 * 返回参数封装
 */
public class Result {

    private String code;
    private String msg;
    private Object data;

    private Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success() {
        return new Result(Constant.SUCCESS_CODE, Constant.SUCCESS_MSG);
    }

    public static Result success(Object data) {
        return new Result(Constant.SUCCESS_CODE, Constant.SUCCESS_MSG, data);
    }

    public static Result error() {
        return new Result(Constant.ERROR_CODE, Constant.ERROR_MSG);
    }

    public static Result error(String msg) {
        return new Result(Constant.ERROR_CODE, msg);
    }

    public static Result error(String code, String msg) {
        return new Result(code, msg);
    }

    // 参数为空
    public static Result paramIsNull() {
        return new Result(Constant.ERROR_CODE_PARAM_NULL, Constant.ERROR_MSG_PARAM_NULL);
    }

    // 分页参数为空
    public static Result pageIsNull() {
        return new Result(Constant.ERROR_CODE_PAGE_NULL, Constant.ERROR_MSG_PAGE_NULL);
    }

    // id为空
    public static Result idIsNull() {
        return new Result(Constant.ERROR_CODE_ID_NULL, Constant.ERROR_MSG_ID_NULL);
    }

    // id对应的对象为空
    public static Result objIsNull() {
        return new Result(Constant.ERROR_CODE_OBJ_NULL, Constant.ERROR_MSG_OBJ_NULL);
    }

    // id对应的对象为空
    public static Result objIsNull(String msg) {
        return new Result(Constant.ERROR_CODE_OBJ_NULL, msg);
    }

    public void setCodeAndMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}