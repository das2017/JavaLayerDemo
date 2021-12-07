package org.zzj.dao.entity.goods;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Channel {
    /**
     * 自增Id
     */
    private Integer id;

    /**
     * 唯一
     */
    private String code;

    /**
     * 渠道类型 1--下单注册 ，2--客户端，3--添加入住人 ，4--推广活动
     */
    private Integer type;

    /**
     * 渠道名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 父渠道id
     */
    private Integer parentId;

    /**
     * 状态 1--启用，0--停用
     */
    private Integer state;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    /**
     * 修改人
     */
    private Date updatedBy;
}
