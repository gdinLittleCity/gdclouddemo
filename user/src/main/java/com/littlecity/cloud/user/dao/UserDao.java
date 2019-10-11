package com.littlecity.cloud.user.dao;


import com.littlecity.cloud.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserDao extends BaseDAO<UserEntity, Integer>{

   List<UserEntity> findByNameAndPassword(String name, String password);

}
