package com.bocom.dao;

import com.bocom.domain.RoleConf;

public interface RoleConfDao {
    int deleteByPrimaryKey(String id);

    int insert(RoleConf record);

    int insertSelective(RoleConf record);

    RoleConf selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleConf record);

    int updateByPrimaryKey(RoleConf record);
}