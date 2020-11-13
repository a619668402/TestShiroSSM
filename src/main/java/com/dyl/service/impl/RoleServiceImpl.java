package com.dyl.service.impl;

import com.dyl.mapper.RoleMapper;
import com.dyl.mapper.UserRoleMapper;
import com.dyl.model.*;
import com.dyl.service.RoleService;
import com.dyl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserService userService;

    @Override
    public Set<String> listRoles(String userName) {
        List<Role> roles = roleMapper.listRolesByUserName(userName);
        Set<String> result = new HashSet<>();
        for (Role role : roles) {
            result.add(role.getName());
        }
        return result;
    }

    @Override
    public List<Role> listRoles1(String userName) {
        List<Role> roles = roleMapper.listRolesByUserName(userName);
        return roles;
    }

    @Override
    public List<Role> listRoles(User user) {
        List<Role> roles = new ArrayList<>();
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUidEqualTo(user.getId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);
        for (UserRole userRole : userRoles) {
            Role role = roleMapper.selectByPrimaryKey(userRole.getRid());
            roles.add(role);
        }
        return roles;
    }

    @Override
    public List<Role> list() {
        RoleExample example = new RoleExample();
        example.setOrderByClause("id asc");
        List<Role> roles = roleMapper.selectByExample(example);
        return roles;
    }

    @Override
    public void add(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void delete(Long id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Role get(Long id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }

    @Override
    public void update(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }
}
