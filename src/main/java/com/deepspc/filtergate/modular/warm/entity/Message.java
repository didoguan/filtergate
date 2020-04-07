package com.deepspc.filtergate.modular.warm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 用户消息
 * @Author didoguan
 * @Date 2020/4/6
 **/
@TableName("wm_message")
@Data
public class Message implements Serializable {
	private static final long serialVersionUID = -6356869947743053697L;

	public Message() {

	}

	@TableId(value = "message_id", type = IdType.ID_WORKER)
	private Long messageId;

	@TableField("msg_type")
	private Integer msgType;

	@TableField("create_time")
	private Date createTime;

	@TableField("msg_content")
	private String msgContent;

	@TableField("customer_id")
	private Long customerId;
}
