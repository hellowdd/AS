
 /**  
 * Project Name:as-util  
 * File Name:StatusDto.java  
 * Package Name:com.bocom.dto  
 * Date:2017年4月24日下午2:46:37  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
*/  
  
package com.bocom.dto;  

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**  
 * ClassName:StatusDto <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年4月24日 下午2:46:37 <br/>  
 * @author   Mr-Wei  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public class StatusDto {
	
	 private String id;

	    private String statusId;

	    private String appId;
	    
	    private String appVersion;

	    private String appName;

	    private String remark;

	    private String roleIds;

	    private String statusRemark;

	    private String createByname;

	    private String createBy;

	    private Date createDate;

	    private String updateBy;

	    private Date updateDate;

	    private String delFlag;
	    
	    private String statusName;

		public String getId() {
		
			return id;
		}

		public void setId(String id) {
		
			this.id = id;
		}

		public String getStatusId() {
		
			return statusId;
		}

		public void setStatusId(String statusId) {
		
			this.statusId = statusId;
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

		public String getRemark() {
		
			return remark;
		}

		public void setRemark(String remark) {
		
			this.remark = remark;
		}

		public String getRoleIds() {
		
			return roleIds;
		}

		public void setRoleIds(String roleIds) {
		
			this.roleIds = roleIds;
		}

		public String getStatusRemark() {
		
			return statusRemark;
		}

		public void setStatusRemark(String statusRemark) {
		
			this.statusRemark = statusRemark;
		}

		public String getCreateByname() {
		
			return createByname;
		}

		public void setCreateByname(String createByname) {
		
			this.createByname = createByname;
		}

		public String getCreateBy() {
		
			return createBy;
		}

		public void setCreateBy(String createBy) {
		
			this.createBy = createBy;
		}

		 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
		public Date getCreateDate() {
		
			return createDate;
		}

		public void setCreateDate(Date createDate) {
		
			this.createDate = createDate;
		}

		public String getUpdateBy() {
		
			return updateBy;
		}

		public void setUpdateBy(String updateBy) {
		
			this.updateBy = updateBy;
		}

		 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
		public Date getUpdateDate() {
		
			return updateDate;
		}

		public void setUpdateDate(Date updateDate) {
		
			this.updateDate = updateDate;
		}

		public String getDelFlag() {
		
			return delFlag;
		}

		public void setDelFlag(String delFlag) {
		
			this.delFlag = delFlag;
		}

		public String getStatusName() {
		
			return statusName;
		}

		public void setStatusName(String statusName) {
		
			this.statusName = statusName;
		}
	    
	    

}
  
