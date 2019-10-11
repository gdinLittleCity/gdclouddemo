package com.littlecity.cloud.user.service;

import com.littlecity.cloud.user.config.SystemConfig;
import com.littlecity.cloud.user.entity.SystemConfigEntity;

import java.util.List;

/**
 * @author huangxiaocheng
 * @Date 2019/10/11
 **/
public interface SystemConfigService {

    Object getConfig(String field);

    List<SystemConfigEntity> getAllConfig();
}
