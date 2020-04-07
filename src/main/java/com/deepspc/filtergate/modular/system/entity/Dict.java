package com.deepspc.filtergate.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 字典表
 * </p>
 */
@TableName("sys_dict")
@Data
public class Dict implements Serializable {

    private static final long serialVersionUID = -6567761964380688270L;
    /**
     * 主键id
     */
    @TableId(value = "DICT_ID", type = IdType.ID_WORKER)
    private Long dictId;
    /**
     * 父级字典id
     */
    @TableField("PID")
    private Long pid;
    /**
     * 字典名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 字典的编码
     */
    @TableField("CODE")
    private String code;
	/**
	 * 关联字典
	 */
	@TableField("REF_ID")
    private Long refId;
    /**
     * 字典描述
     */
    @TableField("DESCRIPTION")
    private String description;
    /**
     * 排序
     */
    @TableField("SORT")
    private Integer sort;
    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date updateTime;
    /**
     * 创建人
     */
    @TableField(value = "CREATE_USER", fill = FieldFill.INSERT)
    private Long createUser;
    /**
     * 修改人
     */
    @TableField(value = "UPDATE_USER", fill = FieldFill.UPDATE)
    private Long updateUser;

    @Override
    public String toString() {
        return "Dict{" +
                ", dictId=" + dictId +
                ", pid=" + pid +
                ", name=" + name +
                ", code=" + code +
                ", description=" + description +
                ", sort=" + sort +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUser=" + createUser +
                ", updateUser=" + updateUser +
                "}";
    }
}
