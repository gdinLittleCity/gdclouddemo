package com.littlecity.cloud.user.config;

import com.littlecity.cloud.user.dao.SystemConfigDao;
import com.littlecity.cloud.user.entity.SystemConfigEntity;
import com.littlecity.cloud.user.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huangxiaocheng
 * @Date 2019/10/11
 **/
@Component
public class SystemConfig {

    private static Map<String, Object> configCache = new HashMap<>();


    private final SystemConfigService systemConfigService;

    public SystemConfig(SystemConfigService systemConfigService) {
        this.systemConfigService = systemConfigService;
        List<SystemConfigEntity> allConfig = systemConfigService.getAllConfig();

        allConfig.forEach(systemConfigEntity -> {
            configCache.put(systemConfigEntity.getField(), systemConfigEntity.getValue());
        });

    }


    public static Object getConfig(String field) {

        Object configValue = configCache.get(field);

        return configValue;
    }

}
