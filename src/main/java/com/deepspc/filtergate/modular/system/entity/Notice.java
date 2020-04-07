package com.deepspc.filtergate.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 通知表
 * </p>
 *
 */
@TableName("sys_notice")
@Data
public class Notice implements Serializable {

    private static final long serialVersionUID = -905671463857122746L;
    /**
     * 主键
     */
    @TableId(value = "NOTICE_ID", type = IdType.ID_WORKER)
    private Long noticeId;
    /**
     * 标题
     */
    @NotEmpty(message="标题不能为空")
    @TableField("TITLE")
    private String title;
    /**
     * 内容
     */
    @NotEmpty(message="内容不能为空")
    @TableField("CONTENT")
    private String content;
    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 创建人
     */
    @TableField(value = "CREATE_USER", fill = FieldFill.INSERT)
    private Long createUser;
    /**
     * 修改时间
     */
    @TableField(value = "UPDATE_TIME", fill = FieldFill.UPDATE)
    private Date updateTime;
    /**
     * 修改人
     */
    @TableField(value = "UPDATE_USER", fill = FieldFill.UPDATE)
    private Long updateUser;

    @Override
    public String toString() {
        return "Notice{" +
                ", noticeId=" + noticeId +
                ", title=" + title +
                ", content=" + content +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                "}";
    }
}
