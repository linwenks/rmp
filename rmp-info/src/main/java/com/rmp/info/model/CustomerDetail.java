package com.rmp.info.model;

import com.rmp.info.base.model.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 客户 明细
 * 
 * t_customer_detail
 *
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetail extends Model {
    /**
     * ID
     */
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

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
}