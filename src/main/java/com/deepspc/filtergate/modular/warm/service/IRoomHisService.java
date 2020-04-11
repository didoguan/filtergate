package com.deepspc.filtergate.modular.warm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepspc.filtergate.modular.warm.entity.RoomHis;

import java.util.List;

/**
 * @Description 房间温湿度历史服务类
 * @Author didoguan
 * @Date 2020/4/9
 **/
public interface IRoomHisService extends IService<RoomHis> {
	/**
	 * 获取房间历史温湿度
	 * @param type D-日 M-月 Y-年
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	List<RoomHis> roomTmpHumStatistics(String type, String uniqueNo, String startTime, String endTime);
}
