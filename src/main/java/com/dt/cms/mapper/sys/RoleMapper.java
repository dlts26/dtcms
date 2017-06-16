package com.dt.cms.mapper.sys;

import java.util.List;

import com.dt.cms.domain.sys.Role;
import com.dt.cms.mapper.BaseMapper;

public interface RoleMapper extends BaseMapper<Role>{
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
    List<Role> findRolesByUserId(Integer userId);
}