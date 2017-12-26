/**  
 * Project Name:as-service  
 * File Name:ApplyServiceImpl.java  
 * Package Name:com.bocom.service.impl  
 * Date:2017年4月24日上午10:10:18  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
 */

package com.bocom.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bocom.dao.StatusDao;
import com.bocom.domain.Status;
import com.bocom.dto.Office;
import com.bocom.dto.ReplaceAppRoleDto;
import com.bocom.dto.ResponsePAPVo;
import com.bocom.dto.SessionUserInfo;
import com.bocom.dto.SessionUserInfoDto;
import com.bocom.dto.StatusDto;
import com.bocom.enums.AuditStatusEnum;
import com.bocom.service.ApplyService;
import com.bocom.service.user.UserRestService;
import com.bocom.util.HttpClientUtil;
import com.bocom.util.JsonUtil;
import com.bocom.util.PageUtil;

/**
 * ClassName:ApplyServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月24日 上午10:10:18 <br/>
 * 
 * @author Mr-Wei
 * @version
 * @since JDK 1.7
 * @see
 */
@Service
public class ApplyServiceImpl extends BaseService implements ApplyService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StatusDao statusDao;
	/**
     * 登陆业务
     */
    @Autowired
    private UserRestService userRestService;

	// 应用角色组织人员关系新增或更新接口地址
	@Value("${rest.pap.replaceAppRole.url}")
	private String replaceAppRole;

	@Override
	public List<StatusDto> queryApplyList(Map map) {
		PageUtil.dealPage(map);
		return statusDao.queryApplyList(map);
	}

	@Override
	public int delApply(String id) {
		return statusDao.delApply(id);
	}

	@Override
	public int insertApply(Status status) {
		//如果大于0代表已经存在该申请
		if(statusDao.queryMyApplyCount(status)>0){
			return -1;
		}
		// 在session中
//        HttpSession session = request.getSession();
//        SessionUserInfo userInfo = (SessionUserInfo) session
//                .getAttribute("sessionUserInfo");
//        String s =  userInfo.getOrgCode();
		return statusDao.insertApply(status);
	}

	@Override
	public int auditApply(Map map) {
		// 此时代表同意申请，调用pap接口进行系统角色的权限赋值
		
		if (map.get("statusId").equals(AuditStatusEnum.PASS_AUDIT.getKeyCode())) {
			// 根据id查询具体的应用信息
			String id = (String) map.get("id");
			Status status = statusDao.selectByPrimaryKey(id);
			String appVersion = status.getAppVersion();
			String appId = status.getAppId();
			String[] roleIds = status.getRoleIds().split(",");
	        HttpSession session = request.getSession();
//	        SessionUserInfo userInfo = (SessionUserInfo) session
//	                .getAttribute("sessionUserInfo");
//	        String orgCode =  userInfo.getOrgCode();
//	        String orgCode =  "12350";//随意赋值，是个必传参数，但是没有作用
	        SessionUserInfoDto req = new SessionUserInfoDto();
//	        req.setUserName(userInfo.getUserName());
	        req.setUserId(Integer.valueOf(status.getCreateBy()));
	        
	        Office office = userRestService.getOrgInfoFromPAP(req);
	        String orgCode =  office.getId();
			for (String roleId : roleIds) {
				// 调用pap接口给用户赋权限,将roleId通过：截取，获取真正的roleId
				roleId=roleId.split(":")[0];
				ReplaceAppRoleDto appRoleDto = new ReplaceAppRoleDto(appId,
						appVersion, roleId, orgCode,
						status.getCreateBy());
//						(String) map.get("userId"));
				logger.info("调用pap接口来给用户赋予角色=========》"
						+ JsonUtil.toJSon(appRoleDto));
				String data = HttpClientUtil.post(replaceAppRole,
						JsonUtil.toJSon(appRoleDto));
				logger.info("调用pap接口来给用户赋予角色返回的数据: " + data);
				ResponsePAPVo responsePap = JsonUtil.readBase64Value(data, ResponsePAPVo.class);
//				ResponsePAPVo responsePap = JsonUtil.readValue(data, ResponsePAPVo.class);
				if (!responsePap.isSuccess()) {
					return 0;
				}
			}

		}
		//获取收藏信息，如有，则修改为已申请通过，

		return statusDao.auditApply(map);
	}
}
