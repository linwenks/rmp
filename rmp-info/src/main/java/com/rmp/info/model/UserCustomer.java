package com.rmp.info.model;

import com.rmp.info.base.model.Model;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用户 客户 关联
 * 
 * t_user_customer
 *
 */
@Getter
@Setter
public class UserCustomer extends Model implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 排序
     */
    private Integer sort;

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