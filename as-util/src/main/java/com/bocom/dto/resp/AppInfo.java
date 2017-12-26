package com.bocom.dto.resp;

import java.util.Date;

public class AppInfo {
	private String id;// 主键
	private String appId;// 应用ID(必填)
	private String appName;// 应用名称(不填)
	private String appDesc;// 应用描述(不填)
	private String logoWeb;// logo 大
	private String logoApp;// logo 小
	private String version;// 版本
	private String storeLocation;// 部署包存储位置
	private String appOrg;// 组织机构
	private String appOrgName;// 组织机构名称
	private String uploadBy;// 上传人员
	private String uploadByName;// 上传人员名称
	private Integer deployNodeNum;// 部署节点数量
	private String companyId;// 公司Id
	private String companyCode;// 公司code
	private String companyName;// 公司名称
	private String appCategory;// 应用类别
	private String bizCategory;// 业务类别
	private String appType;// 应用类型（PAP使用）
	private String aaType;// 类型 shiro/ur(default)/hybrid
	private String url;// 访问链接
	private Integer runStatus;// 运行状态
	private Integer status;// 管理状态
	private String createBy;// 创建人(不填)
	private String createTime;// 创建时间(不填)
	private Long click_rate;


	public Long getClick_rate() {
		return click_rate;
	}

	public void setClick_rate(Long click_rate) {
		this.click_rate = click_rate;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

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

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppDesc() {
		return appDesc;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}

	public String getAppOrg() {
		return appOrg;
	}

	public void setAppOrg(String appOrg) {
		this.appOrg = appOrg;
	}

	public Integer getDeployNodeNum() {
		return deployNodeNum;
	}

	public void setDeployNodeNum(Integer deployNodeNum) {
		this.deployNodeNum = deployNodeNum;
	}

	public String getAppCategory() {
		return appCategory;
	}

	public void setAppCategory(String appCategory) {
		this.appCategory = appCategory;
	}

	public String getBizCategory() {
		return bizCategory;
	}

	public void setBizCategory(String bizCategory) {
		this.bizCategory = bizCategory;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getUploadBy() {
		return uploadBy;
	}

	public void setUploadBy(String uploadBy) {
		this.uploadBy = uploadBy;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(Integer runStatus) {
		this.runStatus = runStatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAppOrgName() {
		return appOrgName;
	}

	public void setAppOrgName(String appOrgName) {
		this.appOrgName = appOrgName;
	}

	public String getUploadByName() {
		return uploadByName;
	}

	public void setUploadByName(String uploadByName) {
		this.uploadByName = uploadByName;
	}

	public String getAaType() {
		return aaType;
	}

	public void setAaType(String aaType) {
		this.aaType = aaType;
	}

	public AppInfo() {
		super();
	}

	public AppInfo(String id, String appId, String appName, String appDesc,
			String logoWeb, String logoApp, String version,
			String storeLocation, String appOrg, String appOrgName,
			String uploadBy, String uploadByName, Integer deployNodeNum,
			String companyId, String companyCode, String companyName,
			String appCategory, String bizCategory, String appType,
			String aaType, String url, Integer runStatus, Integer status,
			String createBy, String createTime,Long click_rate) {
		super();
		this.id = id;
		this.appId = appId;
		this.appName = appName;
		this.appDesc = appDesc;
		this.logoWeb = logoWeb;
		this.logoApp = logoApp;
		this.version = version;
		this.storeLocation = storeLocation;
		this.appOrg = appOrg;
		this.appOrgName = appOrgName;
		this.uploadBy = uploadBy;
		this.uploadByName = uploadByName;
		this.deployNodeNum = deployNodeNum;
		this.companyId = companyId;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.appCategory = appCategory;
		this.bizCategory = bizCategory;
		this.appType = appType;
		this.aaType = aaType;
		this.url = url;
		this.runStatus = runStatus;
		this.status = status;
		this.createBy = createBy;
		this.createTime = createTime;
		this.click_rate=click_rate;
	}
}
