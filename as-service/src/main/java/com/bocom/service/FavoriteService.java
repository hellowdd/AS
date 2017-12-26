/**  
 * Project Name:as-service  
 * File Name:FavoriteService.java  
 * Package Name:com.bocom.service  
 * Date:2017年4月24日下午4:46:59  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
 */

package com.bocom.service;

import java.util.List;
import java.util.Map;

import com.bocom.domain.Favorite;
import com.bocom.dto.FavoriteDto;

/**
 * ClassName:FavoriteService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月24日 下午4:46:59 <br/>
 * 
 * @author Mr-Wei
 * @version
 * @since JDK 1.7
 * @see
 */
public interface FavoriteService {
	List<FavoriteDto> queryFavoriteList(Map map);
	
	int delFavorite(String str);
	
	int insertFavorite(Favorite favorite);
	

}
