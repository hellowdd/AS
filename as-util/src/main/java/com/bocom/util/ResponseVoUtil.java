
 /**  
 * Project Name:as-util  
 * File Name:ResponseVoUtil.java  
 * Package Name:com.bocom.util  
 * Date:2017年4月25日下午6:02:58  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
*/  
  
package com.bocom.util;  

import com.bocom.domain.ResponseVo;

/**  
 * ClassName:ResponseVoUtil <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年4月25日 下午6:02:58 <br/>  
 * @author   Mr-Wei  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
public class ResponseVoUtil {
	  public static ResponseVo success(Object object,String message){
	        ResponseVo responseVo = new ResponseVo();
	        responseVo.setSuccess(true);
	        responseVo.setData(object);
	        responseVo.setMessage(message);
	        return responseVo;
	    }

	    public static ResponseVo fail(String msg, Object object){
	        ResponseVo responseVo = new ResponseVo();
	        responseVo.setSuccess(false);
	        responseVo.setData(object);
	        responseVo.setMessage(msg);
	        return responseVo;
	    }
}

