package com.smbms.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smbms.dao.user.UserMapper;
import com.smbms.pojo.User;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserByCP(String userCode, String userPassword) {
		return userMapper.getUserByCP(userCode, userPassword);
	}

	@Override
	public List<User> getUserListByNR(String userName, Integer userRole) {
		return userMapper.getUserListByNR(userName, userRole);
	}

	@Override
	public int countByUserCode(String userCode) {
		return userMapper.countByUserCode(userCode);
	}

	@Override
	@Transactional
	public int addUser(User user) {
		return userMapper.addUser(user);
	}

	@Override
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}
	
}
