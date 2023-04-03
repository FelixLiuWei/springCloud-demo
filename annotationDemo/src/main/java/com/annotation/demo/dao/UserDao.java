package com.annotation.demo.dao;

import com.common.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User insert(User user);
}
