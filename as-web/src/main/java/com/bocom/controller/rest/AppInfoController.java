/**  
 * Project Name:as-web  
 * File Name:ApplyController.java  
 * Package Name:com.bocom.controller  
 * Date:2017年4月24日上午10:02:01  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
 */

package com.bocom.controller.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bocom.domain.ClickRate;
import com.bocom.domain.ResponseVo;
import com.bocom.dto.Office;
import com.bocom.dto.SessionUserInfo;
import com.bocom.dto.SessionUserInfoDto;
import com.bocom.dto.req.AppPageParamDto;
import com.bocom.dto.req.AppPureDetailParamDto;
import com.bocom.dto.resp.AppInfo;
import com.bocom.dto.resp.DataDto;
import com.bocom.service.ClickRateService;
import com.bocom.service.arcm.ArcmService;
import com.bocom.service.user.UserRestService;
import com.github.pagehelper.PageInfo;

/**
 * ClassName:调用Arcm的controller <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月26日 下午10:02:01 <br/>
 * 
 * @author liuyunfeng
 * @version
 * @since JDK 1.8
 * @see
 */
@Controller
@RequestMapping("/api")
public class AppInfoController {
	private static Logger LOG = LoggerFactory
			.getLogger(AppInfoController.class);
	@Autowired
	private ArcmService arcmService;
	@Autowired
	private ClickRateService clickRateService;
	
	@Value("${fastDFS.http.url}")
    private String fastDfsUrl;

	/**
     * 登陆业务
     */
	@Autowired
    private UserRestService userRestService;

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping("/queryAllAppInfo")
	@ResponseBody
	public ResponseVo queryAllAppInfo(@RequestBody AppPageParamDto dto,
			HttpServletRequest request) {
		PageInfo pageInfo = null;
		ResponseVo responseVo = new ResponseVo();
		ResponseVo responseVo1 = new ResponseVo();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		List<String> listAppId = new ArrayList<String>();
		List<AppInfo> listAppInfo = new ArrayList<AppInfo>();
		List<String> listNullEnum = new ArrayList<String>();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		List<DataDto> data = new ArrayList<DataDto>();
		Set<String> hashPre = new HashSet<String>();
		dto.setPageNum(1);
		dto.setPageSize(Integer.MAX_VALUE);
		try {
			responseVo = arcmService.queryAppPage(dto);
			Map map = (Map) responseVo.getData();
			if (!map.isEmpty()) {
				Map maplist = new HashMap();
				list = (List) map.get("list");
				if(list != null && list.size()>0){
				    for (Map<String, Object> map11 : list) {
	                    String appId = (String) map11.get("appId");
	                    listAppId.add(appId);
	                }
	                maplist.put("appids", listAppId);
	                List<ClickRate> listClickRate = clickRateService
	                        .queryByAppid(maplist);
	                AppInfo appInfo = null;
	                if (listClickRate != null && !listClickRate.isEmpty()) {
	                    for (ClickRate clickRate : listClickRate) {
	                        for (Map<String, Object> mapAppInfo : list) {
	                            if (mapAppInfo.get("appId").equals(
	                                    clickRate.getAppId())) {
	                                // 长整型的毫秒值数据
	                                Date date = null;
	                                if (mapAppInfo.get("createTime") != null
	                                        && mapAppInfo.get("createTime") != "") {
	                                    date = new Date(
	                                            (long) mapAppInfo.get("createTime"));
	                                } else {
	                                    date = new Date((long) 0);
	                                }
	                                // 标准日历系统类
	                                GregorianCalendar gc = new GregorianCalendar();
	                                gc.setTime(date);
	                                // java.text.SimpleDateFormat，设置时间格式
	                                SimpleDateFormat format = new SimpleDateFormat(
	                                        "yyyy-MM-dd hh:mm:ss");
	                                // 得到毫秒值转化的时间
	                                String time = format.format(gc.getTime());
	                                appInfo = new AppInfo(
	                                        (String) mapAppInfo.get("id"),
	                                        (String) mapAppInfo.get("appId"),
	                                        (String) mapAppInfo.get("appName"),
	                                        (String) mapAppInfo.get("appDesc"),
	                                        (String) mapAppInfo.get("logoWeb"),
	                                        (String) mapAppInfo.get("logoApp"),
	                                        (String) mapAppInfo.get("version"),
	                                        (String) mapAppInfo
	                                                .get("storeLocation"),
	                                        (String) mapAppInfo.get("appOrg"),
	                                        (String) mapAppInfo.get("appOrgName"),
	                                        (String) mapAppInfo.get("uploadBy"),
	                                        (String) mapAppInfo.get("uploadByName"),
	                                        (Integer) mapAppInfo
	                                                .get("deployNodeNum"),
	                                        (String) mapAppInfo.get("companyId"),
	                                        (String) mapAppInfo.get("companyCode"),
	                                        (String) mapAppInfo.get("companyName"),
	                                        (String) mapAppInfo.get("appCategory"),
	                                        (String) mapAppInfo.get("bizCategory"),
	                                        (String) mapAppInfo.get("appType"),
	                                        (String) mapAppInfo.get("aaType"),
	                                        (String) mapAppInfo.get("url"),
	                                        (Integer) mapAppInfo.get("runStatus"),
	                                        (Integer) mapAppInfo.get("status"),
	                                        (String) mapAppInfo.get("createBy"),
	                                        time, clickRate.getClickRate());
	                                listAppInfo.add(appInfo);
	                                list1.add(mapAppInfo);
	                            }
	                        }
	                        for (Map<String, Object> mapx : list1) {
	                            list.remove(mapx);
	                        }
	                    }
	                    for (Map<String, Object> mapAppInfo : list) {
	                        Long ClickRate = (long) 0;
	                        // 长整型的毫秒值数据
	                        Date date = null;
	                        if (mapAppInfo.get("createTime") != null
	                                && mapAppInfo.get("createTime") != "") {
	                            date = new Date((long) mapAppInfo.get("createTime"));
	                        } else {
	                            date = new Date((long) 0);
	                        }
	                        // 标准日历系统类
	                        GregorianCalendar gc = new GregorianCalendar();
	                        gc.setTime(date);
	                        // java.text.SimpleDateFormat，设置时间格式
	                        SimpleDateFormat format = new SimpleDateFormat(
	                                "yyyy-MM-dd hh:mm:ss");
	                        // 得到毫秒值转化的时间
	                        String time = format.format(gc.getTime());
	                        appInfo = new AppInfo((String) mapAppInfo.get("id"),
	                                (String) mapAppInfo.get("appId"),
	                                (String) mapAppInfo.get("appName"),
	                                (String) mapAppInfo.get("appDesc"),
	                                (String) mapAppInfo.get("logoWeb"),
	                                (String) mapAppInfo.get("logoApp"),
	                                (String) mapAppInfo.get("version"),
	                                (String) mapAppInfo.get("storeLocation"),
	                                (String) mapAppInfo.get("appOrg"),
	                                (String) mapAppInfo.get("appOrgName"),
	                                (String) mapAppInfo.get("uploadBy"),
	                                (String) mapAppInfo.get("uploadByName"),
	                                (Integer) mapAppInfo.get("deployNodeNum"),
	                                (String) mapAppInfo.get("companyId"),
	                                (String) mapAppInfo.get("companyCode"),
	                                (String) mapAppInfo.get("companyName"),
	                                (String) mapAppInfo.get("appCategory"),
	                                (String) mapAppInfo.get("bizCategory"),
	                                (String) mapAppInfo.get("appType"),
	                                (String) mapAppInfo.get("aaType"),
	                                (String) mapAppInfo.get("url"),
	                                (Integer) mapAppInfo.get("runStatus"),
	                                (Integer) mapAppInfo.get("status"),
	                                (String) mapAppInfo.get("createBy"), time,
	                                ClickRate);
	                        listAppInfo.add(appInfo);
	                    }
	                } else {
	                    for (Map<String, Object> mapAppInfo : list) {
	                        Long ClickRate = (long) 0;
	                        // 长整型的毫秒值数据
	                        Date date = null;
	                        if (mapAppInfo.get("createTime") != null
	                                && mapAppInfo.get("createTime") != "") {
	                            date = new Date((long) mapAppInfo.get("createTime"));
	                        } else {
	                            date = new Date((long) 0);
	                        }
	                        // 标准日历系统类
	                        GregorianCalendar gc = new GregorianCalendar();
	                        gc.setTime(date);
	                        // java.text.SimpleDateFormat，设置时间格式
	                        SimpleDateFormat format = new SimpleDateFormat(
	                                "yyyy-MM-dd hh:mm:ss");
	                        // 得到毫秒值转化的时间
	                        String time = format.format(gc.getTime());
	                        appInfo = new AppInfo((String) mapAppInfo.get("id"),
	                                (String) mapAppInfo.get("appId"),
	                                (String) mapAppInfo.get("appName"),
	                                (String) mapAppInfo.get("appDesc"),
	                                (String) mapAppInfo.get("logoWeb"),
	                                (String) mapAppInfo.get("logoApp"),
	                                (String) mapAppInfo.get("version"),
	                                (String) mapAppInfo.get("storeLocation"),
	                                (String) mapAppInfo.get("appOrg"),
	                                (String) mapAppInfo.get("appOrgName"),
	                                (String) mapAppInfo.get("uploadBy"),
	                                (String) mapAppInfo.get("uploadByName"),
	                                (Integer) mapAppInfo.get("deployNodeNum"),
	                                (String) mapAppInfo.get("companyId"),
	                                (String) mapAppInfo.get("companyCode"),
	                                (String) mapAppInfo.get("companyName"),
	                                (String) mapAppInfo.get("appCategory"),
	                                (String) mapAppInfo.get("bizCategory"),
	                                (String) mapAppInfo.get("appType"),
	                                (String) mapAppInfo.get("aaType"),
	                                (String) mapAppInfo.get("url"),
	                                (Integer) mapAppInfo.get("runStatus"),
	                                (Integer) mapAppInfo.get("status"),
	                                (String) mapAppInfo.get("createBy"), time,
	                                ClickRate);
	                        listAppInfo.add(appInfo);
	                    }
	                }
	                for (AppInfo appInfo1 : listAppInfo) {
	                    hashPre.add(appInfo1.getBizCategory());
	                }
	                ResponseVo responseVoCategory = arcmService.queryCategory(null);
	                List<Map<String,Object>> listCategory = (List) responseVoCategory.getData();
	                if(listCategory != null && listCategory.size()>0){
	                    for(Map<String, Object> mapCategory : listCategory){
	                        DataDto dataDto = new DataDto();
	                        
	                        List<AppInfo> newlistAppInfo = new ArrayList<AppInfo>();
	                        dataDto.setBizCategoryName((String) mapCategory.get("name"));
	                        for (AppInfo appInfo2 : listAppInfo) {
	                            if (mapCategory.get("id").equals(appInfo2.getBizCategory())) {
	                                if(null != appInfo2.getLogoApp() && !appInfo2.getLogoApp().equals("")){
	                                    if(appInfo2.getLogoApp().startsWith("http://")){
	                                        
	                                    }else{
	                                        appInfo2.setLogoApp(fastDfsUrl + appInfo2.getLogoApp());
	                                    }
	                                }
	                                if(null != appInfo2.getLogoWeb() && !appInfo2.getLogoWeb().equals("")){
                                        if(appInfo2.getLogoWeb().startsWith("http://")){
                                            
                                        }else{
                                            appInfo2.setLogoWeb(fastDfsUrl + appInfo2.getLogoWeb());
                                        }
                                    }
	                                newlistAppInfo.add(appInfo2);
	                            }
	                        }
	                        if(newlistAppInfo != null && newlistAppInfo.size()>0){
	                            dataDto.setList(newlistAppInfo);
	                            data.add(dataDto);
	                        }
	                        
	                    }
	                }
	                
//	              DataDto dataDto1 = new DataDto();
	                /*for (String BizCategoryCode : hashPre) {
	                    DataDto dataDto = new DataDto();
	                    
	                    List<AppInfo> newlistAppInfo = new ArrayList<AppInfo>();
	                    String bizCategoryName = BizCategoryPoliceEnum
	                            .getNameByCode(BizCategoryCode);
	if(bizCategoryName!=null){
	                    dataDto.setBizCategoryName(bizCategoryName);
	                    for (AppInfo appInfo2 : listAppInfo) {
	                        if (BizCategoryCode.equals(appInfo2.getBizCategory())) {
	                            newlistAppInfo.add(appInfo2);
	                        }
	                    }
	                    dataDto.setList(newlistAppInfo);
	                    data.add(dataDto);
	                }else{
	                    listNullEnum.add(BizCategoryCode);
	                }
	                    }
	                for(String BizCategoryCode : listNullEnum){
	                    DataDto dataDto = new DataDto();
	                    List<AppInfo> newlistAppInfo = new ArrayList<AppInfo>();
	                    dataDto.setBizCategoryName("其他业务应用");
	                    for (AppInfo appInfo2 : listAppInfo) {
	                        if (BizCategoryCode.equals(appInfo2.getBizCategory())) {
	                            newlistAppInfo.add(appInfo2);
	                        }
	                    }
	                    dataDto.setList(newlistAppInfo);
	                    data.add(dataDto);
	                }*/
//	              }
//	              data.add(dataDto1);
	                responseVo.setData(data); 
				}else{
				    responseVo.setData(null);
				}
				
			} else {
				responseVo.setData(null);
			}
		} catch (Exception e) {
			LOG.error("AppInfoController ---> queryAllAppInfo error", e);
		}
		return responseVo;

	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping("/queryAppInfoById")
	@ResponseBody
	public ResponseVo queryAppInfoById(@RequestBody AppPureDetailParamDto dto,
			HttpServletRequest request) {
		PageInfo pageInfo = null;
		ResponseVo responseVo = new ResponseVo();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		List<AppInfo> listAppInfo = new ArrayList<AppInfo>();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		List<String> listAppId = new ArrayList<String>();
		try {
			responseVo = arcmService.queryPureAppDetail(dto);
			list = (List) responseVo.getData();
			if (!list.isEmpty() && list != null) {
				Map maplist = new HashMap();
				for (Map<String, Object> map11 : list) {
					String appId = (String) map11.get("appId");
					listAppId.add(appId);
				}
				maplist.put("appids", listAppId);
				List<ClickRate> listClickRate = clickRateService
						.queryByAppid(maplist);
				AppInfo appInfo = null;
				if (listClickRate != null && !listClickRate.isEmpty()) {
					for (ClickRate clickRate : listClickRate) {
						for (Map<String, Object> mapAppInfo : list) {
							if (mapAppInfo.get("appId").equals(
									clickRate.getAppId())) {
								Long ClickRate = (long) 0;
								// 长整型的毫秒值数据
								Date date = null;
								if (mapAppInfo.get("createTime") != null
										&& mapAppInfo.get("createTime") != "") {
									date = new Date(
											(long) mapAppInfo.get("createTime"));
								} else {
									date = new Date((long) 0);
								}
								// 标准日历系统类
								GregorianCalendar gc = new GregorianCalendar();
								gc.setTime(date);
								// java.text.SimpleDateFormat，设置时间格式
								SimpleDateFormat format = new SimpleDateFormat(
										"yyyy-MM-dd hh:mm:ss");
								// 得到毫秒值转化的时间
								String time = format.format(gc.getTime());
								appInfo = new AppInfo(
										(String) mapAppInfo.get("id"),
										(String) mapAppInfo.get("appId"),
										(String) mapAppInfo.get("appName"),
										(String) mapAppInfo.get("appDesc"),
										(String) mapAppInfo.get("logoWeb"),
										(String) mapAppInfo.get("logoApp"),
										(String) mapAppInfo.get("version"),
										(String) mapAppInfo
												.get("storeLocation"),
										(String) mapAppInfo.get("appOrg"),
										(String) mapAppInfo.get("appOrgName"),
										(String) mapAppInfo.get("uploadBy"),
										(String) mapAppInfo.get("uploadByName"),
										(Integer) mapAppInfo
												.get("deployNodeNum"),
										(String) mapAppInfo.get("companyId"),
										(String) mapAppInfo.get("companyCode"),
										(String) mapAppInfo.get("companyName"),
										(String) mapAppInfo.get("appCategory"),
										(String) mapAppInfo.get("bizCategory"),
										(String) mapAppInfo.get("appType"),
										(String) mapAppInfo.get("aaType"),
										(String) mapAppInfo.get("url"),
										(Integer) mapAppInfo.get("runStatus"),
										(Integer) mapAppInfo.get("status"),
										(String) mapAppInfo.get("createBy"),
										time, clickRate.getClickRate());
								listAppInfo.add(appInfo);
								list1.add(mapAppInfo);
							}
						}
						for (Map<String, Object> mapx : list1) {
							list.remove(mapx);
						}
					}
					for (Map<String, Object> mapAppInfo : list) {
						Long ClickRate = (long) 0;
						// 长整型的毫秒值数据
						Date date = null;
						if (mapAppInfo.get("createTime") != null
								&& mapAppInfo.get("createTime") != "") {
							date = new Date((long) mapAppInfo.get("createTime"));
						} else {
							date = new Date((long) 0);
						}
						// 标准日历系统类
						GregorianCalendar gc = new GregorianCalendar();
						gc.setTime(date);
						// java.text.SimpleDateFormat，设置时间格式
						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy-MM-dd hh:mm:ss");
						// 得到毫秒值转化的时间
						String time = format.format(gc.getTime());
						appInfo = new AppInfo((String) mapAppInfo.get("id"),
								(String) mapAppInfo.get("appId"),
								(String) mapAppInfo.get("appName"),
								(String) mapAppInfo.get("appDesc"),
								(String) mapAppInfo.get("logoWeb"),
								(String) mapAppInfo.get("logoApp"),
								(String) mapAppInfo.get("version"),
								(String) mapAppInfo.get("storeLocation"),
								(String) mapAppInfo.get("appOrg"),
								(String) mapAppInfo.get("appOrgName"),
								(String) mapAppInfo.get("uploadBy"),
								(String) mapAppInfo.get("uploadByName"),
								(Integer) mapAppInfo.get("deployNodeNum"),
								(String) mapAppInfo.get("companyId"),
								(String) mapAppInfo.get("companyCode"),
								(String) mapAppInfo.get("companyName"),
								(String) mapAppInfo.get("appCategory"),
								(String) mapAppInfo.get("bizCategory"),
								(String) mapAppInfo.get("appType"),
								(String) mapAppInfo.get("aaType"),
								(String) mapAppInfo.get("url"),
								(Integer) mapAppInfo.get("runStatus"),
								(Integer) mapAppInfo.get("status"),
								(String) mapAppInfo.get("createBy"), time,
								ClickRate);
						listAppInfo.add(appInfo);
					}
				} else {
					for (Map<String, Object> mapAppInfo : list) {
						Long ClickRate = (long) 0;
						// 长整型的毫秒值数据
						Date date = null;
						if (mapAppInfo.get("createTime") != null
								&& mapAppInfo.get("createTime") != "") {
							date = new Date((long) mapAppInfo.get("createTime"));
						} else {
							date = new Date((long) 0);
						}
						// 标准日历系统类
						GregorianCalendar gc = new GregorianCalendar();
						gc.setTime(date);
						// java.text.SimpleDateFormat，设置时间格式
						SimpleDateFormat format = new SimpleDateFormat(
								"yyyy-MM-dd hh:mm:ss");
						// 得到毫秒值转化的时间
						String time = format.format(gc.getTime());
						appInfo = new AppInfo((String) mapAppInfo.get("id"),
								(String) mapAppInfo.get("appId"),
								(String) mapAppInfo.get("appName"),
								(String) mapAppInfo.get("appDesc"),
								(String) mapAppInfo.get("logoWeb"),
								(String) mapAppInfo.get("logoApp"),
								(String) mapAppInfo.get("version"),
								(String) mapAppInfo.get("storeLocation"),
								(String) mapAppInfo.get("appOrg"),
								(String) mapAppInfo.get("appOrgName"),
								(String) mapAppInfo.get("uploadBy"),
								(String) mapAppInfo.get("uploadByName"),
								(Integer) mapAppInfo.get("deployNodeNum"),
								(String) mapAppInfo.get("companyId"),
								(String) mapAppInfo.get("companyCode"),
								(String) mapAppInfo.get("companyName"),
								(String) mapAppInfo.get("appCategory"),
								(String) mapAppInfo.get("bizCategory"),
								(String) mapAppInfo.get("appType"),
								(String) mapAppInfo.get("aaType"),
								(String) mapAppInfo.get("url"),
								(Integer) mapAppInfo.get("runStatus"),
								(Integer) mapAppInfo.get("status"),
								(String) mapAppInfo.get("createBy"), time,
								ClickRate);
						listAppInfo.add(appInfo);
					}
				}
				responseVo.setData(listAppInfo);
			} else {
				responseVo.setData(null);
			}
		} catch (Exception e) {
			LOG.error("AppInfoController ---> queryAppInfoById error", e);
		}
		return responseVo;
	}
	@RequestMapping("/queryOrgAppInfo")
    @ResponseBody
    public ResponseVo queryOrgAppInfo(@RequestBody AppPageParamDto dto,
            HttpServletRequest request) {
	    ResponseVo responseVo = new ResponseVo();
	    HttpSession session = request.getSession();
        SessionUserInfo userInfo = (SessionUserInfo) session.getAttribute("sessionUserInfo");
        SessionUserInfoDto req = new SessionUserInfoDto();
        req.setUserName(userInfo.getUserName());
	    
        Office office = userRestService.getOrgInfoFromPAP(req);
	    
//        dto.setCompanyId(office.getCode());
	    dto.setCompanyId(office.getId());
	    responseVo = queryAllAppInfo(dto, request);
	    return responseVo;
	}
}
