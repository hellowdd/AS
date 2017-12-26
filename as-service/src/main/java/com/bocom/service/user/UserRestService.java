package com.bocom.service.user;

import com.bocom.dto.Office;
import com.bocom.dto.SessionUserInfo;
import com.bocom.dto.SessionUserInfoDto;
import com.bocom.dto.UserInfoPAPDto;



public interface UserRestService {
	/**
	 * 根据登录名获取用户信息
	 * 
	 * @param loginName
	 *            登录名
	 * @return
	 */
	public SessionUserInfo getUserInfoByLoginName(String loginName);
	
	/**
	 * 从pap获取用户信息
	 * @return
	 */
	public SessionUserInfo getUserInfoFromPAP(UserInfoPAPDto dto);
	/**
     * 从pap获取组织信息
     * @return
     */
	public Office getOrgInfoFromPAP(SessionUserInfoDto dto);
}
