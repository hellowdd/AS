package com.bocom.dao;

import java.util.List;
import java.util.Map;

import com.bocom.domain.ClickRate;

public interface ClickRateDao {
    int deleteByPrimaryKey(String id);

    int insert(ClickRate record);

    int insertSelective(ClickRate record);

    ClickRate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ClickRate record);

    int updateByPrimaryKey(ClickRate record);
    
    int queryClickRateSize(ClickRate clickRate);
    
    int upClickRate(ClickRate clickRate);

	public List<ClickRate> queryByAppid(Map<String,Object> map);
}