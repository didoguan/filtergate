package com.deepspc.filtergate.modular.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;

import static com.baomidou.mybatisplus.annotation.IdType.ID_WORKER;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 */
@TableName("sys_relation")
@Data
public class Relation implements Serializable {

    private static final long serialVersionUID = 8181280162612276561L;
    /**
     * 主键
     */
    @TableId(value = "RELATION_ID", type = IdType.ID_WORKER)
    private Long relationId;
    /**
     * 菜单id
     */
    @TableField("MENU_ID")
    private Long menuId;
    /**
     * 角色id
     */
    @TableField("ROLE_ID")
    private Long roleId;

    @Override
    public String toString() {
        return "Relation{" +
                ", relationId=" + relationId +
                ", menuId=" + menuId +
                ", roleId=" + roleId +
                "}";
    }
}
