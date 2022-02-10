package com.yf.mydemo.infrastructure.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
@Data
public class ErrorBody {
    /**
     * 异常类名
     */
    private String throwable;
    /**
     * 异常抛出时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS", timezone = "GMT+8")
    private Date throwTime;
    /**
     * 异常消息
     */
    private String message;
    /**
     * 异常堆栈
     */
    private String stackTrace;
    /**
     * 异常源数据
     */
    private Map<String, Object> metadata;
    public ErrorBody(String throwable, Date throwTime, String message, String stackTrace, Map<String, Object> metadata) {
        this.throwable = throwable;
        this.throwTime = throwTime;
        this.message = message;
        this.stackTrace = stackTrace;
        this.metadata = metadata;
    }

    /**
     * 获取ErrorData通过 Throwable 默认不收集堆栈信息
     */
    public static ErrorBody build(Throwable e) {
        return build(e, false);
    }

    /**
     * 获取ErrorData通过 Throwable
     *
     * @param e          异常
     * @param stackTrace 是否收集堆栈
     */
    public static ErrorBody build(Throwable e, boolean stackTrace) {
        return new ErrorBody(
                e.getClass().getName(), new Date(),
                e.getMessage(),
                stackTrace ? collectStackTrace(e) : null,
                new TreeMap<>());
    }

    /**
     * 收集异常堆栈信息
     */
    private static String collectStackTrace(Throwable e) {
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw, true)) {
            e.printStackTrace(pw);
            return sw.toString();
        } catch (IOException ex) {
            return "collectStackTrace exception : " + ex.getMessage();
        }
    }
}
