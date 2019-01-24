package com.smbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.smbms.pojo.Role;
import com.smbms.service.role.RoleService;

@Controller
public class RoleController {
	@Autowired
	private RoleService roleService;
	//ajax:返回值是数据，@responseBody，默认返回text/html
	@RequestMapping(value="/getrolelist.json",produces="application/json;charset=utf-8")
	@ResponseBody
	public String getRoleList() {
		List<Role> rList = roleService.getRoleList();
		//JSON.toJSONString(object);	//对象
		return JSONArray.toJSONString(rList);	//数组
	}
}
