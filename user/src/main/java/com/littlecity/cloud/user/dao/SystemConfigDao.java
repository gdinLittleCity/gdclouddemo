package com.littlecity.cloud.user.dao;

import com.littlecity.cloud.user.entity.SystemConfigEntity;

/**
 * @author huangxiaocheng
 * @Date 2019/10/11
 **/
public interface SystemConfigDao extends BaseDAO<SystemConfigEntity, Integer> {

    SystemConfigEntity findByField(String field);
}
