package com.littlecity.cloud.user.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author huangxiaocheng
 * @Date 2019/10/11
 **/
@Data
@Entity
@Table(name = "sys_config")
public class SystemConfigEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "field")
    private String field;

    @Column(name = "value")
    private String value;


}
