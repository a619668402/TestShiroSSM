package com.dyl.service.impl;

import com.dyl.mapper.UserRoleMapper;
import com.dyl.model.User;
import com.dyl.model.UserRole;
import com.dyl.model.UserRoleExample;
import com.dyl.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void setRoles(User user, long[] roleIds) {
        // 刪除当前用户所有角色
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUidEqualTo(user.getId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);
        for (UserRole userRole : userRoles) {
            userRoleMapper.deleteByPrimaryKey(userRole.getId());
        }

        // 设置新的角色关系
        if (null != roleIds) {
            for (long roleId : roleIds) {
                UserRole userRole = new UserRole();
                userRole.setRid(roleId);
                userRole.setUid(user.getId());
                userRoleMapper.insert(userRole);
            }
        }
    }

    @Override
    public void deleteByUser(long userId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUidEqualTo(userId);
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);
        for (UserRole userRole : userRoles) {
            userRoleMapper.deleteByPrimaryKey(userRole.getId());
        }
    }

    @Override
    public void deleteByRole(long roleId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andRidEqualTo(roleId);
        List<UserRole> urs = userRoleMapper.selectByExample(example);
        for (UserRole ur : urs) {
            userRoleMapper.deleteByPrimaryKey(ur.getId());
        }
    }
}
