package org.zzj.common.constants;

public class ApiCode {
    //操作成功
    public static final int OK = 200;
    public static final String OKMSG_DEFAULT = "操作成功";
    public static final String OKMSG_ADD = "添加成功";
    public static final String OKMSG_UPDATE = "修改成功";
    public static final String OKMSG_DELETE = "删除成功";

    //未授权
    public static final int UNAUTHORIZED = 401;
    public static final String UNAUTHMSG_DEFAULT = "未经授权";

    //服务端错误
    public static final int ERROR = 500;
    public static final String ERRMSG_DEFAULT = "服务端错误";
    public static final String ERRMSG_DETAIL = "服务端错误：%s";
    public static final String ERRMSG_DETAIL2 = "服务端错误，异常消息：%s，异常堆栈：%s";
    public static final String ERRMSG_DETAIL3 = "服务端错误，请求数据：%s，返回数据：%s";
    public static final String ERRMSG_LOGIN = "用户名密码不正确";
    public static final String ERRMSG_PARAM_MISSING = "缺少参数";
    public static final String ERRMSG_PARAM_INVILD = "参数无效，格式不正确";
    public static final String ERRMSG_ROOM = "业务错误：%s";
}
