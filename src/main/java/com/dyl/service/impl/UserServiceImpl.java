package com.dyl.service.impl;

import com.dyl.mapper.UserMapper;
import com.dyl.mapper.UserRoleMapper;
import com.dyl.model.User;
import com.dyl.model.UserExample;
import com.dyl.model.UserRole;
import com.dyl.service.UserRoleService;
import com.dyl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public String getPassword(String name) {
        User user = userMapper.getUser(name);
        if (user != null)
            return user.getPassword();
        return null;
    }

    @Override
    public User getByName(String name) {
        return userMapper.getUser(name);
    }

    @Override
    public List<User> list() {
        UserExample example = new UserExample();
        example.setOrderByClause("id asc");
        return userMapper.selectByExample(example);
    }

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteByPrimaryKey(id);
        userRoleService.deleteByUser(id);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User get(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
