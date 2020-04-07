package com.deepspc.filtergate.modular.customer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 客户信息
 * @Author didoguan
 * @Date 2020/3/2
 **/
@TableName("t_customer")
@Data
public class Customer implements Serializable {

	private static final long serialVersionUID = 5040421770358739117L;

	@TableId(value = "customer_id", type = IdType.ID_WORKER)
	private Long customerId;

	@TableField("customer_name")
	private String customerName;

	@TableField("province_value")
	private Long provinceValue;

	@TableField("city_value")
	private Long cityValue;

	@TableField("district_value")
	private Long districtValue;

	@TableField("sub_address")
	private String subAddress;

	@TableField("contact_person")
	private String contactPerson;

	@TableField("contact_number")
	private String contactNumber;

	@TableField("create_time")
	private Date createTime;

	@TableField(exist = false)
	private String fullAddress;
}
