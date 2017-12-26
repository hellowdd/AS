
/**  
* Project Name:wdd-util  
* File Name:ClassifyAddDto.java  
* Package Name:com.bocom.wdd.dto.req  
* Date:2017年4月19日下午3:19:54  
* Copyright (c) 2017, win@bocom.com All Rights Reserved.  
*  
*/

package com.bocom.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * ClassName:ClassifyAddDto <br/>
 * Function: TODO 添加分类dto. <br/>
 * Date: 2017年4月19日 下午3:19:54 <br/>
 * 
 * @author win
 * @version
 * @since JDK 1.8
 * @see
 */
public class ClassifyAddDto {

	@NotBlank(message = "分类编号不能为空")
	private String classifyCode;// 编号

	@NotBlank(message = "分类名称不能为空")
	private String classifyName;// 名称

	public String getClassifyCode() {

		return classifyCode;
	}

	public void setClassifyCode(String classifyCode) {

		this.classifyCode = classifyCode;
	}

	public String getClassifyName() {

		return classifyName;
	}

	public void setClassifyName(String classifyName) {

		this.classifyName = classifyName;
	}

}
