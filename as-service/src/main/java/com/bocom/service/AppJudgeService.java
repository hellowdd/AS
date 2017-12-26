
 /**  
 * Project Name:as-service  
 * File Name:AppJudgeService.java  
 * Package Name:com.bocom.service  
 * Date:2017年5月4日上午10:37:32  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
*/  
  
package com.bocom.service;  

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bocom.dao.AppJudgeDao;
import com.bocom.domain.AppJudge;
import com.bocom.domain.ResponseVo;

/**  
 * ClassName:AppJudgeService <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年5月4日 上午10:37:32 <br/>  
 * @author   Mr-Wei  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public interface AppJudgeService {
	
	int insertAppJudge(AppJudge appJudge);
	
	int appendAppJudge(AppJudge appJudge);
	
	/*****
	 * 功能：获取评价信息列表
	 * 创建人：donghongguang
	 * 创建时间：2017年5月4日 下午2:29:45
	 * @param 
	 * @return 
	 * @version 1.0.0
	 */
	Map getAppJudgeInfoList(AppJudge appJudge);
	Map getAppJudgeInfoListMobile(AppJudge appJudge);
	
	/*****
	 * 功能：获取评价信息
	 * 创建人：donghongguang
	 * 创建时间：2017年5月4日 下午2:29:49
	 * @param 
	 * @return 
	 * @version 1.0.0
	 */
	public ResponseVo getMyAppJudge(AppJudge appJudge);
}
  
