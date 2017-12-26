/**  
 * Project Name:as-web  
 * File Name:AppJudgeController.java  
 * Package Name:com.bocom.controller.rest  
 * Date:2017年5月4日上午10:38:48  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
 */

package com.bocom.controller.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bocom.domain.AppJudge;
import com.bocom.domain.ResponseVo;
import com.bocom.dto.SessionUserInfo;
import com.bocom.service.AppJudgeService;
import com.bocom.util.ResponseVoUtil;

/**
 * ClassName:AppJudgeController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年5月4日 上午10:38:48 <br/>
 * 
 * @author Mr-Wei
 * @version
 * @since JDK 1.7
 * @see
 */
@Controller
@RequestMapping("/manager/rest/appJudge/")
public class AppJudgeController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AppJudgeService appJudgeService;
	@Autowired
    protected HttpServletRequest request;

	/**
	 * 
	 * insertAppJudge:插入应用评价. <br/> 
	 * @author Mr-Wei  
	 * @param appJudge
	 * @return  
	 * @since JDK 1.7
	 */
	@RequestMapping("/insertAppJudge")
	@ResponseBody
	public ResponseVo insertAppJudge(@RequestBody AppJudge appJudge) {

		try {
			appJudgeService.insertAppJudge(appJudge);
			return ResponseVoUtil.success("", "插入成功");
		} catch (Exception e) {
			logger.error("插入应用评价失败==============》" + e.getMessage());
			return ResponseVoUtil.fail("插入应用评价失败", "插入应用评价失败");

		}
	}

	/**
	 * 
	 * appendAppJudge:追加应用评价. <br/> 
	 * @author Mr-Wei  
	 * @param appJudge
	 * @return  
	 * @since JDK 1.7
	 */
	@RequestMapping("/appendAppJudge")
	@ResponseBody
	public ResponseVo appendAppJudge(@RequestBody AppJudge appJudge) {

		try {
			if (appJudgeService.appendAppJudge(appJudge) == 0) {
//			    return ResponseVoUtil.fail("您已追加该应用评论", "您已追加该应用评论");
				return ResponseVoUtil.fail("追加该应用评论失败", "追加该应用评论失败");
			}
			return ResponseVoUtil.success("", "评论成功");
		} catch (Exception e) {
			logger.error("插入应用评价失败==============》" + e.getMessage());
			return ResponseVoUtil.fail("追加应用评价失败", "追加应用评价失败");

		}
	}
	
	/*****
	 * 功能：获取个人评价信息
	 * 创建人：donghongguang
	 * 创建时间：2017年5月4日 下午1:48:13
	 * @param 
	 * @return 
	 * @version 1.0.0
	 */
	@RequestMapping("/getAppJudgeInfo")
    @ResponseBody
    public ResponseVo getAppJudgeInfo(@RequestBody AppJudge appJudge) {

	    try {
            ResponseVo responseVo=appJudgeService.getMyAppJudge(appJudge);
            return responseVo;
        } catch (Exception e) {
            logger.error("获取评论失败============》"+e.getMessage());
            return ResponseVoUtil.fail(e.getMessage(),e.getMessage());
        }
    }
	
	/*****
     * 功能：获取单个应用的所有评价信息 支持分页
     * 创建人：donghongguang
     * 创建时间：2017年5月4日 下午1:48:13
     * @param 
     * @return 
     * @version 1.0.0
     */
    @RequestMapping("/getAppJudgeInfoList")
    @ResponseBody
    public Map getAppJudgeInfoList(@RequestBody AppJudge appJudge) {
        appJudge.setIsPublic("1"); //1代表公司
        return appJudgeService.getAppJudgeInfoList(appJudge);
    }
    
    /*****
     * 功能：获取警员下的所有评价信息 支持分页
     * 创建人：donghongguang
     * 创建时间：2017年5月4日 下午1:48:13
     * @param 
     * @return 
     * @version 1.0.0
     */
    @RequestMapping("/getMyAppJudgeInfoList")
    @ResponseBody
    public Map getMyAppJudgeInfoList(@RequestBody AppJudge appJudge) {
        HttpSession session = request.getSession();
        SessionUserInfo userInfo = (SessionUserInfo) session.getAttribute("sessionUserInfo");
        appJudge.setCreateBy(String.valueOf(userInfo.getUserId()));
        return appJudgeService.getAppJudgeInfoList(appJudge);
    }
}
