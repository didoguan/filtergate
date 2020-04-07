package com.deepspc.filtergate.modular.warm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 用户配置信息
 * @Author didoguan
 * @Date 2020/4/6
 **/
@TableName("wm_customer_conf")
@Data
public class CustomerConf implements Serializable {
	private static final long serialVersionUID = 1399099718311182489L;

	public CustomerConf() {

	}

	@TableId(value = "conf_id", type = IdType.ID_WORKER)
	private Long confId;

	@TableField("customer_id")
	private Long customerId;

	@TableField("message_accept")
	private Integer messageAccept;
}
