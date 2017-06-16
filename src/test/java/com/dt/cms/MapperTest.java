package com.dt.cms;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dt.cms.domain.OperateType;
import com.dt.cms.domain.sys.Permission;
import com.dt.cms.mapper.sys.PermissionMapper;
import com.dt.cms.mapper.sys.RolePermissionMapper;
import com.dt.cms.util.RedisUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

	@Test
	public void testOperateType() {
		System.out.println(OperateType.ADD);
		System.out.println(OperateType.DELETE);
		System.out.println(OperateType.UPDATE);
	}

	@Autowired
	PermissionMapper permissionMapper;

	@Test
	public void testPermissionMapper() {
		List<Permission> list = permissionMapper.findPermissions(1, null);
		System.out.println(list.size());
	}

	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void testRemove() {
		String date = "2017-06-08";
		redisUtil.remove("infoCodes_" + date);
		redisUtil.remove("infoCodes_index_" + date);
	}

	@Autowired
	RolePermissionMapper rolePermissionMapper;

	@Test
	public void testDeleteRP() {
		rolePermissionMapper.deleteRP(null, 88);
	}
}
