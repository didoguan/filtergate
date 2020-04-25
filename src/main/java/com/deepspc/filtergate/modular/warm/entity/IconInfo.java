package com.deepspc.filtergate.modular.warm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 图标信息
 * @Author didoguan
 * @Date 2020/4/25
 **/
@TableName("wm_icon_info")
@Data
public class IconInfo implements Serializable {
	private static final long serialVersionUID = 5843603316228193711L;

	@TableId(value = "icon_id", type = IdType.ID_WORKER)
	private Long iconId;

	@TableField("old_name")
	private String oldName;

	@TableField("new_name")
	private String newName;

	@TableField("icon_path")
	private String iconPath;

	@TableField("access_path")
	private String accessPath;

	@TableField("icon_type")
	private Integer iconType;

	/**
	 * 图标使用类型 0-用于关闭 1-用于开启
	 */
	@TableField("use_type")
	private Integer useType;

	/**
	 * 关联图标，用于一种图标有两个种状态，比如开和关
	 */
	@TableField("relation_id")
	private Long relationId;

	@TableField("create_time")
	private Date createTime;
}
