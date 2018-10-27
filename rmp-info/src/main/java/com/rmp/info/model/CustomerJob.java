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
     * 行业（计算机硬软件、互联网/电子商务/网游、IT管理、通信、电子/电器/半导体、 财务/审计/税务、金融/投资、银行/保险、工程/机械、能源/原材料、汽车及零配件制造、汽车销售服务、服装/纺织、轻工产品制造、食品生产、贸易、物流/仓储、生物/制药、化工、医院/医疗/护理、广告媒体、市场/营销、影视、编辑出版、艺术/设计、建筑与装潢、房地产开发、房地产销售与中介、物业、人力资源、咨询/顾问、律师/法务、教师/培训、科研、餐饮服务、酒店旅游、美容保健、百货零售、交通运输、家政/生活服务、政府/公务员、翻译、农林牧渔、印刷包装、运动健身、休闲娱乐、其他 ）
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