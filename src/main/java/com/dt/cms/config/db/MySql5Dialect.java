package com.dt.cms.config.db;

import com.dt.cms.util.StringUtils;


/**
 * 分页和排序的mysql方言实现
 * @author 岳海亮
 * @date 2017年7月4日
 */
public class MySql5Dialect extends Dialect {

	/**
	 * 得到分页的SQL
	 * 
	 * @param offset
	 *            偏移量
	 * @param limit
	 *            位置
	 * @return 分页SQL
	 */
	public String getSQL(String originSQL, int offset, int limit, String orderBy) {
		if (StringUtils.isNotEmpty(orderBy))
			return originSQL + " order by " + orderBy + " limit " + offset + " ," + limit;
		else
			return originSQL+ " limit " + offset + " ," + limit;

	}

}
