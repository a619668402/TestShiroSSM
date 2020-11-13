package com.dyl.service;

import com.dyl.model.User;

import java.util.List;

public interface UserService {

    public String getPassword(String name);

    public User getByName(String name);

    public List<User> list();

    public void addUser(User user);

    public void deleteUser(Long id);

    public void update(User user);

    public User get(Long id);
}
