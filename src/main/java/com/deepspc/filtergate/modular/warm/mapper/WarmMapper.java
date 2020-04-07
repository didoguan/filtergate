package com.deepspc.filtergate.modular.warm.mapper;

import com.deepspc.filtergate.modular.warm.model.ModelRoomDto;

import java.util.List;

public interface WarmMapper {

	/**
	 * 获取指定模式下的房间
	 * @param modelId 模式标识
	 * @param customerId 客户标识
	 * @return
	 */
	List<ModelRoomDto> getModelRooms(Long modelId, Long customerId);
}
