package com.bocom.dao;

import com.bocom.domain.ClassifyConf;

public interface ClassifyConfDao {
    int deleteByPrimaryKey(String id);

    int insert(ClassifyConf record);

    int insertSelective(ClassifyConf record);

    ClassifyConf selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ClassifyConf record);

    int updateByPrimaryKey(ClassifyConf record);
}