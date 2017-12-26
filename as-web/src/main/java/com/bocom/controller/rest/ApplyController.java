/**  
 * Project Name:as-web  
 * File Name:ApplyController.java  
 * Package Name:com.bocom.controller  
 * Date:2017年4月24日上午10:02:01  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
 */

package com.bocom.controller.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bocom.domain.ResponseVo;
import com.bocom.domain.Status;
import com.bocom.dto.StatusDto;
import com.bocom.service.ApplyService;
import com.bocom.util.CreateUUidUtil;
import com.bocom.util.PageUtil;
import com.bocom.util.ResponseVoUtil;
import com.github.pagehelper.PageInfo;

/**
 * ClassName:关于申请的controller <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月24日 上午10:02:01 <br/>
 * 
 * @author Mr-Wei
 * @version
 * @since JDK 1.7
 * @see
 */
@Controller
@RequestMapping("/manager/rest/apply/")
public class ApplyController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApplyService applyService;

	/**
	 * 
	 * applyList:根据userId查询申请的列表. <br/>
	 * 
	 * @author Mr-Wei
	 * @param userId
	 *            用户id
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping("/applyList")
	@ResponseBody
	public Map applyList(HttpServletRequest request) {
		PageInfo pageInfo = null;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String userId = request.getParameter("userId");
			if (userId != null) {
				paramMap.put("userId", userId);
			}
			String statusCode = request.getParameter("statusCode");
			if (statusCode != null) {
				paramMap.put("statusCode", statusCode);
			}
			PageUtil.setParams(request, paramMap);
			List<StatusDto> list = applyService.queryApplyList(paramMap);
			//将roleIds中的数字去除
			for(StatusDto statusDto:list){
				String roleIds=statusDto.getRoleIds();
				roleIds=roleIds.replaceAll("\\d{1,}:", "");
				statusDto.setRoleIds(roleIds);
			}
			pageInfo = new PageInfo(list);
		} catch (Exception e) {
			logger.info("queryApplyList==============> 出错" + e.getMessage());
		}
		return PageUtil.covertMap(new Object[] { "page" },
				new Object[] { pageInfo });

	}

	@RequestMapping("/delApply")
	@ResponseBody
	public ResponseVo delApply(@RequestParam(required = true) String id) {
		try {
			applyService.delApply(id);
			return ResponseVoUtil.success("","");
		} catch (Exception e) {
			logger.error("delApply============> 错误" + e.getMessage());
			return ResponseVoUtil.fail(e.getMessage(),e.getMessage());
		}

	}

	@RequestMapping("/insertApply")
	@ResponseBody
	public ResponseVo insertApply(@RequestBody Status status) {
		try {
			String id = CreateUUidUtil.createUuid();
			status.setId(id);
			int i=applyService.insertApply(status);
			if(i==-1){
				return ResponseVoUtil.fail("已经在审批中","已经在审批中");
			}else if (i >= 1) {
				return ResponseVoUtil.success("", "");
			} else {
				return ResponseVoUtil.fail("加入我的申请失败", "加入我的申请失败");
			}
		} catch (Exception e) {
			logger.error("delApply============> 错误" + e.getMessage());
			return ResponseVoUtil.fail(e.getMessage(),e.getMessage());
		}

	}

}
