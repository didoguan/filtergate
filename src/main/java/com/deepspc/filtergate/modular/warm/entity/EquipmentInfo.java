package com.deepspc.filtergate.modular.warm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 设备信息
 * @Author didoguan
 * @Date 2020/4/6
 **/
@TableName("wm_ equipment_info")
@Data
public class EquipmentInfo implements Serializable {
	private static final long serialVersionUID = 7978096619042012266L;

	public EquipmentInfo() {

	}

	@TableId(value = "equipment_id", type = IdType.ID_WORKER)
	private Long equipmentId;

	@TableField("equipment_name")
	private String equipmentName;

	@TableField("unique_no")
	private String uniqueNo;

	@TableField("serial_no")
	private String serialNo;

	@TableField("equipment_type")
	private Integer equipmentType;

	@TableField("customer_id")
	private Long customerId;

	@TableField("status")
	private Integer status;
}
