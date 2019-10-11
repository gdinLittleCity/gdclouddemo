package com.littlecity.cloud.user.service.impl;

import com.littlecity.cloud.user.config.SystemConfig;
import com.littlecity.cloud.user.dao.SystemConfigDao;
import com.littlecity.cloud.user.entity.SystemConfigEntity;
import com.littlecity.cloud.user.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangxiaocheng
 * @Date 2019/10/11
 **/
@Service("systemConfigService")
public class SystemConfigServiceImpl implements SystemConfigService {


    @Autowired
    private SystemConfigDao systemConfigDao;


    @Override
    public Object getConfig(String field) {
        return systemConfigDao.findByField(field).getValue();
    }

    @Override
    public List<SystemConfigEntity> getAllConfig() {
        List<SystemConfigEntity> all = systemConfigDao.findAll();

        return all;
    }
}
