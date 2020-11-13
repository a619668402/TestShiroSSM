package com.dyl.mapper;

import com.dyl.model.Permission;
import com.dyl.model.PermissionExample;
import java.util.List;

public interface PermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> listPermissionsByUserName(String userName);
}