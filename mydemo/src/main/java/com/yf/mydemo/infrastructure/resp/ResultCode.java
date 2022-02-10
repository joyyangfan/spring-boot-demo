package com.yf.mydemo.infrastructure.resp;

/**
 * 结果码 接口
 */
public interface ResultCode {

    /**
     * 获取业务码
     *
     * @return 业务码
     */
    String getCode();

    /**
     * 获取业务描述
     *
     * @return 业务描述
     */
    String getMessage();

    /**
     * 获取业务码描述
     *
     * @return 业务码描述
     */
    default String getDesc() {
        return getMessage();
    }

}