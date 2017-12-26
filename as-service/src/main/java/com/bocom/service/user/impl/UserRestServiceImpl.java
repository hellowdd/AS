package com.bocom.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bocom.dto.Office;
import com.bocom.dto.OrgRoleInfo;
import com.bocom.dto.SessionUserInfo;
import com.bocom.dto.SessionUserInfoDto;
import com.bocom.dto.UserInfoPAPDto;
import com.bocom.dto.UserRoleInfo;
import com.bocom.dto.resp.SessionUserRespDto;
import com.bocom.dto.resp.UserOrgRespDto;
import com.bocom.service.user.UserRestService;
import com.bocom.util.HttpClientUtil;

@Service
public class UserRestServiceImpl implements UserRestService {

	private static ObjectMapper objectMapper = new ObjectMapper();
	private static Logger LOG = LoggerFactory
			.getLogger(UserRestServiceImpl.class);

	@Value("${rest.user.getUserInfoByLoginName.url}")
	private String getUserInfoByLoginNameUrl;
	
	@Value("${rest.user.getUserInfoFromPAP.url}")
	private String getUserInfoFromPAPUrl;
	@Value("${rest.org.queryOrgByUsername.url}")
	private String getOrgByUsernameUrl;

	@Override
	public SessionUserInfo getUserInfoByLoginName(String loginName) {
		SessionUserInfo sessionUserInfo = null;
		try {
			SessionUserInfoDto dto = getResponseData(getUserInfoByLoginNameUrl
					+ loginName);
			sessionUserInfo = createBean(dto);
		} catch (Exception e) {
			LOG.error("UserRestServiceImpl getUserInfoByLoginName error", e);
		}
		return sessionUserInfo;
	}
	
	@Override
	public SessionUserInfo getUserInfoFromPAP(UserInfoPAPDto paramDto) {
		SessionUserInfo sessionUserInfo = null;
		try {
			String json = objectMapper.writeValueAsString(paramDto);
			String data = HttpClientUtil
					.postBase64(getUserInfoFromPAPUrl, json);
			LOG.info("response data: " + data);
			objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			SessionUserRespDto responseDto = objectMapper.readValue(data,
					SessionUserRespDto.class);
			sessionUserInfo = createBean(responseDto.getData());
		} catch (Exception e) {
			LOG.error("UserRestServiceImpl getUserInfoFromPAP error", e);
		}
		return sessionUserInfo;
	}

	private SessionUserInfo createBean(SessionUserInfoDto dto) {
		SessionUserInfo data = new SessionUserInfo();
		if (dto != null) {
			data.setUserId(dto.getUserId());
			data.setUserName(dto.getUserName());
			data.setPoliceCode(dto.getPoliceCode());
			data.setPoliceName(dto.getPoliceName());
			List<OrgRoleInfo> roleUserInfoList = dto.getOrgRoleUserInfoList();
			if (roleUserInfoList != null && roleUserInfoList.size() > 0) {
				OrgRoleInfo info = roleUserInfoList.get(0);
				data.setRoleOrgCode(info.getRoleOrgCode());
				data.setRoleOrgName(info.getRoleOrgName());
				data.setOrgCode(info.getRoleOrgCode());
				data.setOrgName(info.getRoleOrgName());
				data.setParentOrgCode(info.getParentOrgCode());
				data.setParentOrgName(info.getParentOrgName());
				ArrayList<UserRoleInfo> userRoles = new ArrayList<UserRoleInfo>();
				for (int i = 0, len = roleUserInfoList.size(); i < len; i++) {
					OrgRoleInfo info1 = roleUserInfoList.get(i);
					UserRoleInfo userRoleInfo = new UserRoleInfo();
					userRoleInfo.setRoleCode(info1.getRoleCode());
					userRoleInfo.setRoleName(info1.getRoleName());
					userRoles.add(userRoleInfo);
				}
				data.setUserRoles(userRoles);
			}
		}
//		ArrayList<UserRoleInfo> userRoles = new ArrayList<UserRoleInfo>();
//		UserRoleInfo userRoleInfo = new UserRoleInfo();
//        userRoleInfo.setRoleCode("2");
//        userRoleInfo.setRoleName("22");
//        userRoles.add(userRoleInfo);
//        data.setUserRoles(userRoles);
		return data;
	}

	private SessionUserInfoDto getResponseData(String url) throws Exception {
		String data = HttpClientUtil.getBase64(url);
		LOG.info("response data: " + data);
		objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		SessionUserRespDto responseDto = objectMapper.readValue(data,
				SessionUserRespDto.class);
		if (responseDto != null && responseDto.isSuccess()) {
			return responseDto.getData();
		}
		return null;
	}
	
	@Override
	public Office getOrgInfoFromPAP(SessionUserInfoDto dto){
	    Office office = null;
        try {
            String json = objectMapper.writeValueAsString(dto);
            String data = HttpClientUtil
                    .postBase64(getOrgByUsernameUrl, json);
            LOG.info("response data: " + data);
            objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            UserOrgRespDto responseDto = objectMapper.readValue(data,
                    UserOrgRespDto.class);
            office = responseDto.getData();
        } catch (Exception e) {
            LOG.error("UserRestServiceImpl getOrgByUsernameUrl error", e);
        }
        return office;
	}
}
