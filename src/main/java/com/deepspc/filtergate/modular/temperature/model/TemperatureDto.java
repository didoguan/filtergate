package com.deepspc.filtergate.modular.temperature.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 温度数据对象
 * @Author didoguan
 * @Date 2020/3/5
 **/
@Data
public class TemperatureDto implements Serializable {

	private static final long serialVersionUID = 425221629594259532L;

	private Long temperatureId;

	private String customerName;

	private String gateName;

	private String gateCode;

	private Float tempNumber;

	private String createTime;
}
