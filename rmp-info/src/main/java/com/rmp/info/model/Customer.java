package com.rmp.info.model;

import com.rmp.info.base.model.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 客户 基础
 * 
 * t_customer
 *
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends Model {
    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 真实名称
     */
    private String realName;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 手机号
     */
    private Long phone;

    /**
     * 性别（0:女 1:男）
     */
    private Integer sex;

    /**
     * 生日
     */
    private Integer birthday;

    /**
     * 头像
     */
    private String headPic;

    /**
     * 区域
     */
    private String area;

    /**
     * 地址
     */
    private String address;

    /**
     * vip等级
     */
    private Integer vip;

    /**
     * 标签
     */
    private String tag;

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