package com.deepspc.filtergate.modular.warm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 用户消息
 * @Author didoguan
 * @Date 2020/4/6
 **/
@TableName("wm_message")
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

	@TableField(exist = false)
	private String createTimeStr;

	@TableField("msg_content")
	private String msgContent;

	@TableField("customer_id")
	private Long customerId;

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeStr() {
		if (null != this.createTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.createTimeStr = sdf.format(this.createTime);
			sdf = null;
		}
		return createTimeStr;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
}
