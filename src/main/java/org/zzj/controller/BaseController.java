package org.zzj.controller;

import org.zzj.common.constants.ApiCode;
import org.zzj.dto.common.ApiResponse;

public class BaseController {
    public ApiResponse success() {
        return new ApiResponse(ApiCode.OK, ApiCode.OKMSG_DEFAULT);
    }

    public ApiResponse success(String message) {
        return new ApiResponse(ApiCode.OK, message);
    }

    public ApiResponse success(Object data) {
        return new ApiResponse(ApiCode.OK, ApiCode.OKMSG_DEFAULT, data);
    }

    public ApiResponse success(String message, Object data) {
        return new ApiResponse(ApiCode.OK, message, data);
    }
}