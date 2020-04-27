package com.deepspc.filtergate.modular.warm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepspc.filtergate.modular.warm.entity.ModelRoom;
import org.apache.ibatis.annotations.Param;

public interface ModelRoomMapper extends BaseMapper<ModelRoom> {

    /**
     * 删除模式下的指定房间
     * @param modelId 模式标识
     * @param roomIds 所有要删除的房间标识
     */
    void deleteModelRooms(@Param("modelId") Long modelId, @Param("roomIds") String roomIds);
}
