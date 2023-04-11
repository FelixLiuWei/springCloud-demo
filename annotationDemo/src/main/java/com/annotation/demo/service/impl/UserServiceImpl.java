package com.annotation.demo.service.impl;

import com.annotation.demo.dao.UserDao;
import com.annotation.demo.service.IStudentService;
import com.annotation.demo.service.IUserService;
import com.common.model.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@CacheConfig(cacheNames = "user")
@Service
public class UserServiceImpl implements IUserService {

    private final UserDao userDao;
    private final IStudentService studentService;

    public UserServiceImpl(UserDao userDao, IStudentService studentService) {
        this.userDao = userDao;
        this.studentService = studentService;
    }

    @Override
//    @Async(value = "myPool")
    @CacheEvict(key = "#result.id", condition = "#result.age > 10")
    @Transactional(rollbackFor = Exception.class)
    public User insertTestNoRollbackFor() {
        User user = new User();
        user.setAge(10);
        user.setId(UUID.randomUUID().toString());
        user.setName("test");
        userDao.insert(user);
        User returnUser = new User();
        returnUser.setName("return");
        returnUser.setAge(11);
        returnUser.setId(UUID.randomUUID().toString());
        studentService.updateStudent();
        return returnUser;
    }

    @Override
    @Cacheable(key="#userId", unless = "#result == null")
    public User getUser(String userId) {
        User returnUser = new User();
        returnUser.setName("return");
        returnUser.setAge(11);
        returnUser.setId(UUID.randomUUID().toString());
        if("4".equals(userId)){
            return null;
        } else {
            return returnUser;
        }
    }

}
