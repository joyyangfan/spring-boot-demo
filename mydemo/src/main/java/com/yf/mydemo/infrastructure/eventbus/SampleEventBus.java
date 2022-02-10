package com.yf.mydemo.infrastructure.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class SampleEventBus extends AbstractSpringEventBus implements SubscriberExceptionHandler {
    private AsyncEventBus asynceventBus;
    private EventBus eventBus = new EventBus(this);
    public SampleEventBus() {
        //异步事件配置线程池
        asynceventBus = new AsyncEventBus(new ThreadPoolExecutor(1, 10,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>()), this);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }
    @Override
    public void postAsync(Object event) {
        asynceventBus.post(event);
    }
    @Override
    public void addConsumer(Object obj) {
        eventBus.register(obj);
        asynceventBus.register(obj);
    }

    @Override
    public void removeConsumer(Object obj) {
        eventBus.unregister(obj);
        asynceventBus.unregister(obj);
    }

    @SneakyThrows
    @Override
    public void handleException(Throwable exception, SubscriberExceptionContext context) {
        log.error("user event handler exception", exception);
    }
}
