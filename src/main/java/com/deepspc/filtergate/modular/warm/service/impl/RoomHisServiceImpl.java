package com.deepspc.filtergate.modular.warm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepspc.filtergate.modular.warm.entity.RoomHis;
import com.deepspc.filtergate.modular.warm.mapper.RoomHisMapper;
import com.deepspc.filtergate.modular.warm.service.IRoomHisService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 房间历史温湿度服务实现类
 * @Author didoguan
 * @Date 2020/4/9
 **/
@Service
public class RoomHisServiceImpl extends ServiceImpl<RoomHisMapper, RoomHis> implements IRoomHisService {
	@Override
	public List<RoomHis> roomTmpHumStatistics(String type, String uniqueNo, String startTime, String endTime) {
		return this.baseMapper.roomTmpHumStatistics(type, uniqueNo, startTime, endTime);
	}
}
