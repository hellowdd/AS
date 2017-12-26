package com.bocom.dao;

import com.bocom.domain.StatusConf;

public interface StatusConfDao {
    int deleteByPrimaryKey(String id);

    int insert(StatusConf record);

    int insertSelective(StatusConf record);

    StatusConf selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StatusConf record);

    int updateByPrimaryKey(StatusConf record);
}