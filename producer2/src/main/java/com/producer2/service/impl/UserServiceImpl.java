package com.producer2.service.impl;

import com.common.model.User;
import com.producer2.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Override
    public List <User> getAll() {
        List <User> userList = new ArrayList <>();
        User user = new User();
        user.setAge(2);
        user.setName("Jerry2");
        user.setId("2");
        userList.add(user);
        return userList;
    }
}
