package com.dyl.mapper;

import com.dyl.model.Role;
import com.dyl.model.RoleExample;

import java.util.List;

public interface RoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> listRolesByUserName(String userName);
}