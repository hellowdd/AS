package com.bocom.service.arcm;

import java.util.Map;

import com.bocom.domain.ResponseVo;
import com.bocom.dto.req.AppPageParamDto;
import com.bocom.dto.req.AppPureDetailParamDto;

public interface ArcmService {
	public ResponseVo queryAppPage(AppPageParamDto dto);

	public ResponseVo queryPureAppDetail(AppPureDetailParamDto dto);
	
	public ResponseVo queryCategory(Map map);

	ResponseVo queryMobileApp(AppPageParamDto dto);
}
