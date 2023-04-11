package com.annotation.demo.service;

import com.annotation.demo.model.RegisterTransactionEvent;

public interface ITransactionEventService {
    void save(RegisterTransactionEvent registerTransactionEvent);
}
