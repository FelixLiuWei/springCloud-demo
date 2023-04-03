package com.annotation.demo.service.impl;

import com.annotation.demo.service.IStudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStudent() {
        System.out.println("123");
    }
}
