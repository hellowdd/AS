package com.bocom.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bocom.domain.AppJudge;
import com.bocom.domain.Favorite;
import com.bocom.domain.ResponseVo;
import com.bocom.domain.Status;
import com.bocom.dto.FavoriteDto;
import com.bocom.dto.StatusDto;
import com.bocom.service.AppJudgeService;
import com.bocom.service.ApplyService;
import com.bocom.service.FavoriteService;
import com.bocom.util.CreateUUidUtil;
import com.bocom.util.JsonUtil;
import com.bocom.util.PageUtil;
import com.bocom.util.ResponseVoUtil;
import com.github.pagehelper.PageInfo;

/*****
 * 类名称：ApiMobileController
 * 类描述：对外接口
 * 创建人：donghongguang
 * 创建时间：2017年7月17日 下午1:50:15
 * 修改人：
 * 修改时间：
 * @version 1.0.0
 */
@Controller
@RequestMapping("/mobile/rest")
public class ApiMobileController
{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AppJudgeService appJudgeService;

    @Autowired
    private ApplyService applyService;
    
    @Autowired
    private FavoriteService favoriteService;


    /*****
     * 功能：评论
     * 创建人：donghongguang
     * 创建时间：2017年7月17日 下午1:52:14
     * @param 
     * @return 
     * @version 1.0.0
     */
    @RequestMapping("/insertAppJudge")
    @ResponseBody
    public ResponseVo insertAppJudge(@RequestBody AppJudge appJudge) {

        try {
            appJudgeService.insertAppJudge(appJudge);
            return ResponseVoUtil.success("", "插入成功");
        } catch (Exception e) {
            logger.error("插入应用评价失败==============》" + e.getMessage());
            return ResponseVoUtil.fail("插入应用评价失败", "插入应用评价失败");

        }
    }
    
    /*****
     * 功能：获取单个应用的所有评价信息 支持分页
     * 创建人：donghongguang
     * 创建时间：2017年5月4日 下午1:48:13
     * @param 
     * @return 
     * @version 1.0.0
     */
    @RequestMapping("/getAppJudgeInfoList")
    @ResponseBody
    public Map getAppJudgeInfoList(@RequestBody AppJudge appJudge) {
        appJudge.setIsPublic("1"); //1代表公开
        return appJudgeService.getAppJudgeInfoListMobile(appJudge);
    }
    
    /**
     * 
     * applyList:根据userId查询申请的列表. <br/>
     * 
     * @author Mr-Wei
     * @param userId
     *            用户id
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("/applyList")
    @ResponseBody
    public String applyList(@RequestBody Map map, HttpServletRequest request) {
        PageInfo pageInfo = null;
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            String userId = (String) map.get("userId");
            if (userId != null) {
                paramMap.put("userId", userId);
            }
            String statusCode = (String) map.get("statusCode");
            if (statusCode != null) {
                paramMap.put("statusCode", statusCode);
            }
//            PageUtil.setParams(request, paramMap);
            Integer pageSize =(Integer) map.get("pageSize");
            Integer pageNum =(Integer) map.get("pageNum");
            paramMap.put("pageSize", pageSize);
            paramMap.put("pageNum", pageNum);
            List<StatusDto> list = applyService.queryApplyList(paramMap);
            //将roleIds中的数字去除
            for(StatusDto statusDto:list){
                String roleIds=statusDto.getRoleIds();
                roleIds=roleIds.replaceAll("\\d{1,}:", "");
                statusDto.setRoleIds(roleIds);
            }
            pageInfo = new PageInfo(list);
        } catch (Exception e) {
            logger.info("queryApplyList==============> 出错" + e.getMessage());
        }
        return Base64
                .encodeBase64String(JsonUtil.toJSon(PageUtil.covertMap(new Object[] { "data" },
                        new Object[] { pageInfo })).getBytes());

    }

    @RequestMapping("/delApply")
    @ResponseBody
    public String delApply(@RequestParam(required = true) String id) {
        ResponseVo responseVo = new ResponseVo();
        try {
            applyService.delApply(id);
            responseVo = ResponseVoUtil.success("","");
        } catch (Exception e) {
            logger.error("delApply============> 错误" + e.getMessage());
            responseVo = ResponseVoUtil.fail(e.getMessage(),e.getMessage());
        }
        return Base64
                .encodeBase64String(JsonUtil.toJSon(responseVo).getBytes());

    }

    @RequestMapping("/insertApply")
    @ResponseBody
    public String insertApply(@RequestBody Status status) {
        ResponseVo responseVo = new ResponseVo();
        try {
            String id = CreateUUidUtil.createUuid();
            status.setId(id);
            int i=applyService.insertApply(status);
            if(i==-1){
                responseVo = ResponseVoUtil.fail("已经在审批中","已经在审批中");
            }else if (i >= 1) {
                responseVo = ResponseVoUtil.success("", "");
            } else {
                responseVo = ResponseVoUtil.fail("加入我的申请失败", "加入我的申请失败");
            }
        } catch (Exception e) {
            logger.error("delApply============> 错误" + e.getMessage());
            responseVo = ResponseVoUtil.fail(e.getMessage(),e.getMessage());
        }
        return Base64
                .encodeBase64String(JsonUtil.toJSon(responseVo).getBytes());
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
    public String insertFavorite(@RequestBody Favorite favorite) {
        ResponseVo responseVo = new ResponseVo();
        try {
            int i = favoriteService.insertFavorite(favorite);
            if (i == -1) {
                responseVo = ResponseVoUtil.fail("收藏夹中已存在该应用", "收藏夹中已存在该应用");
            } else if (i >= 1) {
                responseVo = ResponseVoUtil.success("", "");
            } else {
                responseVo = ResponseVoUtil.fail("加入收藏夹失败", "加入收藏夹失败");
            }

        } catch (Exception e) {
            logger.info("delFavorite==============> 出错" + e.getMessage());
            responseVo = ResponseVoUtil.fail(e.getMessage(), e.getMessage());
        }
        return Base64
                .encodeBase64String(JsonUtil.toJSon(responseVo).getBytes());
    }
    
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
    public String queryFavoriteList(HttpServletRequest request) {
        PageInfo pageInfo = null;
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            String userId = request.getParameter("userId");
            if (userId != null) {
                paramMap.put("userId", userId);
            }
            String updateBy = request.getParameter("updateBy");
//            if (userId != null) {
                paramMap.put("updateBy", "1");
//            }
            PageUtil.setParams(request, paramMap);
            List<FavoriteDto> list = favoriteService
                    .queryFavoriteList(paramMap);
            pageInfo = new PageInfo(list);
        } catch (Exception e) {
            logger.info("queryFavoriteList==============> 出错" + e.getMessage());
        }
        return Base64
                .encodeBase64String(JsonUtil.toJSon(PageUtil.covertMap(new Object[] { "data" },
                        new Object[] { pageInfo })).getBytes());

    }
    
}
