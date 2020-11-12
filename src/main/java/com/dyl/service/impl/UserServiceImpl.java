package com.dyl.service.impl;

import com.dyl.mapper.UserMapper;
import com.dyl.model.User;
import com.dyl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String getPassword(String name) {
        User user = userMapper.getUser(name);
        if (user != null)
            return user.getPassword();
        return null;
    }
}
