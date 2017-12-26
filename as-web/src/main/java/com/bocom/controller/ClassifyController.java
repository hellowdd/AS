
 /**  
 * Project Name:wdd-web  
 * File Name:ClassifyController.java  
 * Package Name:com.bocom.km.controller  
 * Date:2017年4月19日下午2:26:20  
 * Copyright (c) 2017, win@bocom.com All Rights Reserved.  
 *  
*/  
  
package com.bocom.controller;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bocom.beanvalidator.BeanValidators;
import com.bocom.domain.ResponseVo;
import com.bocom.dto.req.ClassifyAddDto;
import com.bocom.service.impl.ClassifyService;
import com.bocom.util.ResponseUtil;
import com.bocom.util.ResponseVoUtil;

/**  
 * ClassName:ClassifyController <br/>  
 * Reason:   TODO 分类配置 <br/>  
 * Date:     2017年4月19日 下午2:26:20 <br/>  
 * @author   win  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
@Controller
@RequestMapping(value = "/as")
public class ClassifyController {
	
	@Autowired
	private ClassifyService classifyService;
	
	/**
	 * 
	 * addClassify:(添加分类信息). <br/>  
	 *  
	 * @author win  
	 * @return  
	 * @since JDK 1.8
	 */
	@RequestMapping("/addClassify")
	@ResponseBody
	public ResponseVo addClassify(@RequestBody(required = false) ClassifyAddDto dto){
		try {
			
			classifyService.addClassify(dto);
			return ResponseVoUtil.success("添加成功","添加成功");
		}catch(ConstraintViolationException e){
			return ResponseVoUtil.fail(e.getMessage(),e.getMessage());
		} catch (Exception e) {
			  
			e.printStackTrace();  
			return ResponseVoUtil.fail("添加失败","添加失败");
			
		}
	}

}
  
