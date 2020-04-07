package com.deepspc.filtergate.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 部门表
 * </p>
 *
 */
@TableName("sys_dept")
@Data
public class Dept implements Serializable {

    private static final long serialVersionUID = -7859176367130669747L;
    /**
     * 主键id
     */
    @TableId(value = "DEPT_ID", type = IdType.ID_WORKER)
    private Long deptId;
    /**
     * 父部门id
     */
    @TableField("PID")
    private Long pid;
    /**
     * 父级ids
     */
    @TableField("PIDS")
    private String pids;
    /**
     * 简称
     */
    @NotEmpty(message="部门简称不能为空")
    @TableField("SIMPLE_NAME")
    private String simpleName;
    /**
     * 全称
     */
    @NotEmpty(message="部门全称不能为空")
    @TableField("FULL_NAME")
    private String fullName;
    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;
    /**
     * 版本（乐观锁保留字段）
     */
    @TableField("VERSION")
    private Integer version;
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
        return "Dept{" +
        ", deptId=" + deptId +
        ", pid=" + pid +
        ", pids=" + pids +
        ", simpleName=" + simpleName +
        ", fullName=" + fullName +
        ", description=" + description +
        ", version=" + version +
        ", sort=" + sort +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", createUser=" + createUser +
        ", updateUser=" + updateUser +
        "}";
    }
}
