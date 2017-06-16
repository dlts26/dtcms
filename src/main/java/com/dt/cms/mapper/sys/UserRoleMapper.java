package com.dt.cms.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dt.cms.domain.sys.UserRole;
import com.dt.cms.mapper.BaseMapper;

public interface UserRoleMapper extends BaseMapper<UserRole> {

	List<Integer> findRoleIds(Integer userId);

	void deleteUR(@Param("userId")Integer userId, @Param("roleId")Integer roleId);
}