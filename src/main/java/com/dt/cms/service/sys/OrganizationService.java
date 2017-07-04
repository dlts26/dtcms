package com.dt.cms.service.sys;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.cms.domain.sys.Organization;
import com.dt.cms.mapper.sys.OrganizationMapper;
import com.dt.cms.service.BaseService;

@Service
@Transactional(readOnly = true)
public class OrganizationService extends BaseService<OrganizationMapper, Organization> {

}
