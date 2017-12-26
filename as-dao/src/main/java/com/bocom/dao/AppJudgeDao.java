package com.bocom.dao;

import java.util.List;
import java.util.Map;

import com.bocom.domain.AppJudge;

public interface AppJudgeDao {
    int deleteByPrimaryKey(String id);

    int insert(AppJudge record);

    int insertSelective(AppJudge record);

    AppJudge selectByPrimaryKey(String id);

    int appendAppJudge(AppJudge record);

    int updateByPrimaryKey(AppJudge record);
    
    String queryAppScoreAdd(AppJudge record);
    
    List<AppJudge> selectJudgeInfo(Map map);
    List<AppJudge> selectJudgeScore(Map map);
    
    List<AppJudge> selectJudgeInfoAll(Map map);
}