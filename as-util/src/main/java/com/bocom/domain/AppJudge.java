package com.bocom.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AppJudge {
    private String id;

    private String appId;

    private String appVersion;

    private String appName;

    private String createBy;

    private String createByname;

    private Date createDate;

    private Integer appScore;

    private String appJudge;

    private String appJudgeAdd;

    private Integer appScoreAdd;

    private Date judgeAddTime;

    private Date updateTime;

    private String isPublic;

    private String delFlag;
    
    private List<AppJudgeInfo> appJudgeInfo;
    private Integer pageSize;
    private Integer pageNum;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateByname() {
        return createByname;
    }

    public void setCreateByname(String createByname) {
        this.createByname = createByname;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getAppScore() {
        return appScore;
    }

    public void setAppScore(Integer appScore) {
        this.appScore = appScore;
    }

    public String getAppJudge() {
        return appJudge;
    }

    public void setAppJudge(String appJudge) {
        this.appJudge = appJudge;
    }

    public String getAppJudgeAdd() {
        return appJudgeAdd;
    }

    public void setAppJudgeAdd(String appJudgeAdd) {
        this.appJudgeAdd = appJudgeAdd;
    }

    public Integer getAppScoreAdd() {
        return appScoreAdd;
    }

    public void setAppScoreAdd(Integer appScoreAdd) {
        this.appScoreAdd = appScoreAdd;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getJudgeAddTime() {
        return judgeAddTime;
    }

    public void setJudgeAddTime(Date judgeAddTime) {
        this.judgeAddTime = judgeAddTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public List<AppJudgeInfo> getAppJudgeInfo()
    {
        return appJudgeInfo;
    }

    public void setAppJudgeInfo(List<AppJudgeInfo> appJudgeInfo)
    {
        this.appJudgeInfo = appJudgeInfo;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public Integer getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(Integer pageNum)
    {
        this.pageNum = pageNum;
    }
    
}