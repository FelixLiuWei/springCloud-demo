package com.annotation.demo.service.impl;

import com.annotation.demo.model.RegisterTransactionEvent;
import com.annotation.demo.service.ITransactionEventService;
import com.annotation.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TransactionEventServiceImpl implements ITransactionEventService {

    @Resource
    private ApplicationContext applicationContext;

    @Autowired
    private IUserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RegisterTransactionEvent registerTransactionEvent) {
        userService.insertTestNoRollbackFor();
        applicationContext.publishEvent(registerTransactionEvent);
    }
}
