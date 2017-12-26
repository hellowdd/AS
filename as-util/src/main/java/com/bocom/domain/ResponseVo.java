
/**  
* Project Name:wdd-util  
* File Name:ResponseVo.java  
* Package Name:com.bocom.wdd.domain  
* Date:2017年4月19日下午3:15:23  
* Copyright (c) 2017, win@bocom.com All Rights Reserved.  
*  
*/

package com.bocom.domain;

/**
 * ClassName:ResponseVo <br/>
 * Function: TODO 响应的实体 <br/>
 * Date: 2017年4月19日 下午3:15:23 <br/>
 * 
 * @author win
 * @version
 * @since JDK 1.8
 * @see
 */
public class ResponseVo {
	
	/**返回的数据对象*/
	private Object data;
	
	/**是否成功*/
	private Boolean success;
	
	/**提示信息*/
	private String message;

	public ResponseVo(Object obj) {
		if(obj instanceof Exception){
			this.data = obj;
			this.success = false;
			this.message = "error";
		}else{
			this.data = obj;
			this.success = true;
			this.message = "sucess";
		}
	}

	public ResponseVo() {
		this.data = null;
		this.success = true;
		this.message = "sucess";
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
