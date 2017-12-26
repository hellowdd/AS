
/**  
* Project Name:wdd-util  
* File Name:DelFlagEnum.java  
* Package Name:com.bocom.wdd  
* Date:2017年4月19日下午1:50:14  
* Copyright (c) 2017, win@bocom.com All Rights Reserved.  
*  
*/

package com.bocom.enums;

/**
 * ClassName:DelFlagEnum <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO 删除枚举值 <br/>
 * Date: 2017年4月19日 下午1:50:14 <br/>
 * 
 * @author win
 * @version
 * @since JDK 1.8
 * @see
 */
public enum DelFlagEnum {

	DELETE_FLAG("1", "删除"), NORMAL_FLAG("0", "正常");

	private String keyCode;

	private String value;

	private DelFlagEnum(String keyCode, String value) {
		this.keyCode = keyCode;
		this.value = value;
	}

	public String getKeyCode() {

		return keyCode;
	}

	public void setKeyCode(String keyCode) {

		this.keyCode = keyCode;
	}

	public String getValue() {

		return value;
	}

	public void setValue(String value) {

		this.value = value;
	}

}
