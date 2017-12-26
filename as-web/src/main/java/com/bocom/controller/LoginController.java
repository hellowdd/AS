package com.bocom.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bocom.domain.ResponseVo;



@Controller
@RequestMapping("/")
public class LoginController {

	@RequestMapping(method=RequestMethod.GET)
	public String homePage() {
		return "redirect:/manager/loginAction/loginCas";
	}
	/**
	 * 结合sso自动登录访问地址
	 */
	@RequestMapping(value = "/manager/loginAction/loginCas")
	public String loginCas(ModelMap model, HttpSession session) {
		return "home/homepage";
	}
	
	/**
	 *结合sso自动登录访问地址
	 */
	@RequestMapping(value="/manager/loginAction/loginCasOut", method=RequestMethod.POST)
	@ResponseBody
	public ResponseVo loginCasOut(HttpSession session){
		session.invalidate();
		return new ResponseVo();
	}
}
