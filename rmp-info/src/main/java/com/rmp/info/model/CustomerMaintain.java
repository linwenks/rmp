package com.rmp.info.model;

import com.rmp.info.base.model.Model;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 客户 维护设置
 * 
 * t_customer_maintain
 *
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerMaintain extends Model {
    /**
     * ID
     */
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 维护（0:手动 1:自动）
     */
    private Integer maintain;

    /**
     * 频率
     */
    private Integer frequency;

    /**
     * 次数
     */
    private Integer count;

    /**
     * 预算
     */
    private BigDecimal budget;

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