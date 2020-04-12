package com.deepspc.filtergate.modular.warm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepspc.filtergate.modular.warm.entity.RoomHis;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoomHisMapper extends BaseMapper<RoomHis> {

	/**
	 * 获取房间历史温湿度
	 * @return
	 */
	List<RoomHis> roomTmpHumStatistics(@Param("param") Map<String, Object> param);
}
