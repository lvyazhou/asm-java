package com.platform.bean;

/**
 * 返回结果代码
 * @author lvyz
 */
public class ResultCode {
    /**
     * 系统错误
     */
    public static final int SYS_ERROR = 500;

    /**
     * 运行异常
     */
    public static final int RUNTIM_ERROR = -1;
    /**
     * 登录合法性验证失败
     */
    public static final int LOGIN_CHECK_ERROR = -100;
    /**
     * 验证码过期
     */
    public static final int SIGN_CHECK_ERROR = -99;

    /**
     * 业务逻辑请求出错
     */
    public static final int ERROR = 0;
    /**
     * 业务逻辑请求成功
     */
    public static final int SUCCESS = 1;


    /**
     * 业务逻辑请求出错
     */
    public static final String ERROR_STR_LOWERCASE = "error";
    /**
     * 业务逻辑请求成功
     */
    public static final String SUCCESS_LOWERCASE = "success";


}
