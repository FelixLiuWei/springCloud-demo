package com.annotation.demo.listener;

import com.annotation.demo.model.RegisterTransactionEvent;
import com.annotation.demo.model.SucceedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.concurrent.TimeUnit;

@Component
public class RegisterTransactionEventListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleNotifyEvent1(RegisterTransactionEvent registerTransactionEvent) throws InterruptedException{
        System.out.println("开始处理1事件: " + registerTransactionEvent.getUsername());
        TimeUnit.SECONDS.sleep(5);
    }

    @TransactionalEventListener(condition = "#registerTransactionEvent.username.equals(\"Felix2\")")
    public void handleNotifyEvent2(RegisterTransactionEvent registerTransactionEvent) {
        System.out.println("开始处理2事件: " + registerTransactionEvent.getUsername());
    }

    @TransactionalEventListener(classes = SucceedEvent.class)
    public void handleNotifyEvent3(SucceedEvent succeedEvent) {
        System.out.println("开始处理3事件: " + succeedEvent.getUsername());
    }
}
