package com.deepspc.filtergate.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 菜单表
 * </p>
 *
 */
@TableName("sys_menu")
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 7203901561717850498L;
    /**
     * 主键id
     */
    @TableId(value = "MENU_ID", type = IdType.ID_WORKER)
    private Long menuId;
    /**
     * 菜单编号
     */
    @TableField("CODE")
    private String code;
    /**
     * 菜单父编号
     */
    @TableField("PCODE")
    private String pcode;
    /**
     * 当前菜单的所有父菜单编号
     */
    @TableField("PCODES")
    private String pcodes;
    /**
     * 菜单名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 菜单图标
     */
    @TableField("ICON")
    private String icon;
    /**
     * url地址
     */
    @TableField("URL")
    private String url;
    /**
     * 菜单排序号
     */
    @TableField("SORT")
    private Integer sort;
    /**
     * 菜单层级
     */
    @TableField("LEVELS")
    private Integer levels;
    /**
     * 是否是菜单(字典)
     */
    @TableField("MENU_FLAG")
    private String menuFlag;
    /**
     * 备注
     */
    @TableField("DESCRIPTION")
    private String description;
    /**
     * 菜单状态(字典)
     */
    @TableField("STATUS")
    private String status;
    /**
     * 是否打开新页面的标识(字典)
     */
    @TableField("NEW_PAGE_FLAG")
    private String newPageFlag;
    /**
     * 是否打开(字典)
     */
    @TableField("OPEN_FLAG")
    private String openFlag;
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
        return "Menu{" +
                ", menuId=" + menuId +
                ", code=" + code +
                ", pcode=" + pcode +
                ", pcodes=" + pcodes +
                ", name=" + name +
                ", icon=" + icon +
                ", url=" + url +
                ", sort=" + sort +
                ", levels=" + levels +
                ", menuFlag=" + menuFlag +
                ", description=" + description +
                ", status=" + status +
                ", newPageFlag=" + newPageFlag +
                ", openFlag=" + openFlag +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUser=" + createUser +
                ", updateUser=" + updateUser +
                "}";
    }
}
