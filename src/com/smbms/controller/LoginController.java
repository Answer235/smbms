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
	//��¼
	@RequestMapping("/login.html")
	public String login(@RequestParam String userCode,
				@RequestParam String userPassword,HttpServletRequest request) {
		//��֤�û���������
		User user = userService.getUserByCP(userCode, userPassword);
		if(user != null) {
			request.getSession().setAttribute(Tool.USER_SESSION, user);
			return "frame";
		}else {
			request.setAttribute("error", "�û������������");
			//forward:ת�����ᱻ��ͼ����������
			//redirect:�ض���
			return "forward:login.jsp";
		}
	}
	//�˳�
	@RequestMapping("/logout.html")
	public String logout(HttpSession session) {
		session.removeAttribute(Tool.USER_SESSION);
		return "redirect:login.jsp";
	}
}
