package com.yf.mydemo.infrastructure.eventbus;

public interface IEventBus {
    /**
     * 发布事件
     * @param event 事件实体
     */
    void post(Object event);
    /**
     * 发布事件
     * @param event 事件实体
     */
    void postAsync(Object event);

    /**
     * 添加消费者
     * @param obj 消费者对象，默认以class为key
     */
    void addConsumer(Object obj);

    /**
     * 移除消费者
     * @param obj 消费者对象，默认以class为key
     */
    void removeConsumer(Object obj);

    /**
     * 扫描消费者
     * @param packageName 扫描包
     */
    void scanConsumer(String packageName);
}
