package com.dyl.mapper;

import com.dyl.model.Role;

import java.util.List;

public interface RoleMapper {

    public List<Role> listRolesByUserName(String userName);
}
