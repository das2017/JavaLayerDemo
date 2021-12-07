package org.zzj.common.custom;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiException extends RuntimeException {
    private Integer code;
    private String message;
    private Object data;

    public ApiException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ApiException(Integer code, String message, Object data) {
        super(message);
        this.code = code;
        this.message = message;
        this.data = data;
    }
}