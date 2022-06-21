package com.producer.service.impl;

import com.common.model.User;
import com.producer.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Override
    public List <User> getAll() {
        List <User> userList = new ArrayList <>();
        User user = new User();
        user.setAge(0);
        user.setName("Jerry0");
        user.setId(0);
        userList.add(user);
        return userList;
    }
}
