package com.deepspc.filtergate.modular.warm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepspc.filtergate.modular.warm.entity.RoomHis;

import java.util.List;
import java.util.Map;

/**
 * @Description 房间温湿度历史服务类
 * @Author didoguan
 * @Date 2020/4/9
 **/
public interface IRoomHisService extends IService<RoomHis> {
	/**
	 * 获取房间历史温湿度
	 * @return
	 */
	List<RoomHis> roomTmpHumStatistics(Map<String, Object> param);
}
