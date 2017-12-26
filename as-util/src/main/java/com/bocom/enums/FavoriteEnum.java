
 /**  
 * Project Name:as-util  
 * File Name:FavoriteEnum.java  
 * Package Name:com.bocom.enums  
 * Date:2017年4月24日下午4:36:30  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
*/  
  
package com.bocom.enums;  
/**  
 * ClassName:FavoriteEnum <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年4月24日 下午4:36:30 <br/>  
 * @author   Mr-Wei  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public enum FavoriteEnum {
	
	LEFT("1", "左边专用"), FAVORITE("2", "收藏栏专用");
	
	private String keyCode;

	private String value;
	
	private FavoriteEnum(String keyCode, String value) {
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
  
