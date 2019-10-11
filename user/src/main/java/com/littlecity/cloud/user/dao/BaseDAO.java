package com.littlecity.cloud.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author huangxiaocheng
 * @Date 2019/10/11
 **/
@NoRepositoryBean
public interface BaseDAO<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
