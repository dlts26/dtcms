package com.dt.cms.mapper.sys;

import com.dt.cms.domain.sys.User;
import com.dt.cms.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User> {

	/**
	 * 
	 * @param loginName
	 * @return
	 */
	User findByLoginName(String loginName);
}