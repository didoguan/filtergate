package com.deepspc.filtergate.modular.warm.model;

import com.deepspc.filtergate.modular.warm.entity.ModelInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 模式数据对象
 * @Author didoguan
 * @Date 2020/4/6
 **/
@Data
public class ModelData implements Serializable {
	private static final long serialVersionUID = -6029666242670967037L;

	private List<ModelInfo> modelInfos;
	//当前运行模式下的房间
	private List<ModelRoomDto> modelRooms;

	public ModelData() {

	}
}
