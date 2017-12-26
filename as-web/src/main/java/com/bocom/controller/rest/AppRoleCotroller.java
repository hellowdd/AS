package com.bocom.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bocom.domain.ResponseVo;
import com.bocom.service.AppRoleService;
import com.bocom.util.ResponseVoUtil;

/*****
 * 类名称：AppRoleCotroller 类描述：应用及角色 创建人：donghongguang 创建时间：2017年4月25日 上午11:25:46
 * 修改人： 修改时间：
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping("/manager/rest/myApp/")
public class AppRoleCotroller {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AppRoleService appRoleService;

	/*****
	 * 功能：获取我的应用 创建人：donghongguang 创建时间：2017年4月25日 上午11:43:42
	 * 
	 * @param
	 * @return
	 * @version 1.0.0
	 */
	@RequestMapping("/getMyApp")
	@ResponseBody
	public ResponseVo getMyApp() {
		try {
			ResponseVo responseVo=appRoleService.getMyApp();
			return responseVo;
		} catch (Exception e) {
			logger.error("获取应用失败============》"+e.getMessage());
			return ResponseVoUtil.fail(e.getMessage(),e.getMessage());
		}
	}

	/*****
	 * 功能：获取应用所有的角色 创建人：donghongguang 创建时间：2017年4月25日 上午11:43:45
	 * 
	 * @param
	 * @return
	 * @version 1.0.0
	 */
	@RequestMapping("/getAppAllRole")
	@ResponseBody
	public ResponseVo getAppAllRole(
			@RequestParam(required = true) String appId,
			@RequestParam(required = true) String version) {
		try {
			ResponseVo responseVo=appRoleService.getAppAllRole(appId, version);
			return responseVo;
		} catch (Exception e) {
			logger.error("获取应用角色失败============》"+e.getMessage());
			return ResponseVoUtil.fail(e.getMessage(),e.getMessage());
		}
	}
}
