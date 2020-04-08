package com.deepspc.filtergate.modular.warm.service;

import com.deepspc.filtergate.modular.warm.entity.RoomInfo;
import com.deepspc.filtergate.modular.warm.model.ModelData;
import com.deepspc.filtergate.modular.warm.model.ModelSaveDto;

import java.util.List;

public interface IWarmService {

	/**
	 * 获取所有模式
	 * @param customerId
	 * @return
	 */
	ModelData getAllModels(Long customerId);

    /**
     * 新增或更新模式及房间信息
     * @param dto
     */
	void saveUpdateModelRoom(ModelSaveDto dto, Long customerId);

    /**
     * 删除模式
     * @param modelId
     */
	void deleteModel(Long modelId);

    /**
     * 添加房间到指定模式
     */
	void addModelsRoom(List<RoomInfo> roomInfos);

    /**
     * 删除模式下的房间
     * @param ids
     */
	void delteModelsRooms(String ids);
}
