
 /**  
 * Project Name:as-util  
 * File Name:ReplaceAppRoleDto.java  
 * Package Name:com.bocom.dto  
 * Date:2017年4月25日下午1:40:12  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
*/  
  
package com.bocom.dto;  
/**  
 * ClassName:ReplaceAppRoleDto <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年4月25日 下午1:40:12 <br/>  
 * @author   Mr-Wei  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public class ReplaceAppRoleDto {
	
	private String appId;
	
	private String version;
	
	private String roleCode;
	
	private String orgCode;
	
	private String userId;

	public String getAppId() {
	
		return appId;
	}

	public void setAppId(String appId) {
	
		this.appId = appId;
	}

	public String getVersion() {
	
		return version;
	}

	public void setVersion(String version) {
	
		this.version = version;
	}

	public String getRoleCode() {
	
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
	
		this.roleCode = roleCode;
	}

	public String getOrgCode() {
	
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
	
		this.orgCode = orgCode;
	}

	public String getUserId() {
	
		return userId;
	}

	public void setUserId(String userId) {
	
		this.userId = userId;
	}
	
	public ReplaceAppRoleDto(){
		
	}

	
	public ReplaceAppRoleDto(String appId, String version, String roleCode,
			String orgCode, String userId) {
		super();
		this.appId = appId;
		this.version = version;
		this.roleCode = roleCode;
		this.orgCode = orgCode;
		this.userId = userId;
	}
	
   
	
	
	
	

}
  
