
 /**  
 * Project Name:wdd-service  
 * File Name:BaseService.java  
 * Package Name:com.bocom.wdd.service  
 * Date:2017年4月19日下午3:38:41  
 * Copyright (c) 2017, win@bocom.com All Rights Reserved.  
 *  
*/  
  
package com.bocom.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**  
 * ClassName:BaseService <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年4月19日 下午3:38:41 <br/>  
 * @author   win  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
@Service
public abstract class BaseService {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected Validator validator;
	@Autowired
    protected HttpServletRequest request;

}
  
