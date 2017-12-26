/**  
 * Project Name:as-web  
 * File Name:FavoriteController.java  
 * Package Name:com.bocom.controller.rest  
 * Date:2017年4月24日下午4:29:03  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
 */

package com.bocom.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bocom.domain.Favorite;
import com.bocom.domain.ResponseVo;
import com.bocom.dto.FavoriteDto;
import com.bocom.service.FavoriteService;
import com.bocom.util.PageUtil;
import com.bocom.util.ResponseUtil;
import com.bocom.util.ResponseVoUtil;
import com.github.pagehelper.PageInfo;

/**
 * ClassName:FavoriteController <br/>
 * Function: 收藏夹 <br/>
 * Date: 2017年4月24日 下午4:29:03 <br/>
 * 
 * @author Mr-Wei
 * @version
 * @since JDK 1.7
 * @see
 */
@Controller
@RequestMapping("/manager/rest/favorite/")
public class FavoriteController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FavoriteService favoriteService;

	/**
	 * 
	 * queryFavoriteList:查询收藏夹列表. <br/>
	 * 
	 * @author Mr-Wei
	 * @param request
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping("/queryFavoriteList")
	@ResponseBody
	public Map queryFavoriteList(HttpServletRequest request) {
		PageInfo pageInfo = null;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String userId = request.getParameter("userId");
			if (userId != null) {
				paramMap.put("userId", userId);
			}
			paramMap.put("updateBy", "0");
			PageUtil.setParams(request, paramMap);
			List<FavoriteDto> list = favoriteService
					.queryFavoriteList(paramMap);
			pageInfo = new PageInfo(list);
		} catch (Exception e) {
			logger.info("queryFavoriteList==============> 出错" + e.getMessage());
		}
		return PageUtil.covertMap(new Object[] { "page" },
				new Object[] { pageInfo });

	}

	/**
	 * 
	 * delFavorite:逻辑删除收藏. <br/>
	 * 
	 * @author Mr-Wei
	 * @param id
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping("/delFavorite")
	@ResponseBody
	public ResponseVo delFavorite(@RequestParam(required = true) String id) {
		try {
			favoriteService.delFavorite(id);
			return ResponseVoUtil.success("", "");
		} catch (Exception e) {
			logger.info("delFavorite==============> 出错" + e.getMessage());
			return ResponseVoUtil.fail(e.getMessage(), e.getMessage());
		}

	}

	/**
	 * 
	 * insertFavorite:加入我的收藏. <br/>
	 * 
	 * @author Mr-Wei
	 * @param favorite
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping("/insertFavorite")
	@ResponseBody
	public ResponseVo insertFavorite(@RequestBody Favorite favorite) {
		try {
		    favorite.setUpdateBy("0");
			int i = favoriteService.insertFavorite(favorite);
			if (i == -1) {
				return ResponseVoUtil.fail("收藏夹中已存在该应用", "收藏夹中已存在该应用");
			} else if (i >= 1) {
				return ResponseVoUtil.success("", "");
			} else {
				return ResponseVoUtil.fail("加入收藏夹失败", "加入收藏夹失败");
			}

		} catch (Exception e) {
			logger.info("delFavorite==============> 出错" + e.getMessage());
			return ResponseVoUtil.fail(e.getMessage(), e.getMessage());
		}

	}

}
