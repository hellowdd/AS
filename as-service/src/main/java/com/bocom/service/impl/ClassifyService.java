
 /**  
 * Project Name:wdd-service  
 * File Name:ClassifyService.java  
 * Package Name:com.bocom.wdd.service  
 * Date:2017年4月19日下午3:32:27  
 * Copyright (c) 2017, win@bocom.com All Rights Reserved.  
 *  
*/  
  
package com.bocom.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bocom.beanvalidator.BeanValidators;
import com.bocom.dao.ClassifyConfDao;
import com.bocom.domain.ClassifyConf;
import com.bocom.dto.req.ClassifyAddDto;

/**  
 * ClassName:ClassifyService <br/>  
 * Function: TODO 分类相关的业务处理. <br/>  
 * Date:     2017年4月19日 下午3:32:27 <br/>  
 * @author   win  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
@Service
public class ClassifyService extends BaseService{
	
	@Autowired
	private ClassifyConfDao classifyConfDao;
	
	public void addClassify(ClassifyAddDto dto){
		if(dto != null){
			BeanValidators.validateWithException(validator, dto);
			ClassifyConf classifyConf = new ClassifyConf();
			BeanUtils.copyProperties(dto, classifyConf);
			classifyConfDao.insertSelective(classifyConf);
		}
		
	}
	

}
  
