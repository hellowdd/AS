package com.bocom.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AppJudgeInfo {
    private String id;

    private String judgeId;

    private String judgeContent;

    private Date createTime;


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(String judgeId) {
        this.judgeId = judgeId;
    }

    public String getJudgeContent() {
        return judgeContent;
    }

    public void setJudgeContent(String judgeContent) {
        this.judgeContent = judgeContent;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}