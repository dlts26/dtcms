package com.dt.cms.service.sys;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.cms.domain.sys.Dict;
import com.dt.cms.mapper.sys.DictMapper;
import com.dt.cms.service.BaseService;

/**
 * 字典service
 * 
 * @Author 岳海亮
 * @Mail yhl@feheadline.com
 * @Date 2016年5月4日
 */
@Service("dictService")
@Transactional(readOnly = true)
public class DictService extends BaseService<DictMapper, Dict> {

}
