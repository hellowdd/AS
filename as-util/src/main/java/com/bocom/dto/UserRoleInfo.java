package com.bocom.dto;

public class UserRoleInfo {
	/**角色部分信息*/
    private Integer roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色编号 1.领导 2.专员
     */
    private String roleCode;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
    
    
}
