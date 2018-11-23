package com.rmp.info.model;

import com.rmp.info.base.model.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 客户 关系
 * 
 * t_customer_relation
 *
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRelation extends Model {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private Long customerId;

    /**
     * 关系（0:其他 1:家人 2:亲戚 3:朋友 4:同学 5:同事 6:客户 7:熟人 8:陌生人）
     */
    private Integer relationship;

    /**
     * 亲密（0:不详 1:很亲近 2:一般亲近 3:正常交往 4:点头之交）
     */
    private Integer intimacy;

    /**
     * 重要（0:不重要 1:重要 2:非常重要（vip））
     */
    private Integer importance;

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