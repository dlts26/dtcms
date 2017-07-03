package com.dt.cms.mapper.sys;

import java.util.List;

import com.dt.cms.domain.sys.Log;
import com.dt.cms.mapper.BaseMapper;

public interface LogMapper extends BaseMapper<Log> {
	/**
	 * 批量删除日志：delete from Log log where log.id in (:idList)
	 * @param ids 日志id列表
	 */
	void deleteBatch(List<Integer> idList);
}