package com.deepspc.filtergate.modular.warm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 模式
 * @Author didoguan
 * @Date 2020/4/6
 **/
@TableName("wm_model_info")
@Data
public class ModelInfo implements Serializable {

	private static final long serialVersionUID = -1820677561545231051L;

	@TableId(value = "model_id", type = IdType.ID_WORKER)
	private Long modelId;

	@TableField("model_name")
	private String modelName;

	@TableField("start_time")
	private String startTime;

	@TableField("end_time")
	private String endTime;

	/**
	 * 0-不可用
	 * 1-可用
	 * 2-删除
	 * 3-运行中
	 */
	@TableField("status")
	private Integer status;

	@TableField("customer_id")
	private Long customerId;

	@TableField("icon_id")
	private Long iconId;

	@TableField("icon_path")
	private String iconPath;

	public ModelInfo() {

	}

}
