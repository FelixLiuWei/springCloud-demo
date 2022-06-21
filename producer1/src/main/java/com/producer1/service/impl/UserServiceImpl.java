package com.producer1.service.impl;

import com.common.model.User;
import com.producer1.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Override
    public List <User> getAll() {
        List <User> userList = new ArrayList <>();
        User user = new User();
        user.setAge(1);
        user.setName("Jerry1");
        user.setId(1);
        userList.add(user);
        return userList;
    }
}
