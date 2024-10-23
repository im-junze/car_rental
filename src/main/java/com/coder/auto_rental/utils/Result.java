package com.coder.auto_rental.utils;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Result<T> {
    private Integer code;
    private String message;
    private Boolean success;
    private T data;

    public Result() {
    }

    public static <T> Result<T> success() {
        return new Result<T>().setSuccess(true).setCode(ResultCode.SUCCESS).setMessage("操作成功");
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>().setSuccess(true).setCode(ResultCode.SUCCESS).setMessage("操作成功").setData(data);
    }

    public static <T> Result<T> fail() {
        return new Result<T>().setSuccess(false).setCode(ResultCode.ERROR).setMessage("操作失败");
    }

    public static <T> Result<T> fail(T data) {
        return new Result<T>().setSuccess(false).setCode(ResultCode.ERROR).setMessage("操作失败").setData(data);
    }
}
