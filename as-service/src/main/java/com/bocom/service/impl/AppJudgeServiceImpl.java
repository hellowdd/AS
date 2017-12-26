
 /**  
 * Project Name:as-service  
 * File Name:AppJudgeServiceImpl.java  
 * Package Name:com.bocom.service.impl  
 * Date:2017年5月4日上午10:38:02  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
*/  
  
package com.bocom.service.impl;  

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bocom.dao.AppJudgeDao;
import com.bocom.dao.AppJudgeInfoDao;
import com.bocom.domain.AppJudge;
import com.bocom.domain.AppJudgeInfo;
import com.bocom.domain.ResponseVo;
import com.bocom.dto.SessionUserInfo;
import com.bocom.service.AppJudgeService;
import com.bocom.util.CreateUUidUtil;
import com.bocom.util.PageUtil;
import com.bocom.util.ResponseVoUtil;
import com.github.pagehelper.PageInfo;

/**  
 * ClassName:AppJudgeServiceImpl <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2017年5月4日 上午10:38:02 <br/>  
 * @author   Mr-Wei  
 * @version    
 * @since    JDK 1.7  
 * @see        
 */
@Service
public class AppJudgeServiceImpl extends BaseService implements AppJudgeService {
	
	@Autowired
	private AppJudgeDao appJudgeDao;
	@Autowired
    private AppJudgeInfoDao appJudgeInfoDao;
//	@Autowired
//    protected HttpServletRequest request;

	@Override
	public int insertAppJudge(AppJudge appJudge) {
		String id=CreateUUidUtil.createUuid();
		appJudge.setId(id);
		//首次评论，加入分数，加入评论信息
		if(appJudgeDao.insertSelective(appJudge)>0){
		    AppJudgeInfo judgeInfo = new AppJudgeInfo();
	        judgeInfo.setCreateTime(new Date());
	        judgeInfo.setJudgeContent(appJudge.getAppJudge());
	        judgeInfo.setJudgeId(id);
	        judgeInfo.setId(CreateUUidUtil.createUuid());
	        return appJudgeInfoDao.insertSelective(judgeInfo);
		}
		return 0;
	}

	@Override
	public int appendAppJudge(AppJudge appJudge) {
		/**
		 * 首先查询评价信息，
		 * 查到之后更改评分，
		 * 新增评论信息
		 */
	    String id = appJudge.getId();
	    AppJudge appJudges = appJudgeDao.selectByPrimaryKey(id);
	    if(null != appJudges && null != appJudges.getAppScore()){
	        //更改评分
	        if(appJudgeDao.appendAppJudge(appJudge)>0){
	            AppJudgeInfo judgeInfo = new AppJudgeInfo();
	            judgeInfo.setCreateTime(new Date());
	            judgeInfo.setJudgeContent(appJudge.getAppJudgeAdd());
	            judgeInfo.setJudgeId(id);
	            judgeInfo.setId(CreateUUidUtil.createUuid());
	            return appJudgeInfoDao.insertSelective(judgeInfo);
	        }
	    }
	        return 0;

//		appJudge.setJudgeAddTime(new Date());
//		String appSocerAdd=appJudgeDao.queryAppScoreAdd(appJudge);
//		//此时已评论
//		if(StringUtils.isNotBlank(appSocerAdd)){
//			return -1;
//		}else{
//			return appJudgeDao.appendAppJudge(appJudge);
//		}
		
	}

	/*****
     * 功能：获取评价信息列表
     * 创建人：donghongguang
     * 创建时间：2017年5月4日 下午2:29:45
     * @param 
     * @return 
     * @version 1.0.0
     */
    @Override
    public Map getAppJudgeInfoList(AppJudge appJudge)
    {
        PageInfo pageInfo = null;
        String score = "";
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            if (appJudge.getAppId() != null) {
                paramMap.put("appId", appJudge.getAppId());
            }
            if (appJudge.getAppVersion() != null) {
                paramMap.put("appVersion", appJudge.getAppVersion());
            }
            if (appJudge.getCreateBy() != null) {
                paramMap.put("createBy", appJudge.getCreateBy());
            }
            if (appJudge.getIsPublic() != null) {
                paramMap.put("isPublic", appJudge.getIsPublic());
            }
            PageUtil.setParams(request, paramMap);
            PageUtil.dealPage(paramMap);
            List<AppJudge> list = appJudgeDao.selectJudgeInfoAll(paramMap);
            pageInfo = new PageInfo(list);
            List<AppJudge> scoreList = appJudgeDao.selectJudgeScore(paramMap);
            if(null != scoreList && scoreList.size()>0){
                score = String.valueOf(scoreList.get(0).getAppScore());
            }
        } catch (Exception e) {
             e.getMessage();
             logger.error("获取评论失败============》"+e.getMessage());
        }
        Map map = PageUtil.covertMap(new Object[] { "page" },
                new Object[] { pageInfo });
        map.put("app_score_avg", score);
        return map;
    }
    /*****
     * 功能：获取评价信息列表
     * 创建人：donghongguang
     * 创建时间：2017年5月4日 下午2:29:45
     * @param 
     * @return 
     * @version 1.0.0
     */
    @Override
    public Map getAppJudgeInfoListMobile(AppJudge appJudge)
    {
        PageInfo pageInfo = null;
        String score = "";
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            if (appJudge.getAppId() != null) {
                paramMap.put("appId", appJudge.getAppId());
            }
            if (appJudge.getAppVersion() != null) {
                paramMap.put("appVersion", appJudge.getAppVersion());
            }
            if (appJudge.getCreateBy() != null) {
                paramMap.put("createBy", appJudge.getCreateBy());
            }
            if (appJudge.getIsPublic() != null) {
                paramMap.put("isPublic", appJudge.getIsPublic());
            }
            PageUtil.setParams(request, paramMap);
            if (appJudge.getPageNum() != null) {
                paramMap.put("pageNum", appJudge.getPageNum());
            }
            if (appJudge.getPageSize() != null) {
                paramMap.put("pageSize", appJudge.getPageSize());
            }
            PageUtil.dealPage(paramMap);
            List<AppJudge> list = appJudgeDao.selectJudgeInfoAll(paramMap);
            pageInfo = new PageInfo(list);
            List<AppJudge> scoreList = appJudgeDao.selectJudgeScore(paramMap);
            if(null != scoreList && scoreList.size()>0){
                score = String.valueOf(scoreList.get(0).getAppScore());
            }
        } catch (Exception e) {
             e.getMessage();
             logger.error("获取评论失败============》"+e.getMessage());
        }
        Map map = PageUtil.covertMap(new Object[] { "data" },
                new Object[] { pageInfo });
        map.put("app_score_avg", score);
        return map;
    }

    /*****
     * 功能：获取评价信息
     * 创建人：donghongguang
     * 创建时间：2017年5月4日 下午2:29:49
     * @param 
     * @return 
     * @version 1.0.0
     */
    @Override
    public ResponseVo getMyAppJudge(AppJudge appJudge)
    {
        /**
         * session 获取用户信息
         * 赋值，传递
         */
        try
        {
            HttpSession session = request.getSession();
            SessionUserInfo userInfo = (SessionUserInfo) session.getAttribute("sessionUserInfo");
            
            Map<String, Object> paramMap = new HashMap<String, Object>();
            if (appJudge.getAppId() != null) {
                paramMap.put("appId", appJudge.getAppId());
            }
            if (appJudge.getAppVersion() != null) {
                paramMap.put("appVersion", appJudge.getAppVersion());
            }
            paramMap.put("createBy", userInfo.getUserId());
//            paramMap.put("createBy", "11");
            
            List<AppJudge> appJudgeList = appJudgeDao.selectJudgeInfoAll(paramMap);
            if(null != appJudgeList && appJudgeList.size() > 0){
                return ResponseVoUtil.success(appJudgeList.get(0),"success");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseVoUtil.fail("获取失败", null);
        }
        return ResponseVoUtil.fail("获取失败", null);
    }
	
	

}
  
