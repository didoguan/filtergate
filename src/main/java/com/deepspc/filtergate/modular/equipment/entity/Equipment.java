package com.deepspc.filtergate.modular.equipment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 设备实体
 * @Author didoguan
 * @Date 2020/4/13
 **/
@TableName("wm_equipment")
@Data
public class Equipment implements Serializable {
    private static final long serialVersionUID = -8144129856263754364L;

    @TableId(value = "equipment_id", type = IdType.ID_WORKER)
    private Long equipmentId;

    @TableField("equipment_name")
    private String equipmentName;

    @TableField("unique_no")
    private String uniqueNo;

    @TableField("serial_no")
    private String serialNo;

    /**
     * 1-控制器主机
     * 2-温度执行器
     */
    @TableField("equipment_type")
    private String equipmentType;

    /**
     * 功率
     */
    @TableField("watts")
    private Integer watts;

    /**
     * 0-停用
     * 1-启用
     */
    @TableField("status")
    private Integer status;

    @TableField("create_time")
    private Date createTime;

    @TableField("customer_id")
    private Long customerId;

    @TableField("customer_name")
    private Long customerName;
}
