package com.bocom.dao;

import com.bocom.domain.Classify;

public interface ClassifyDao {
    int deleteByPrimaryKey(String id);

    int insert(Classify record);

    int insertSelective(Classify record);

    Classify selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Classify record);

    int updateByPrimaryKey(Classify record);
}