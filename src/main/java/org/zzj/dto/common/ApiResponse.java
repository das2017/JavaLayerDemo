package org.zzj.dto.common;

import org.zzj.common.constants.ApiCode;
import lombok.Data;

@Data
public class ApiResponse<T> {
    /**
     * 响应码
     */
    private int code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public ApiResponse() {
        this.code = ApiCode.OK;
        this.msg = ApiCode.OKMSG_DEFAULT;
    }

    public ApiResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
