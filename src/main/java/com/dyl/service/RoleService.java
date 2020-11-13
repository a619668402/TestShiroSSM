package com.dyl.service;

import com.dyl.model.Role;
import com.dyl.model.User;

import java.util.List;
import java.util.Set;

public interface RoleService {

    public Set<String> listRoles(String userName);

    public List<Role> listRoles1(String userName);

    public List<Role> listRoles(User user);

    public List<Role> list();
    public void add(Role role);
    public void delete(Long id);
    public Role get(Long id);
    public void update(Role role);
}
