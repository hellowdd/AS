/**  
 * Project Name:as-web  
 * File Name:ApiController.java  
 * Package Name:com.bocom.controller.api  
 * Date:2017年4月25日下午2:59:39  
 * Copyright (c) 2017, Mr-Wei@bocom.com All Rights Reserved.  
 *  
 */

package com.bocom.controller.api;

import java.net.URLDecoder;
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

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bocom.domain.ClickRate;
import com.bocom.domain.ResponseVo;
import com.bocom.dto.req.AppPageParamDto;
import com.bocom.dto.resp.AppInfo;
import com.bocom.dto.resp.DataDto;
import com.bocom.service.ApplyService;
import com.bocom.service.ClickRateService;
import com.bocom.service.FavoriteService;
import com.bocom.service.arcm.ArcmService;
import com.bocom.util.JsonUtil;
import com.bocom.util.ResponseUtil;
import com.bocom.util.ResponseVoUtil;
import com.github.pagehelper.PageInfo;

/**
 * ClassName:ApiController <br/>
 * Function: 对外提供接口类 <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年4月25日 下午2:59:39 <br/>
 * 
 * @author Mr-Wei
 * @version
 * @since JDK 1.7
 * @see
 */

@Controller
@RequestMapping("/as/rest")
public class ApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ArcmService arcmService;
	
	

	@Autowired
	private ApplyService applyService;
	@Autowired
	private FavoriteService favoriteService;

	@Autowired
	private ClickRateService clickRateService;
	@Value("${fastDFS.http.url}")
    private String fastDfsUrl;

	@RequestMapping("/addRate")
	@ResponseBody
	public ResponseVo addRate(@RequestBody ClickRate clickRate) {
		try {
			if (clickRateService.upClickRate(clickRate) > 0) {
				return ResponseVoUtil.success("", "");
			}
			return ResponseVoUtil.fail("", "");

		} catch (Exception e) {
			logger.info("queryApplyList==============> 出错" + e.getMessage());
			return ResponseVoUtil.fail(e.getMessage(), e.getMessage());
		}

	}

	@RequestMapping("/queryAllAppInfo")
	@ResponseBody
	public String queryAllAppInfo(@RequestBody AppPageParamDto dto,
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
			responseVo = arcmService.queryMobileApp(dto);
			Map map = (Map) responseVo.getData();
			if (!map.isEmpty()) {
				Map maplist = new HashMap();
				list = (List) map.get("list");
				if (list != null && list.size() > 0) {
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
												(long) mapAppInfo
														.get("createTime"));
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
											(String) mapAppInfo
													.get("appOrgName"),
											(String) mapAppInfo.get("uploadBy"),
											(String) mapAppInfo
													.get("uploadByName"),
											(Integer) mapAppInfo
													.get("deployNodeNum"),
											(String) mapAppInfo
													.get("companyId"),
											(String) mapAppInfo
													.get("companyCode"),
											(String) mapAppInfo
													.get("companyName"),
											(String) mapAppInfo
													.get("appCategory"),
											(String) mapAppInfo
													.get("bizCategory"),
											(String) mapAppInfo.get("appType"),
											(String) mapAppInfo.get("aaType"),
											(String) mapAppInfo.get("url"),
											(Integer) mapAppInfo
													.get("runStatus"),
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
					ResponseVo responseVoCategory = arcmService
							.queryCategory(null);
					List<Map<String, Object>> listCategory = (List) responseVoCategory
							.getData();
					if (listCategory != null && listCategory.size() > 0) {
						for (Map<String, Object> mapCategory : listCategory) {
							DataDto dataDto = new DataDto();

							List<AppInfo> newlistAppInfo = new ArrayList<AppInfo>();
							dataDto.setBizCategoryName((String) mapCategory
									.get("name"));
							for (AppInfo appInfo2 : listAppInfo) {
								if (mapCategory.get("id").equals(
										appInfo2.getBizCategory())) {
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
							if (newlistAppInfo != null
									&& newlistAppInfo.size() > 0) {
								dataDto.setList(newlistAppInfo);
								data.add(dataDto);
							}

						}
					}

					responseVo.setData(data);
				} else {
					responseVo.setData(null);
				}

			} else {
				responseVo.setData(null);
			}
		} catch (Exception e) {
			logger.error("AppInfoController ---> queryAllAppInfo error", e);
		}
		return Base64
				.encodeBase64String(JsonUtil.toJSon(responseVo).getBytes());

	}

	@RequestMapping("/auditApply")
	@ResponseBody
	public String auditApply(@RequestParam(required = true) String isAgree,
			@RequestParam(required = true) String id,
			@RequestParam(required = true) String userId,
			@RequestParam String orgCode, @RequestParam String statusRemark) {
		// 同意该申请
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("statusId", isAgree);
			map.put("id", id);
			map.put("userId", userId);
			map.put("orgCode", orgCode);
			map.put("statusRemark", URLDecoder.decode(statusRemark.replaceAll("%", "%25"), "utf-8"));
			if (applyService.auditApply(map) > 0) {
				return Base64.encodeBase64String(JsonUtil.toJSon(
						ResponseVoUtil.success("", "")).getBytes());
			} else {
				return Base64.encodeBase64String(JsonUtil.toJSon(
						ResponseVoUtil.fail("申请失败", "申请失败")).getBytes());
			}

		} catch (Exception e) {
			logger.error("auditApply===================>" + e.getMessage());
			return Base64.encodeBase64String(JsonUtil.toJSon(
					ResponseVoUtil.fail(e.getMessage(), e.getMessage()))
					.getBytes());
		}

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
	public String delFavorite(@RequestParam(required = true) String id) {
		try {
			favoriteService.delFavorite(id);
			return Base64.encodeBase64String(JsonUtil.toJSon(
					ResponseVoUtil.success("", "")).getBytes());
		} catch (Exception e) {
			logger.info("delFavorite==============> 出错" + e.getMessage());
			return Base64.encodeBase64String(JsonUtil.toJSon(
					ResponseVoUtil.fail(e.getMessage(), e.getMessage()))
					.getBytes());
			
		}

	}

}
