package com.deepspc.filtergate.modular.temperature.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 温度实体
 * @Author didoguan
 * @Date 2020/3/2
 **/
@TableName("t_temperature")
@Data
public class Temperature implements Serializable {

	private static final long serialVersionUID = -2220201919930615489L;

	@TableId(value = "temperature_id", type = IdType.ID_WORKER)
	private Long temperatureId;

	@TableField("gate_id")
	private Long gateId;

	@TableField("temp_number")
	private Float tempNumber;

	@TableField("create_time")
	private Date createTime;
}
