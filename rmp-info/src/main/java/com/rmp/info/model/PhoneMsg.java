package com.rmp.info.model;

import java.io.Serializable;

import com.rmp.info.base.model.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 手机短信
 * 
 * t_phone_msg
 *
 */
@AllArgsConstructor
@Getter
@Setter
public class PhoneMsg extends Model implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 手机号
     */
    private Long phone;

    /**
     * 验证码
     */
    private String code;

    /**
     * 类型（1：注册 2：找回密码）
     */
    private Integer type;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态（0:未使用 1:已使用）
     */
    private Integer status;

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