package com.rmp.info.model;

import java.io.Serializable;

import com.rmp.info.base.model.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 
 * t_sys_code
 *
 */
@AllArgsConstructor
@Getter
@Setter
public class SysCode extends Model implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String key;

    /**
     * 
     */
    private String value;

    /**
     * 
     */
    private Long pid;

    /**
     * 序号
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除（0:未删除 1:已删除）
     */
    private Integer isDelete;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;

    private static final long serialVersionUID = 1L;
}