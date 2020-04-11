package com.deepspc.filtergate.modular.warm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepspc.filtergate.modular.warm.entity.RoomHis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomHisMapper extends BaseMapper<RoomHis> {

	/**
	 * 获取房间历史温湿度
	 * @param type D-日 M-月 Y-年
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<RoomHis> roomTmpHumStatistics(@Param("type") String type, @Param("uniqueNo") String uniqueNo, @Param("startTime") String startTime, @Param("endTime") String endTime);
}
