package com.deepspc.filtergate.modular.warm.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 模式所属房间信息
 * @Author didoguan
 * @Date 2020/4/6
 **/
@Data
public class ModelRoomDto implements Serializable {

	private static final long serialVersionUID = -4249887423642907922L;

	public ModelRoomDto() {

	}

	private Long modelId;

	private Long roomId;

	private String roomName;

	private String serialNo;

	private String uniqueNo;

	private String temperature;

	private String startTime;

	private String endTime;

	private Integer runingStatus;

	private Integer status;

	private Long customerId;

	private String iconPath;

	private String iconOffPath;
}
