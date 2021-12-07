package org.zzj.dto.request;

import lombok.Data;

import java.util.Date;
import org.zzj.dto.common.PageRequest;

@Data
public class ChannelQueryRequest extends PageRequest {
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
    /*
    @NotNull(message = "不能为空")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    @Length(max = 20, message = "用户名不能超过20个字符")
    @Pattern(regexp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$", message = "不满足邮箱正则表达式")
    @Email(message = "邮箱格式不对")
    @Max(value = 20, message = "最大长度为20")
    @Future(message = "时间必须是将来时间")
    @Past(message = "时间必须是过去时间")
    @Range(min=2, max=100)
    @Size(min = 2, max = 30)
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
