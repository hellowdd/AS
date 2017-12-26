package com.bocom.dao;

import java.util.List;
import java.util.Map;

import com.bocom.domain.Status;
import com.bocom.dto.StatusDto;

public interface StatusDao {
    int deleteByPrimaryKey(String id);

    int insert(Status record);

    int insertSelective(Status record);

    Status selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Status record);

    int updateByPrimaryKey(Status record);
    
    List<StatusDto> queryApplyList(Map map);
    
    int delApply(String id);
    
    int insertApply(Status status);
    
    int auditApply(Map map);
    
    int queryMyApplyCount(Status status);
}