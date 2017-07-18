package com.dt.cms.mapper;

import java.util.List;

import com.dt.cms.entity.Page;

/**
 * 基础mapper
 * @author 岳海亮
 * @date 2017年7月18日
 * @param <T>
 */
public interface BaseMapper<T> {

	/**
	 * 根据page参数，获取分页数据
	 * @param page
	 * @return
	 */
	List<T> selectList(Page<T> page);

	/**
	 * 按主键删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	
	/**
	 * 选择性插入
	 * @param record
	 * @return
	 */
	int insertSelective(T record);

	
	/**
	 * 按主键查询
	 * @param id
	 * @return
	 */
	T selectByPrimaryKey(Integer id);

	
	/**
	 * 选择性更新
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(T record);

}
