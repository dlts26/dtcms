package com.dt.cms.mapper.sys;

import java.util.List;

import com.dt.cms.domain.sys.UserOrg;
import com.dt.cms.mapper.BaseMapper;

public interface UserOrgMapper extends BaseMapper<UserOrg> {

	/**
	 * 查询用户拥有的机构id集合:select ur.orgId from UserOrg ur where ur.userId=?0
	 * @param userId
	 * @return 结果集合
	 */
	List<Integer> findOrgIds(Integer userId);

	
	/**
	 * 删除用户机构:delete UserOrg ur where ur.userId=?0
	 * @param userId
	 * @param orgId
	 */
	void deleteUO(Integer userId);
}