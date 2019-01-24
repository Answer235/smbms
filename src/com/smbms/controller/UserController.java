package com.smbms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smbms.pojo.Role;
import com.smbms.pojo.User;
import com.smbms.service.role.RoleService;
import com.smbms.service.user.UserService;
import com.smbms.util.FileUploadUtil;
import com.smbms.util.Tool;

@Controller
public class UserController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;

	@RequestMapping("/userList.html")
	public String userList(String queryname, Integer queryUserRole, HttpServletRequest request,
			@RequestParam(defaultValue = "1") Integer pageIndex) {
		// 1.查询角色列表
		List<Role> roleList = roleService.getRoleList();
		request.setAttribute("roleList", roleList);
		// 2.查询用户列表（根据userCode,userRole 分页查询）
		PageHelper.startPage(pageIndex, 8); // 分页
		List<User> userList = userService.getUserListByNR(queryname, queryUserRole);
		request.setAttribute("userList", userList);
		PageInfo<User> pageInfo = new PageInfo<User>(userList);
		request.setAttribute("totalCount", pageInfo.getTotal());
		request.setAttribute("currentPageNo", pageInfo.getPageNum());
		request.setAttribute("totalPageCount", pageInfo.getPages());
		request.setAttribute("queryUserName", queryname);
		request.setAttribute("queryUserRole", queryUserRole);
		return "userlist";
	}

	// 跳转添加页面
	@RequestMapping("/useradd.html")
	public String userAdd() {
		return "useradd";
	}

	// 验证userCode
	@RequestMapping(value = "/ucexist.json", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String ucexist(String userCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (userService.countByUserCode(userCode) > 0) {
			// 已存在
			map.put("userCode", "exist");
		} else {
			// 不存在，可用
			map.put("userCode", "noexist");
		}
		return JSONArray.toJSONString(map);
	}

	@RequestMapping("/usersave.html")
	public String userSave(User user, HttpSession session, @RequestParam(required = false) MultipartFile[] a_idPicPath,
			HttpServletRequest request) throws IllegalStateException, IOException {
		// 多个文件上传
		FileUploadUtil fuu = new FileUploadUtil(request, Tool.UPLOAD_PATH);
		for (int i = 0; i < a_idPicPath.length; i++) {
			MultipartFile mf = a_idPicPath[i];
			if (mf.isEmpty() == false) {
				if (i == 0) {
					// 身上证
					String idPicPath = fuu.all(mf, 500000, "u", "jpg", "png");
					user.setIdPicPath(idPicPath);
				} else {
					// 工作证
					String workPicPath = fuu.all(mf, 5000000, "w", "jpg", "png");
					user.setWorkPicPath(workPicPath);
				}
			}
		}
		/*
		 * 单个文件上传 if (a_idPicPath.isEmpty()==false) { FileUploadUtil fuu = new
		 * FileUploadUtil(request, Tool.UPLOAD_PATH); String idPicPath =
		 * fuu.all(a_idPicPath, 500000, "u", "jpg","png"); user.setIdPicPath(idPicPath);
		 * }
		 */
		User loginUser = (User) session.getAttribute(Tool.USER_SESSION);
		user.setCreatedBy(loginUser.getId()); // 设置创建人
		user.setCreationDate(new Date()); // 设置创建时间
		int result = userService.addUser(user);
		if (result > 0) {
			return "redirect:userList.html";
		}
		return "useradd";
	}

	// @RequestMapping("/userview.html")
	// REST 风格
	@RequestMapping("/userview.html/{id}")
	public String userView(@PathVariable Integer id, HttpServletRequest request) {
		User user = userService.getUserById(id);
		request.setAttribute("user", user);
		return "userview";
	}
}
