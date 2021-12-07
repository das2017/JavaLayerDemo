package org.zzj.common.constants;

public enum TestEnum {

    A(1, "小程序"),
    B(2, "微信公众号"),
    C(3, "携程"),
    D(4, "美团");

    TestEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int code() {
        return code;
    }

    public String msg() {
        return msg;
    }
}
