package com.dyl.service;

import com.dyl.model.User;

public interface UserRoleService {

    public void setRoles(User user, long[] roleIds);

    public void deleteByUser(long userId);

    public void deleteByRole(long roleId);
}
