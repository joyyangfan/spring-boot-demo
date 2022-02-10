package com.yf.mydemo.infrastructure.eventbus;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class AbstractSpringEventBus implements IEventBus, ApplicationContextAware {
    private ApplicationContext context;
    /*
    程序启动时容器初始化完成后自动执行自动执行
    * */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
        this.scanConsumer(null);
    }

    @Override
    public void scanConsumer(String packageName) {
        context.getBeansOfType(IEventConsumer.class).forEach((k,v)->{
            this.addConsumer(v);
        });
    }
}
