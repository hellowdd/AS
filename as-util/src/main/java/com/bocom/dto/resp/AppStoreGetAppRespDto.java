package com.bocom.dto.resp;

import java.util.List;

/**
 * ClassName:AppStoreGetAppRespDto <br/>
 * Function: TODO 获取用户所有权限的应用. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月24日 上午10:23:38 <br/>
 * 
 * @author win
 * @version
 * @since JDK 1.8
 * @see
 */
public class AppStoreGetAppRespDto {

	private Long userId;// 用户ID

	private Integer total;// 总数

	private List<RoleAndAppInfoDto> data;// 用户权限的应用列表

	public List<RoleAndAppInfoDto> getData() {

		return data;
	}

	public void setData(List<RoleAndAppInfoDto> data) {

		this.data = data;
	}

	public Long getUserId() {

		return userId;
	}

	public void setUserId(Long userId) {

		this.userId = userId;
	}

	public Integer getTotal() {

		return total;
	}

	public void setTotal(Integer total) {

		this.total = total;
	}

}
