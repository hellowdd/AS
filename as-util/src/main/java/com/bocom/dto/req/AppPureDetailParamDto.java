package com.bocom.dto.req;

public class AppPureDetailParamDto {
	private String id;
	private String appId;

	public AppPureDetailParamDto() {
		super();
	}

	public AppPureDetailParamDto(String id, String appId) {
		super();
		this.id = id;
		this.appId = appId;
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
}
