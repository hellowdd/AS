
 /**  
 * Project Name:as-service  
 * File Name:ApplyService.java  
 * Package Name:com.bocom.service  
 * Date:2017年4月24日上午10:09:10  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
*/  
  
package com.bocom.service;  

import java.util.List;
import java.util.Map;

import com.bocom.domain.ResponseVo;
import com.bocom.domain.Status;
import com.bocom.dto.StatusDto;

/**  
 * ClassName:ApplyService <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年4月24日 上午10:09:10 <br/>  
 * @author   Mr-Wei  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public interface ApplyService {
	
	List<StatusDto> queryApplyList(Map map);
	
	int delApply(String id);
	
	int insertApply(Status status);
	
	int auditApply(Map map);

}
  
