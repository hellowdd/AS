/**  
 * Project Name:as-service  
 * File Name:FavoriteServiceImpl.java  
 * Package Name:com.bocom.service.impl  
 * Date:2017年4月24日下午4:47:28  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
 */

package com.bocom.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bocom.dao.FavoriteDao;
import com.bocom.domain.Favorite;
import com.bocom.dto.AppInfoBase;
import com.bocom.dto.FavoriteDto;
import com.bocom.dto.SessionUserInfo;
import com.bocom.dto.req.AppStoreGetAppReqDto;
import com.bocom.dto.resp.AppStoreGetAppRespDto;
import com.bocom.dto.resp.AppStoreResponse;
import com.bocom.dto.resp.RoleAndAppInfoDto;
import com.bocom.service.FavoriteService;
import com.bocom.service.user.impl.UserRestServiceImpl;
import com.bocom.util.CreateUUidUtil;
import com.bocom.util.HttpClientUtil;
import com.bocom.util.PageUtil;

/**
 * ClassName:FavoriteServiceImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月24日 下午4:47:28 <br/>
 * 
 * @author Mr-Wei
 * @version
 * @since JDK 1.7
 * @see
 */
@Service
public class FavoriteServiceImpl  extends BaseService  implements FavoriteService {

    private static Logger LOG = LoggerFactory
            .getLogger(UserRestServiceImpl.class);
	@Autowired
	private FavoriteDao favoriteDao;
	@Value("${rest.pap.queryAppRoles.url}")
    private String queryAppRoles;
	@Value("${rest.pap.queryUserApp.url}")
    private String queryUserApp;
	
	private static ObjectMapper objectMapper = new ObjectMapper();

	public List<FavoriteDto> queryFavoriteList(Map map) {
		PageUtil.dealPage(map);
		List<FavoriteDto> favoriteList = favoriteDao.queryFavoriteList(map);
		//遍历查找是否拥有该应用。如果有置状态为1，没有则置状态为2
		try
        {
            if(null != favoriteList && favoriteList.size() >0){
              //查找我的应用
             // 在session中
//                HttpSession session = request.getSession();
//                SessionUserInfo userInfo = (SessionUserInfo) session
//                        .getAttribute("sessionUserInfo");
//                int userId = userInfo.getUserId();//121104;
                int userId = Integer.parseInt(favoriteList.get(0).getCreateBy());
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
                    if(appStoreGetAppRespDto != null){
                        List<RoleAndAppInfoDto> roleAndAppInfoDtoList = appStoreGetAppRespDto
                                .getData();
                        if(null != roleAndAppInfoDtoList && roleAndAppInfoDtoList.size()>0){
                            for(FavoriteDto favorite : favoriteList){
                                if(!(null != favorite.getStatusId() && favorite.getStatusId().equals("0"))){
                                    String appId = favorite.getAppId();
                                    for(RoleAndAppInfoDto appInfo : roleAndAppInfoDtoList){
                                        if(appId.equals(appInfo.getAppId())){
                                            favorite.setStatusId("1");
                                            roleAndAppInfoDtoList.remove(appInfo);
                                            break ;
                                        }
                                    } 
                                }else{
                                    
                                }
                                
                            }
                        }
                    }
                }
                
            }
        }
        
        catch (Exception e)
        {
            LOG.error("FavoriteServiceImpl queryFavoriteList error", e);
        }
		
		return favoriteList;

	}

	@Override
	public int delFavorite(String str) {
		
		return favoriteDao.delFavorite(str);

	}

	@Override
	public int insertFavorite(Favorite favorite) {
		String id=CreateUUidUtil.createUuid();
		favorite.setId(id);
		//插入之前查询是否已经收藏该应用
		int i=favoriteDao.queryMyFavoriteSize(favorite);
		if(i>0){
			//返回-1表明我的收藏中已存在该应用
			return -1;
			
		}else{
			return favoriteDao.insertFavorite(favorite);
		}
		
		
	}

}
