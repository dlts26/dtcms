package com.dt.cms.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.cms.domain.sys.Log;
import com.dt.cms.mapper.sys.LogMapper;
import com.dt.cms.service.BaseService;

/**
 * 日志service
 * 
 * @Author 岳海亮
 * @Mail yhl@feheadline.com
 * @Date 2016年5月9日
 */
@Service
@Transactional(readOnly = true)
public class LogService extends BaseService<LogMapper,Log> {

	@Autowired
	private LogMapper logMapper;

	/**
	 * 批量删除日志
	 * 
	 * @param idList
	 */
	@Transactional(readOnly = false)
	public void deleteLog(List<Integer> idList) {
		logMapper.deleteBatch(idList);
	}


}
