
 /**  
 * Project Name:wdd-util  
 * File Name:ResponseUtil.java  
 * Package Name:com.bocom.wdd.util  
 * Date:2017年4月19日下午3:14:58  
 * Copyright (c) 2017, win@bocom.com All Rights Reserved.  
 *  
*/  
  
package com.bocom.util;

import com.bocom.domain.ResponseVo;


/**  
 * ClassName:ResponseUtil <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年4月19日 下午3:14:58 <br/>  
 * @author   win  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
import java.io.UnsupportedEncodingException;

public class ResponseUtil {
	private static String charset = "UTF-8";

	public static String success() {
		return success(null);
	}

	public static String success(Object object) {
		ResponseVo responseVo = new ResponseVo();
		responseVo.setSuccess(true);
		responseVo.setData(object);
		return getBASE64(JsonUtil.toJSon(responseVo));
	}

	public static String fail(String msg) {
		return fail(msg, null);
	}

	public static String fail(String msg, Object object) {
		ResponseVo responseVo = new ResponseVo();
		responseVo.setSuccess(false);
		responseVo.setData(object);
		responseVo.setMessage(msg);
		return getBASE64(JsonUtil.toJSon(responseVo));
	}

	public static String getBASE64(String s) {
		if (s == null)
			return null;
		try {
			return (new sun.misc.BASE64Encoder()).encode(s.getBytes(charset));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}

