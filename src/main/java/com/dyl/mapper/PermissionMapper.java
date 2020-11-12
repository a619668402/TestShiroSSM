package com.dyl.mapper;

import com.dyl.model.Permission;

import java.util.List;

public interface PermissionMapper {

    public List<Permission> listPermissionsByUserName(String userName);
}
