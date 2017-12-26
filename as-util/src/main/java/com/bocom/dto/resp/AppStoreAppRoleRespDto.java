package com.bocom.dto.resp;

/**
 * ClassName:AppStoreAppRoleRespDto <br/>
 * Function: TODO 返回角色. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月24日 下午3:22:59 <br/>
 * 
 * @author win
 * @version
 * @since JDK 1.8
 * @see
 */
public class AppStoreAppRoleRespDto {

	private String id;// 应用角色的主键

	private String roleCode;// 角色编号

	private String roleName;// 角色名称

	private String appId;

	private String version;

	private Integer groupLevels;

	private Integer userNum;

	private Integer enpowerType;

	private String instruction;

	private Integer type;

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
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

	public String getVersion() {

		return version;
	}

	public void setVersion(String version) {

		this.version = version;
	}

	public Integer getGroupLevels() {

		return groupLevels;
	}

	public void setGroupLevels(Integer groupLevels) {

		this.groupLevels = groupLevels;
	}

	public Integer getUserNum() {

		return userNum;
	}

	public void setUserNum(Integer userNum) {

		this.userNum = userNum;
	}

	public Integer getEnpowerType() {

		return enpowerType;
	}

	public void setEnpowerType(Integer enpowerType) {

		this.enpowerType = enpowerType;
	}

	public String getInstruction() {

		return instruction;
	}

	public void setInstruction(String instruction) {

		this.instruction = instruction;
	}

	public Integer getType() {

		return type;
	}

	public void setType(Integer type) {

		this.type = type;
	}

}
