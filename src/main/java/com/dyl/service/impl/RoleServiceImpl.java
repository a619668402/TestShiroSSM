package com.dyl.service.impl;

import com.dyl.mapper.RoleMapper;
import com.dyl.model.Role;
import com.dyl.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<String> listRoles(String userName) {
        List<Role> roles = roleMapper.listRolesByUserName(userName);
        Set<String> result = new HashSet<>();
        for (Role role : roles) {
            result.add(role.getName());
        }
        return result;
    }
}
