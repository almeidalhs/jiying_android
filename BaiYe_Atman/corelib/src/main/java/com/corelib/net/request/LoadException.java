package com.corelib.net.request;

/**
 * Created by GodXj on 2015/12/18.
 */
public class LoadException extends Exception {

    public static final String PARAMERROR = "参数错误";
    public static final String MALFORMEDURLEXCEPTION = "URL异常";
    public static final String UNKOWNEXCPTION = "未知异常";
    public static final String HTTPCODEERROR = "返回码";

    public LoadException() {
        super();
    }

    public LoadException(String detailMessage) {
        super(detailMessage);
    }

    public LoadException(String detailMessage, Throwable cause) {
        super(detailMessage, cause);
    }

    public LoadException(Throwable cause) {
        super(cause);
    }

}
