package com.rmp.info.model;

import com.rmp.info.base.model.Model;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 客户 家庭
 * 
 * t_customer_family
 *
 */
@Getter
@Setter
public class CustomerFamily extends Model implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 关系（1:父母、2:配偶、3:子女）
     */
    private Integer relationship;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 生日
     */
    private Integer birthday;

    /**
     * 手机
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