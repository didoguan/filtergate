package com.deepspc.filtergate.modular.warm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepspc.filtergate.modular.warm.entity.ModelRoom;

public interface IModelRoomService extends IService<ModelRoom> {

    /**
     * 删除模式下的指定房间
     * @param modelId 模式标识
     * @param roomIds 所有要删除的房间标识
     */
    void deleteModelRooms(Long modelId, String roomIds);
}
