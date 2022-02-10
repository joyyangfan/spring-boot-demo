package com.yf.mydemo.domain.event;

import com.google.common.eventbus.Subscribe;
import com.yf.mydemo.infrastructure.eventbus.IEventConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyEventHandler implements IEventConsumer<MyEventData> {

    @Override
    @Subscribe
    public void consumer(MyEventData event) {
        log.info("consumer:"+event.getName());
        throw new RuntimeException("xxx");
    }
}
