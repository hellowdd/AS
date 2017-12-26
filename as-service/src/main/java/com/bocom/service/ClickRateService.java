
 /**  
 * Project Name:as-service  
 * File Name:ClickRateService.java  
 * Package Name:com.bocom.service  
 * Date:2017年4月25日下午3:05:53  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
*/  
  
package com.bocom.service;  

import java.util.List;
import java.util.Map;

import com.bocom.domain.ClickRate;

/**  
 * ClassName:ClickRateService <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年4月25日 下午3:05:53 <br/>  
 * @author   Mr-Wei  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public interface ClickRateService {
	
	int upClickRate(ClickRate clickRate);
	public List<ClickRate> queryByAppid(Map<String,Object> map);
}
  
