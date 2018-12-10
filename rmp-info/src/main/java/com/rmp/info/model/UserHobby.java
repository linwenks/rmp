package com.rmp.info.model;

import com.rmp.info.base.model.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户 兴趣爱好
 * 
 * t_user_hobby
 *
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class UserHobby extends Model {
    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 兴趣（美食、旅游、美容美发、购物、按摩温泉、影视、运动、汽车、家居装饰、宠物、KTV、社交、养生、投资理财、营销、IT互联网、演出、外语学习、体验游戏、网络游戏）
     */
    private String interest;

    /**
     * 饮食（1:川湘菜、2:江浙菜、3:粤菜、4:北方菜、5:日韩料理、6:西餐、7:东南亚菜、8:火锅、9:海鲜、10:素食、11:烧烤、12:甜点）
     */
    private String diet;

    /**
     * 口味（1:甜、2:辣、3:酸、4:苦）
     */
    private String taste;

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