package com.smbms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smbms.pojo.User;
import com.smbms.service.user.UserService;
import com.smbms.util.Tool;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	//登录
	@RequestMapping("/login.html")
	public String login(@RequestParam String userCode,
				@RequestParam String userPassword,HttpServletRequest request) {
		//验证用户名和密码
		User user = userService.getUserByCP(userCode, userPassword);
		if(user != null) {
			request.getSession().setAttribute(Tool.USER_SESSION, user);
			return "frame";
		}else {
			request.setAttribute("error", "用户名或密码错误！");
			//forward:转发不会被试图解析器解析
			//redirect:重定向
			return "forward:login.jsp";
		}
	}
	//退出
	@RequestMapping("/logout.html")
	public String logout(HttpSession session) {
		session.removeAttribute(Tool.USER_SESSION);
		return "redirect:login.jsp";
	}
}
