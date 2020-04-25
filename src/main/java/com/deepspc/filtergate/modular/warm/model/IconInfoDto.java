package com.deepspc.filtergate.modular.warm.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 返回前端图标信息
 * @Author didoguan
 * @Date 2020/4/25
 **/
@Data
public class IconInfoDto implements Serializable {
	private static final long serialVersionUID = 8200708949776281478L;

	private Long iconId;

	private Integer iconType;

	private String accessPath;

	public IconInfoDto() {

	}
}
