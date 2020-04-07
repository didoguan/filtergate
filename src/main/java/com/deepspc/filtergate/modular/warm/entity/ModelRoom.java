package com.deepspc.filtergate.modular.warm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 模式房间关联关系
 * @Author didoguan
 * @Date 2020/4/6
 **/
@TableName("wm_model_room")
@Data
public class ModelRoom implements Serializable {
	private static final long serialVersionUID = 1511322440419468307L;

	public ModelRoom() {

	}

	@TableId(value = "model_room_id", type = IdType.ID_WORKER)
	private Long modelRoomId;

	@TableField("model_id")
	private Long modelId;

	@TableField("room_id")
	private Long roomId;
}
