package com.bocom.controller.view;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/manager/view/deploy")
public class DeployViewController {
	  
	/**
	 *默认主页
	 */	
	@RequestMapping("/homepage")
	public String homepage( ModelMap model){
		return "home/homepage";
	} 
	/**
	 *申请列表
	 */	
	@RequestMapping("/applyList")
	public String undeploy( ModelMap model){
		return "subpage/myapply";
	} 
	
	/**
	 *审核列表
	 */	
	@RequestMapping("/approveList")
	public String updata( ModelMap model){
		return "subpage/myappro";
	} 
	
	/**
	 *驳回审核
	 */	
	@RequestMapping("/reject")
	public String sysView( ModelMap model){
		return "subpage/reject";
	} 
	
	/**
	 *收藏列表
	 */	
	@RequestMapping("/storelist")
	public String deploy_step1( ModelMap model){
		return "subpage/mystore";
	} 
	
	/**
	 *我的应用
	 */	
	@RequestMapping("/myapplist")
	public String deploy_step2( ModelMap model){
		return "subpage/myapp";
	} 
	/**
	 *我的应用
	 */	
	@RequestMapping("/myapplist1")
	public String myapplist1( ModelMap model){
		return "subpage/myapp1";
	}	
	/**
	 *新应用
	 */	
	@RequestMapping("/newapplist")
	public String deploy_step3( ModelMap model){
		return "subpage/newapp";
	} 
	
	/**
	 *应用排行榜
	 */	
	@RequestMapping("/ranklist")
	public String waitView( ModelMap model){
		return "subpage/rank";
	} 
	
	/**
	 *申请审核
	 */	
	@RequestMapping("/formapply")
	public String handleDeploy( ModelMap model){
		return "subpage/formapply";
	} 
	/**
	 *评价
	 */	
	@RequestMapping("/evaluate")
	public String handleevaluate( ModelMap model){
		return "subpage/evaluate";
	} 
	/**
	 *评价追加
	 */	
	@RequestMapping("/evaluateadd")
	public String evaluateadd( ModelMap model){
		return "subpage/evaluateadd";
	} 	
	/**
	 *详情页
	 */	
	@RequestMapping("/detail")
	public String handledetail( ModelMap model){
		return "subpage/detail";
	}	
	/**
	 *评价
	 */	
	@RequestMapping("/evaList")
	public String handleevalist( ModelMap model){
		return "subpage/evalist";
	}
	/**
	 *评价浏览
	 */	
	@RequestMapping("/viewlist")
	public String vieweva( ModelMap model){
		return "subpage/view";
	}
	/**
	 *查看更多评价
	 */	
	@RequestMapping("/moreeva")
	public String moreeva( ModelMap model){
		return "subpage/moreeva";
	}	
}
