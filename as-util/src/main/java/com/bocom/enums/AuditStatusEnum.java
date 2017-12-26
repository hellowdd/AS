
/**  
* Project Name:wdd-util  
* File Name:AuditStatusEnum.java  
* Package Name:com.bocom.wdd.enums  
* Date:2017年4月19日下午2:11:43  
* Copyright (c) 2017, win@bocom.com All Rights Reserved.  
*  
*/

package com.bocom.enums;

/**
 * ClassName:AuditStatusEnum <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO 审核状态 <br/>
 * Date: 2017年4月19日 下午2:11:43 <br/>
 * 
 * @author win
 * @version
 * @since JDK 1.8
 * @see
 */
public enum AuditStatusEnum {

	WAIT_AUDIT("0", "申请中"),
	PASS_AUDIT("1", "申请通过"),
	REFUSE_AUDIT("2", "申请驳回");

	private String keyCode;

	private String value;

	private AuditStatusEnum(String keyCode, String value) {
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
