package com.bocom.domain;

public class ClickRate {
    private String id;

    private String appId;

    private String versionCode;

    private Long clickRate;

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

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public Long getClickRate() {
        return clickRate;
    }

    public void setClickRate(Long clickRate) {
        this.clickRate = clickRate;
    }

    public ClickRate(String id, String appId, String versionCode, Long clickRate)
    {
        super();
        this.id = id;
        this.appId = appId;
        this.versionCode = versionCode;
        this.clickRate = clickRate;
    }

    public ClickRate()
    {
        super();
    }
    
    
}