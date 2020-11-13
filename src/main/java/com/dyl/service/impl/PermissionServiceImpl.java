package com.dyl.service.impl;

import com.dyl.mapper.PermissionMapper;
import com.dyl.model.Permission;
import com.dyl.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<String> listPermissions(String userName) {
        List<Permission> permissions = permissionMapper.listPermissionsByUserName(userName);
        Set<String> result = new HashSet<>();
        for (Permission permission : permissions) {
            result.add(permission.getName());
        }
        return result;
    }
}
