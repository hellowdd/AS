package com.bocom.dto;

/*****
 * 类名称：AppInfoBase
 * 类描述：app的基本信息
 * 创建人：donghongguang
 * 创建时间：2017年4月25日 下午2:10:24
 * 修改人：
 * 修改时间：
 * @version 1.0.0
 */
public class AppInfoBase
{
    
    private String appId; //应用id
    private String appName; //应用名称
    private String appVersion; //应用版本
    private String logoApp; //logo url
    private String compageName; //公司名称
    private String compageId; //公司id
    private String releaseDate; //发布时间
    private String url; //url
    private String remark; //说明
    private Long clikeRate; //点击量
    private String roleName;// 角色名称
    private String appDesc; //应用描述
    
    private String appScore;
    private String appScoreAdd;
    
    
    public String getAppId()
    {
        return appId;
    }
    public void setAppId(String appId)
    {
        this.appId = appId;
    }
    public String getAppName()
    {
        return appName;
    }
    public void setAppName(String appName)
    {
        this.appName = appName;
    }
    public String getAppVersion()
    {
        return appVersion;
    }
    public void setAppVersion(String appVersion)
    {
        this.appVersion = appVersion;
    }
    public String getLogoApp()
    {
        return logoApp;
    }
    public void setLogoApp(String logoApp)
    {
        this.logoApp = logoApp;
    }
    public String getCompageName()
    {
        return compageName;
    }
    public void setCompageName(String compageName)
    {
        this.compageName = compageName;
    }
    public String getCompageId()
    {
        return compageId;
    }
    public void setCompageId(String compageId)
    {
        this.compageId = compageId;
    }
    public String getReleaseDate()
    {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }
    public String getUrl()
    {
        return url;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    public Long getClikeRate()
    {
        return clikeRate;
    }
    public void setClikeRate(Long clikeRate)
    {
        this.clikeRate = clikeRate;
    }
    
    public String getRoleName()
    {
        return roleName;
    }
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
    
    public String getAppScore()
    {
        return appScore;
    }
    public void setAppScore(String appScore)
    {
        this.appScore = appScore;
    }
    public String getAppScoreAdd()
    {
        return appScoreAdd;
    }
    public void setAppScoreAdd(String appScoreAdd)
    {
        this.appScoreAdd = appScoreAdd;
    }
    
    public String getAppDesc()
    {
        return appDesc;
    }
    public void setAppDesc(String appDesc)
    {
        this.appDesc = appDesc;
    }
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("AppInfoBase [appId=");
        builder.append(appId);
        builder.append(", appName=");
        builder.append(appName);
        builder.append(", appVersion=");
        builder.append(appVersion);
        builder.append(", logoApp=");
        builder.append(logoApp);
        builder.append(", compageName=");
        builder.append(compageName);
        builder.append(", compageId=");
        builder.append(compageId);
        builder.append(", releaseDate=");
        builder.append(releaseDate);
        builder.append(", url=");
        builder.append(url);
        builder.append(", remark=");
        builder.append(remark);
        builder.append(", clikeRate=");
        builder.append(clikeRate);
        builder.append("]");
        return builder.toString();
    }
    
}
