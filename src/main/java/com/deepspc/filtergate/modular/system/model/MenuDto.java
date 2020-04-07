package com.deepspc.filtergate.modular.system.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 菜单表
 * </p>
 */
@Data
public class MenuDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long menuId;
    /**
     * 菜单编号
     */
    @NotEmpty(message="菜单编号不能为空")
    private String code;
    /**
     * 菜单父级id
     */
    @NotNull(message="父级菜单不能为空")
    private Long pid;
    /**
     * 菜单父编号
     */
    private String pcode;
    /**
     * 菜单父级名称
     */
    private String pcodeName;
    /**
     * 菜单名称
     */
    @NotEmpty(message="菜单名称不能为空")
    private String name;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * url地址
     */
    private String url;
    /**
     * 菜单排序号
     */
    private Integer sort;
    /**
     * 菜单层级
     */
    private Integer levels;
    /**
     * 是否是菜单(字典)
     */
    private String menuFlag;
    /**
     * 备注
     */
    private String description;

}
