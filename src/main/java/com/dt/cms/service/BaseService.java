package com.dt.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dt.cms.domain.BaseDomain;
import com.dt.cms.entity.Page;
import com.dt.cms.mapper.BaseMapper;
import com.dt.cms.persistant.PropertyFilter;
import com.dt.cms.persistant.PropertyFilter.MatchType;

/**
 * 基础service 所有service继承该类 提供基础的实体操作方法
 * 
 * @param <PK>
 * @Author 岳海亮
 * @Mail yhl@feheadline.com
 * @Date 2016年5月10日
 */
public abstract class BaseService<M extends BaseMapper<T>, T extends BaseDomain<T>> {

	@Autowired
	M baseMapper;

	@Transactional(readOnly = true)
	public T get(final Integer id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly = false)
	public void save(final T entity) {
		baseMapper.insertSelective(entity);
	}

	@Transactional(readOnly = false)
	public void update(final T entity) {
		baseMapper.updateByPrimaryKeySelective(entity);
	}

	@Transactional(readOnly = false)
	public void delete(final T entity) {

	}

	@Transactional(readOnly = false)
	public void delete(final Integer id) {
		baseMapper.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	public List<T> getAll() {
		return baseMapper.selectList(null);
	}

	@Transactional(readOnly = true)
	public List<T> getAll(Boolean isCache) {
		return null;
	}

	@Transactional(readOnly = true)
	public List<T> search(final List<PropertyFilter> filters) {
		return baseMapper.selectList(null);
	}

	@Transactional(readOnly = true)
	public Page<T> search(final Page<T> page, final List<PropertyFilter> filters) {
		String toWhere = toWhere(filters);
		page.setWhere(toWhere);
		List<T> list = baseMapper.selectList(page);
		page.setResult(list);
		return page;
	}

	/**
	 * 把传递的属性过滤条件转为SQL的标准where条件
	 * 
	 * @param filters
	 * @return
	 */
	protected String toWhere(List<PropertyFilter> filters) {
		StringBuffer sb = new StringBuffer();
		if (filters != null && filters.size() > 0)
			for (PropertyFilter filter : filters) {
				if (filter.getMatchType() == MatchType.LIKE)
					sb.append(filter.getPropertyName() + " " + filter.getMatchType() + " '%" + filter.getMatchValue()
							+ "%' and ");
				else
					sb.append(filter.getPropertyName() + " " + filter.getMatchType() + " '" + filter.getMatchValue()
							+ "' and ");
			}
		sb.append("1=1");
		return sb.toString();
	}
}
