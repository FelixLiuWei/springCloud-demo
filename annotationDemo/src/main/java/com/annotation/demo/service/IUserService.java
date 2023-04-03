package com.annotation.demo.service;

import com.common.model.User;

public interface IUserService {
    User insertTestNoRollbackFor();

    User getUser(String userId);
}
