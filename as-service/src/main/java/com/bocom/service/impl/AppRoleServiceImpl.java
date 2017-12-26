package com.bocom.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bocom.dao.AppJudgeDao;
import com.bocom.dao.ClickRateDao;
import com.bocom.domain.AppJudge;
import com.bocom.domain.ClickRate;
import com.bocom.domain.ResponseVo;
import com.bocom.dto.AppInfoBase;
import com.bocom.dto.SessionUserInfo;
import com.bocom.dto.req.AppStoreGetAppReqDto;
import com.bocom.dto.resp.AppStoreGetAppRespDto;
import com.bocom.dto.resp.AppStoreResponse;
import com.bocom.dto.resp.RoleAndAppInfoDto;
import com.bocom.service.AppRoleService;
import com.bocom.service.user.impl.UserRestServiceImpl;
import com.bocom.util.AppInfoClickSort;
import com.bocom.util.HttpClientUtil;
import com.bocom.util.JsonUtil;
import com.bocom.util.ResponseUtil;
import com.bocom.util.ResponseVoUtil;
import com.github.pagehelper.PageInfo;

/*****
 * 类名称：AppRoleServiceImpl 类描述：我的应用及角色服务类 创建人：donghongguang 创建时间：2017年4月25日
 * 下午1:19:11 修改人： 修改时间：
 * 
 * @version 1.0.0
 */
@Service
public class AppRoleServiceImpl extends BaseService implements AppRoleService {
	private static ObjectMapper objectMapper = new ObjectMapper();

	private static Logger LOG = LoggerFactory
			.getLogger(UserRestServiceImpl.class);

	@Value("${rest.pap.queryAppRoles.url}")
	private String queryAppRoles;

	@Value("${rest.pap.queryUserApp.url}")
	private String queryUserApp;
	@Autowired
	private ClickRateDao clickRateDao;
	@Autowired
    private AppJudgeDao appJudgeDao;
	@Value("${fastDFS.http.url}")
    private String fastDfsUrl;

	/*****
	 * 功能：获取所有我的应用 创建人：donghongguang 创建时间：2017年4月25日 下午1:18:01
	 * 
	 * @param
	 * @return
	 * @version 1.0.0
	 */
	@Override
	public ResponseVo getMyApp() {
		/**
		 * 1.获取当前用户 2.从pap获取所有应用 3.结合点击量进行排序 4.返回给上层
		 */
		try {
			// 在session中
			HttpSession session = request.getSession();
			SessionUserInfo userInfo = (SessionUserInfo) session
					.getAttribute("sessionUserInfo");
			int userId = userInfo.getUserId();//121104;

			AppStoreGetAppReqDto dtoReq = new AppStoreGetAppReqDto();
			dtoReq.setUserId(new Long(userId));
			String json = objectMapper.writeValueAsString(dtoReq);
			String data = HttpClientUtil.post(queryUserApp, json);
			LOG.info("response data: " + data);
			objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			AppStoreResponse responseDto = objectMapper.readValue(data,
					AppStoreResponse.class);
			if(responseDto.isSuccess()){
			    AppStoreGetAppRespDto appStoreGetAppRespDto = responseDto.getData();
			    List<AppInfoBase> appInfoList = new ArrayList<>();
			    if(appStoreGetAppRespDto != null){
			        List<RoleAndAppInfoDto> roleAndAppInfoDtoList = appStoreGetAppRespDto
	                        .getData();
			        if(null != roleAndAppInfoDtoList && roleAndAppInfoDtoList.size()>0){
			         // 获取点击量
	                    Map<String, Object> map = new HashMap<String, Object>();
	                    List appids = new ArrayList<>();
	                    List<ClickRate> clickRateList = new ArrayList<ClickRate>();
	                    for (RoleAndAppInfoDto dto : roleAndAppInfoDtoList) {
	                        if(null != dto.getLogoApp() && !dto.getLogoApp().equals("")){
                                if(dto.getLogoApp().startsWith("http://")){
                                    
                                }else{
                                    dto.setLogoApp(fastDfsUrl + dto.getLogoApp());
                                }
                            }
	                        appids.add(dto.getAppId());
	                        Map<String ,Object> paramMap = new HashMap<String ,Object>();
	                        paramMap.put("appId",dto.getAppId());
	                        paramMap.put("appVersion",dto.getAppVersion());
	                        paramMap.put("createBy",userId);
	                        //获取是否评价和追评
	                        List<AppJudge> list = appJudgeDao.selectJudgeInfo(paramMap);
	                        if(null != list && list.size()>0){
	                            dto.setAppScore(String.valueOf(list.get(0).getAppScore()));
	                            dto.setAppScoreAdd(String.valueOf(list.get(0).getAppScoreAdd()));
	                        }
	                        
	                    }
	                    
	                    if(null != appids && appids.size()>0){
	                        map.put("appids", appids);
	                        clickRateList = clickRateDao.queryByAppid(map);
	                    }
	                    
	                    // 排序
	                     appInfoList = AppInfoClickSort.sortAppInfo(
	                            roleAndAppInfoDtoList, clickRateList);

	                    return ResponseVoUtil.success(appInfoList, ""); 
			        }else{
			            return ResponseVoUtil.success(appInfoList, "您无应用"); 
			        }
	                
			    }else{
			        return ResponseVoUtil.success(appInfoList, "您无应用");
			    }
	            
			}else{
			    return ResponseVoUtil.fail(responseDto.getMessage(), null);
			}
			
		} catch (Exception e) {
			LOG.error("UserRestServiceImpl getUserInfoFromPAP error", e);
		}
		return ResponseVoUtil.fail("获取失败", null);
	}

	/*****
	 * 功能：获取某一个应用所有的角色 创建人：donghongguang 创建时间：2017年4月25日 下午1:18:03
	 * 
	 * @param
	 * @return
	 * @version 1.0.0
	 */
	@Override
	public ResponseVo getAppAllRole(String appId, String appVersion) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appId", appId);
		map.put("appVersion", appVersion);
		logger.info("调用pap接口查询应用角色=========》" + JsonUtil.toJSon(map));
		String data = HttpClientUtil.post(queryAppRoles, JsonUtil.toJSon(map));
		logger.info("调用pap接口来给用户赋予角色返回的数据: " + data);
		objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ResponseVo responseVo = JsonUtil.readValue(data, ResponseVo.class);
		return responseVo;
	}

}
