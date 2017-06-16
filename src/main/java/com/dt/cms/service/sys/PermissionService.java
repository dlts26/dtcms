package com.dt.cms.service.sys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.cms.domain.sys.Permission;
import com.dt.cms.mapper.sys.PermissionMapper;
import com.dt.cms.mapper.sys.RolePermissionMapper;
import com.dt.cms.service.BaseService;

/**
 * 权限service
 * 
 * @Author 岳海亮
 * @Date 2016年5月9日
 */
@Service
@Transactional(readOnly = true)
public class PermissionService extends BaseService<PermissionMapper, Permission> {

	@Autowired
	private PermissionMapper permissionMapper;

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Transactional(readOnly = false)
	public void delete(final Integer id) {
		Permission p = permissionMapper.selectByPrimaryKey(id);
		if ("F".equals(p.getType())) {
			List<Permission> children = permissionMapper.findByParentId(id);
			for (Permission cp : children) {
				delete(cp.getId());
			}
		}
		rolePermissionMapper.deleteRP(null, id);
		super.delete(id);
	}

	/**
	 * 添加菜单基础操作
	 * 
	 * @param pid
	 *            菜单id
	 * @param pName
	 *            菜单权限标识名
	 */
	@Transactional(readOnly = false)
	public void addBaseOpe(Integer pid, String pClassName) {
		List<Permission> pList = new ArrayList<Permission>();
		pList.add(new Permission(pid, "添加", "O", "", "sys:" + pClassName + ":add"));
		pList.add(new Permission(pid, "删除", "O", "", "sys:" + pClassName + ":delete"));
		pList.add(new Permission(pid, "修改", "O", "", "sys:" + pClassName + ":update"));
		pList.add(new Permission(pid, "查看", "O", "", "sys:" + pClassName + ":view"));

		// 添加没有的基本操作
		List<Permission> existPList = getMenuOperation(pid);
		for (Permission permission : pList) {
			boolean exist = false;
			for (Permission existPermission : existPList) {
				if (permission.getPermCode().equals(existPermission.getPermCode())) {
					exist = true;
					break;
				} else {
					exist = false;
				}
			}
			if (!exist)
				save(permission);
		}
	}

	/**
	 * 获取角色拥有的权限集合
	 * 
	 * @param userId
	 * @return 结果集合
	 */
	public List<Permission> getPermissionsByUserId(Integer userId) {
		return permissionMapper.findPermissions(userId, null);
	}

	/**
	 * 获取角色拥有的菜单
	 * 
	 * @param userId
	 * @return 菜单集合
	 */
	public List<Permission> getMenus(Integer userId) {
		return permissionMapper.findMenus(userId);
	}

	/**
	 * 获取所有菜单
	 * 
	 * @return 菜单集合
	 */
	public List<Permission> getMenus() {
		return permissionMapper.findMenus(null);
	}

	/**
	 * 获取菜单下的操作
	 * 
	 * @param pid
	 * @return 操作集合
	 */
	public List<Permission> getMenuOperation(Integer pid) {
		return permissionMapper.findMenuOperation(pid);
	}

	public List<Permission> getPermissionsByRoleName(String roleName) {
		return permissionMapper.findPermissionsByRoleName(roleName);
	}

}
