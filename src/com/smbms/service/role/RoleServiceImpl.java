package com.smbms.service.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smbms.dao.role.RoleMapper;
import com.smbms.pojo.Role;
@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleMapper roleMapper;
	@Override
	public List<Role> getRoleList() {
		return roleMapper.getRoleList();
	}

}
