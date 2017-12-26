package com.bocom.dao;

import com.bocom.domain.AppJudgeInfo;

public interface AppJudgeInfoDao {
    int deleteByPrimaryKey(String id);

    int insert(AppJudgeInfo record);

    int insertSelective(AppJudgeInfo record);

    AppJudgeInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AppJudgeInfo record);

    int updateByPrimaryKey(AppJudgeInfo record);
}