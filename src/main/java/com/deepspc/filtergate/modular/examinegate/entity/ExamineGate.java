package com.deepspc.filtergate.modular.examinegate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 检测门
 * @Author didoguan
 * @Date 2020/3/2
 **/
@TableName("t_gate")
@Data
public class ExamineGate implements Serializable {

	private static final long serialVersionUID = 9171830938021493953L;

	@TableId(value = "gate_id", type = IdType.ID_WORKER)
	private Long gateId;

	@TableField("customer_id")
	private Long customerId;

	@TableField("customer_name")
	private String customerName;

	/**
	 * 名称
	 */
	@TableField("gate_name")
	private String gateName;

	/**
	 * 唯一编码
	 */
	@TableField("gate_code")
	private String gateCode;

	/**
	 * 安装地址
	 */
	@TableField("set_address")
	private String setAddress;

	/**
	 * 经度
	 */
	@TableField("longitude")
	private Double longitude;

	/**
	 * 纬度
	 */
	@TableField("latitude")
	private Double latitude;

	/**
	 * 安装日期
	 */
	@TableField("set_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date setDate;
}
