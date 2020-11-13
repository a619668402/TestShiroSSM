package com.dyl.service.impl;

import com.dyl.mapper.PermissionMapper;
import com.dyl.mapper.RolePermissionMapper;
import com.dyl.model.*;
import com.dyl.service.PermissionService;
import com.dyl.service.RoleService;
import com.dyl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public Set<String> listPermissions(String userName) {
        List<Permission> permissions = permissionMapper.listPermissionsByUserName(userName);
        Set<String> result = new HashSet<>();
        for (Permission permission : permissions) {
            result.add(permission.getName());
        }
        return result;
    }

    @Override
    public List<Permission> list() {
        PermissionExample example = new PermissionExample();
        example.setOrderByClause("id asc");
        return permissionMapper.selectByExample(example);
    }

    @Override
    public void add(Permission permission) {
        permissionMapper.insert(permission);
    }

    @Override
    public void delete(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Permission get(Long id) {
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        return permission;
    }

    @Override
    public void update(Permission permission) {
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public List<Permission> list(Role role) {
        List<Permission> permissions = new ArrayList<>();
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        rolePermissionExample.createCriteria().andRidEqualTo(role.getId());
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(rolePermissionExample);

        for (RolePermission rolePermission : rolePermissions) {
            Permission permission = permissionMapper.selectByPrimaryKey(rolePermission.getPid());
            permissions.add(permission);
        }

        return permissions;
    }

    @Override
    public boolean needInterceptor(String requestURI) {
        List<Permission> ps = list();
        for (Permission p : ps) {
            if (p.getUrl().equals(requestURI)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Set<String> listPermissionURLs(String userName) {
        Set<String> result = new HashSet<>();
        List<Role> roles = roleService.listRoles1(userName);

        List<RolePermission> rolePermissions = new ArrayList<>();

        for (Role role : roles) {
            RolePermissionExample example = new RolePermissionExample();
            example.createCriteria().andRidEqualTo(role.getId());
            List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
            rolePermissions.addAll(rps);
        }

        for (RolePermission rolePermission : rolePermissions) {
            Permission p = permissionMapper.selectByPrimaryKey(rolePermission.getPid());
            result.add(p.getUrl());
        }

        return result;
    }
}
