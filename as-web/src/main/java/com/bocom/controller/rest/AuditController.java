/**  
 * Project Name:as-web  
 * File Name:AuditController.java  
 * Package Name:com.bocom.controller.rest  
 * Date:2017年4月24日下午3:38:20  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
 */

package com.bocom.controller.rest;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bocom.domain.ResponseVo;
import com.bocom.service.ApplyService;
import com.bocom.util.ResponseVoUtil;

/**
 * ClassName:AuditController <br/>
 * Function: 审核controller <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月24日 下午3:38:20 <br/>
 * 
 * @author Mr-Wei
 * @version
 * @since JDK 1.7
 * @see
 */
@Controller
@RequestMapping("/manager/rest/audit/")
public class AuditController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApplyService applyService;

	/**
	 * 
	 * auditApply:审批是否同意接口. <br/>
	 * 
	 * @author Mr-Wei
	 * @param isAgree
	 *            是否同意 1申请通过，2申请驳回
	 * @param id
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping("/auditApply")
	@ResponseBody
	public ResponseVo auditApply(@RequestParam(required = true) String isAgree,
			@RequestParam(required = true) String id,
			@RequestParam(required = true) String userId,
			@RequestParam String orgCode,@RequestParam String statusRemark) {
		// 同意该申请
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("statusId", isAgree);
			map.put("id", id);
			map.put("userId", userId);
			map.put("orgCode", orgCode);
			map.put("statusRemark", URLDecoder.decode(statusRemark.replaceAll("%", "%25"),"utf-8"));
			if (applyService.auditApply(map) > 0) {
				return ResponseVoUtil.success("","");
			} else {
				return ResponseVoUtil.fail("申请失败","申请失败");
			}

		} catch (Exception e) {
			logger.error("auditApply===================>" + e.getMessage());
			return ResponseVoUtil.fail(e.getMessage(),e.getMessage());
		}

	}

}
