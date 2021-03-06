package com.rmp.info.model;

import com.rmp.info.base.model.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户 提醒
 * 
 * t_user_remind
 *
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class UserRemind extends Model {
    /**
     * ID
     */
    private Long id;

    /**
     * 客户ID
     */
    private Long userId;

    /**
     * 分类（1:纪念日 2:客户家庭生日 3:客户生日 4:用户生日）
     */
    private Integer type;

    /**
     * 分类表ID
     */
    private Long typeId;

    /**
     * 提前提醒日期
     */
    private Integer advanceDate;

    /**
     * 提前提醒天数
     */
    private Integer advanceDay;

    /**
     * 提醒日期
     */
    private Integer remindDate;

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