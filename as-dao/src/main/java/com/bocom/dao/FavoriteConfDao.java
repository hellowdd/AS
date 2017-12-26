package com.bocom.dao;

import com.bocom.domain.FavoriteConf;

public interface FavoriteConfDao {
    int deleteByPrimaryKey(String id);

    int insert(FavoriteConf record);

    int insertSelective(FavoriteConf record);

    FavoriteConf selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FavoriteConf record);

    int updateByPrimaryKey(FavoriteConf record);
}