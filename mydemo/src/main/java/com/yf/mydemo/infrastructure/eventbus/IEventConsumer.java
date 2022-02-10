package com.yf.mydemo.infrastructure.eventbus;

public interface IEventConsumer<T> {
    /**
     * 消费者事件
     * @param event 事件
     */
    void consumer(T event);
}
