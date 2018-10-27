package com.rmp.info.model;

import com.rmp.info.base.model.Model;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 客户 工作
 * 
 * t_customer_job
 *
 */
@Getter
@Setter
public class CustomerJob extends Model implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 行业（计算机硬软件、互联网/电子商务/网游、IT管理、通信、电子/电器/半导体、
     */
    private Integer industry;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 职位（工薪族、个体户、企业主、学生、公务员、自由职业、无业）
     */
    private Integer position;

    /**
     * 工作电话
     */
    private Long phone;

    /**
     * 区域ID
     */
    private Long areaId;

    /**
     * 地址
     */
    private String address;

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