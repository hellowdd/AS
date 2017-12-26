package com.bocom.service.arcm.Impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bocom.domain.ResponseVo;
import com.bocom.dto.req.AppPageParamDto;
import com.bocom.dto.req.AppPureDetailParamDto;
import com.bocom.service.arcm.ArcmService;
import com.bocom.util.HttpClientUtil;
import com.bocom.util.JsonUtil;

@Service
public class ArcmServiceImpl implements ArcmService {
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static Logger LOG = LoggerFactory.getLogger(ArcmServiceImpl.class);

	@Value("${rest.arcm.queryAppPage.url}")
	private String queryAppPageUrl;
	@Value("${rest.arcm.queryPureAppDetail.url}")
	private String queryPureAppDetailUrl;
	@Value("${config.mobileCategory}")
	private String mobileCategory;
	@Value("${config.appCategory}")
	private String appCategory;
	@Value("${config.bizCategory}")
	private String bizCategory;
	@Value("${rest.arcm.queryCategory.url}")
	private String queryCategory;
	@Value("${config.category}")
	private String category;

	@Override
	public ResponseVo queryAppPage(AppPageParamDto dto) {
		try {
			if (StringUtils.isNotEmpty(appCategory)) {
				dto.setAppCategory(appCategory);
			}
			if (StringUtils.isNotEmpty(bizCategory)) {
				dto.setBizCategory(bizCategory);
			}
			dto.setAppIdGroup("AppIdGroup");
			String json = objectMapper.writeValueAsString(dto);
			String data = HttpClientUtil.postBase64(queryAppPageUrl
					+ "?pageSize=" + Integer.MAX_VALUE, json);
			objectMapper
					.configure(
							org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
							false);
			return JsonUtil.readValue(data, ResponseVo.class);

			// LOG.info("response data: " + data);
			// ResponseVo responseVo = objectMapper.readValue(data,
			// ResponseVo.class);
			// if (null != responseVo && responseVo.isSuccess()) {
			// return (Object) responseVo.getData();
			// }
		} catch (Exception e) {
			LOG.error("ArcmServiceImpl queryAppPage error", e);
		}
		return null;
	}

	@Override
	public ResponseVo queryMobileApp(AppPageParamDto dto) {
		try {
			if (StringUtils.isNotEmpty(mobileCategory)) {
				dto.setAppCategory(mobileCategory);
			}
			if (StringUtils.isNotEmpty(bizCategory)) {
				dto.setBizCategory(bizCategory);
			}
			dto.setAppIdGroup("AppIdGroup");
			String json = objectMapper.writeValueAsString(dto);
			String data = HttpClientUtil.postBase64(queryAppPageUrl
					+ "?pageSize=" + Integer.MAX_VALUE, json);
			objectMapper
					.configure(
							org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
							false);
			return JsonUtil.readValue(data, ResponseVo.class);

			// LOG.info("response data: " + data);
			// ResponseVo responseVo = objectMapper.readValue(data,
			// ResponseVo.class);
			// if (null != responseVo && responseVo.isSuccess()) {
			// return (Object) responseVo.getData();
			// }
		} catch (Exception e) {
			LOG.error("ArcmServiceImpl queryAppPage error", e);
		}
		return null;
	}

	@Override
	public ResponseVo queryPureAppDetail(AppPureDetailParamDto dto) {
		try {
			String json = objectMapper.writeValueAsString(dto);
			String data = HttpClientUtil
					.postBase64(queryPureAppDetailUrl, json);
			LOG.info("response data: " + data);
			objectMapper
					.configure(
							org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
							false);
			return JsonUtil.readValue(data, ResponseVo.class);
			/*
			 * ResponseVo responseVo = objectMapper.readValue(data,
			 * ResponseVo.class); if (null != responseVo &&
			 * responseVo.isSuccess()) { return (Object) responseVo.getData(); }
			 */
		} catch (Exception e) {
			LOG.error("ArcmServiceImpl queryPureAppDetail error", e);
		}
		return null;
	}

	/****
	 * 描述： 查询业务类别 创建人：donghongguang 创建时间：2017年7月6日 下午4:21:31
	 * 
	 * @version 1.0.0
	 */
	@Override
	public ResponseVo queryCategory(Map map) {
		try {
			map = new HashMap();
			map.put("category", category);
			String json = objectMapper.writeValueAsString(map);
			String data = HttpClientUtil.postBase64(queryCategory, json);
			LOG.info("response data: " + data);
			objectMapper
					.configure(
							org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
							false);
			return JsonUtil.readValue(data, ResponseVo.class);
		} catch (Exception e) {
			LOG.error("ArcmServiceImpl queryCategory error", e);
		}
		return null;
	}
}
