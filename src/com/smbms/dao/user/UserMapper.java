package com.smbms.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.pojo.User;

public interface UserMapper {
	//登陆
	User getUserByCP(@Param("userCode") String userCode, @Param("userPassword") String userPassword);
	//查询所有用户列表
	List<User> getUserListByNR(@Param("userName") String userName, @Param("userRole") Integer userRole);
	//验证userCode
	int countByUserCode(String userCode);
	//添加
	int addUser(User user);
	//根据id查询用户信息
	User getUserById(Integer id);
}
