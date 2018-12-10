package com.rmp.info.model;

import com.rmp.info.base.model.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 客户 可能问题
 * 
 * t_customer_problem
 *
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class CustomerProblem extends Model {
    /**
     * ID
     */
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 健康问题（1:心脏病 2:动脉硬化 3:高血压 4:高血脂 5:肠胃病 6:糖尿病 7:关节炎 8:肥胖症 9:胆结石 10:肾病 11:精神问题 12:脸部痘痕 13:五官瑕疵）
     */
    private String health;

    /**
     * 生活问题（1:资金缺乏 2:寻找工作 3:事业发展 4:感情困扰 5:子女学习 6:法律问题 7:税务）
     */
    private String life;

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