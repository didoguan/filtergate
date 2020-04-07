package com.deepspc.filtergate.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文件信息表
 *
 * </p>
 *
 */
@TableName("sys_file_info")
@Data
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1613395153789139646L;
    /**
     * 主键id
     */
    @TableId(value = "FILE_ID", type = IdType.ID_WORKER_STR)
    private String fileId;
    /**
     * base64编码的文件
     */
    @TableField("FILE_DATA")
    private String fileData;
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
     * 创建用户
     */
    @TableField(value = "CREATE_USER", fill = FieldFill.INSERT)
    private Long createUser;
    /**
     * 修改用户
     */
    @TableField(value = "UPDATE_USER", fill = FieldFill.UPDATE)
    private Long updateUser;

    @Override
    public String toString() {
        return "FileInfo{" +
                ", fileId=" + fileId +
                ", fileData=" + fileData +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUser=" + createUser +
                ", updateUser=" + updateUser +
                "}";
    }
}
