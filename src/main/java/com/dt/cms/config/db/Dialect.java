package com.dt.cms.config.db;

/**
 * 类似hibernate的Dialect,但只精简出分页部分
 * 
 * @Author 岳海亮
 * @Date 2016年5月6日
 */
public abstract class Dialect {

	public abstract String getSQL(String sql, int offset, int limit,String orderBy);

}
