package com.smbms.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smbms.pojo.User;

public interface UserMapper {
	//��½
	User getUserByCP(@Param("userCode") String userCode, @Param("userPassword") String userPassword);
	//��ѯ�����û��б�
	List<User> getUserListByNR(@Param("userName") String userName, @Param("userRole") Integer userRole);
	//��֤userCode
	int countByUserCode(String userCode);
	//���
	int addUser(User user);
	//����id��ѯ�û���Ϣ
	User getUserById(Integer id);
}
