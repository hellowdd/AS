package com.bocom.dto.resp;


/**
 * ClassName:GetAppRespDto <br/>
 * Function: TODO 单个应用列表. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月24日 上午10:26:38 <br/>
 * 
 * @author win
 * @version
 * @since JDK 1.8
 * @see
 */
public class RoleAndAppInfoDto {

	private String roleId;// 角色id

	private String roleCode;// 角色编号

	private String roleName;// 角色名称

	private String appId;// 应用ID

	private String appName;// 应用名称

	private String appVersion;// 应用版本号
	
    private String url;//链接

    private String runStatus;

    private String status;
    
    private String logoWeb;

    private String logoApp;
    private String appScore;
    private String appScoreAdd;
    private String appDesc; //应用描述
    
	public String getUrl() {
	
		return url;
	}

	public void setUrl(String url) {
	
		this.url = url;
	}

	public String getRunStatus() {
	
		return runStatus;
	}

	public void setRunStatus(String runStatus) {
	
		this.runStatus = runStatus;
	}

	public String getStatus() {
	
		return status;
	}

	public void setStatus(String status) {
	
		this.status = status;
	}

	public String getLogoWeb() {
	
		return logoWeb;
	}

	public void setLogoWeb(String logoWeb) {
	
		this.logoWeb = logoWeb;
	}

	public String getLogoApp() {
	
		return logoApp;
	}

	public void setLogoApp(String logoApp) {
	
		this.logoApp = logoApp;
	}

	public String getRoleId() {

		return roleId;
	}

	public void setRoleId(String roleId) {

		this.roleId = roleId;
	}

	public String getRoleCode() {

		return roleCode;
	}

	public void setRoleCode(String roleCode) {

		this.roleCode = roleCode;
	}

	public String getRoleName() {

		return roleName;
	}

	public void setRoleName(String roleName) {

		this.roleName = roleName;
	}

	public String getAppId() {

		return appId;
	}

	public void setAppId(String appId) {

		this.appId = appId;
	}

	public String getAppName() {

		return appName;
	}

	public void setAppName(String appName) {

		this.appName = appName;
	}

	public String getAppVersion() {

		return appVersion;
	}

	public void setAppVersion(String appVersion) {

		this.appVersion = appVersion;
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

}
