/**  
 * Project Name:as-service  
 * File Name:ClickRateServiceImpl.java  
 * Package Name:com.bocom.service.impl  
 * Date:2017年4月25日下午3:06:20  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
 */

package com.bocom.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bocom.dao.ClickRateDao;
import com.bocom.domain.ClickRate;
import com.bocom.service.ClickRateService;
import com.bocom.util.CreateUUidUtil;

/**
 * ClassName:ClickRateServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月25日 下午3:06:20 <br/>
 * 
 * @author Mr-Wei
 * @version
 * @since JDK 1.7
 * @see
 */
@Service
public class ClickRateServiceImpl implements ClickRateService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClickRateDao clickRateDao;

	public int upClickRate(ClickRate clickRate) {

		// 首先查询点击量的表中是否已经存在该应用
		int i = clickRateDao.queryClickRateSize(clickRate);
		if (i > 0) {
			// 此时代表存在该应用，直接修改点击的量
			return clickRateDao.upClickRate(clickRate);
		} else {
			// 此时不存在该应用，先插入一条数据
			String id = CreateUUidUtil.createUuid();
			clickRate.setId(id);
			if (clickRateDao.insertSelective(clickRate) > 0) {
				return clickRateDao.upClickRate(clickRate);
			}

		}
		return 0;
	}

	@Override
	public List<ClickRate> queryByAppid(Map<String, Object> map) {
		return clickRateDao.queryByAppid(map);
	}

}
