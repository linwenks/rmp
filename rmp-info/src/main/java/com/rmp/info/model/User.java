package com.rmp.info.model;

import com.rmp.info.base.model.Model;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户
 * 
 * t_user
 *
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class User extends Model {
    /**
     * ID
     */
    private Long id;

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 手机号
     */
    private Long phone;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headPic;

    /**
     * 性别（0:女 1:男）
     */
    private Integer sex;

    /**
     * 生日
     */
    private Integer birthday;

    /**
     * 支付密码
     */
    private String payPwd;

    /**
     * 账户余额
     */
    private BigDecimal account;

    /**
     * 上次登录时间
     */
    private Long lastLoginTime;

    /**
     * 区域
     */
    private String area;

    /**
     * 地址
     */
    private String address;

    /**
     * 微信ID
     */
    private String wxId;

    /**
     * 标记
     */
    private String token;

    /**
     * 状态（0:未注册 1:已注册）
     */
    private Integer status;

    /**
     * 微信sessionKey
     */
    private String wxSessionKey;

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