package com.bocom.dao;

import java.util.List;
import java.util.Map;

import com.bocom.domain.Favorite;
import com.bocom.dto.FavoriteDto;

public interface FavoriteDao {
    int deleteByPrimaryKey(String id);

    int insert(Favorite record);

    int insertSelective(Favorite record);

    Favorite selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Favorite record);

    int updateByPrimaryKey(Favorite record);
    
    List<FavoriteDto> queryFavoriteList(Map map);
    
    int delFavorite(String str);
    
    int insertFavorite(Favorite favorite);
    
    int queryMyFavoriteSize(Favorite favorite);
}