package com.rmp.info.model;

import com.rmp.info.base.model.Model;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 客户 纪念日
 * 
 * t_customer_memorial_day
 *
 */
@Getter
@Setter
public class CustomerMemorialDay extends Model implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型（1:1次 2:每年 2:每月 3:每周）
     */
    private Integer occurType;

    /**
     * 发生日期
     */
    private Integer occurDate;

    /**
     * 提前类型（1:1天 2:2天 3:3天 4:5天 5:1周 6:2周 7:1月）
     */
    private Integer advanceType;

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