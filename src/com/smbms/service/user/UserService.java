package com.smbms.service.user;

import java.util.List;

import com.smbms.pojo.User;

public interface UserService {
	User getUserByCP(String userCode,String userPassword);
	List<User> getUserListByNR(String userName,Integer userRole);
	int countByUserCode(String userCode);
	int addUser(User user);
	User getUserById(Integer id);
}
