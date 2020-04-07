package com.deepspc.filtergate.modular.warm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepspc.filtergate.modular.warm.entity.ModelInfo;
import com.deepspc.filtergate.modular.warm.entity.ModelRoom;
import com.deepspc.filtergate.modular.warm.mapper.ModelInfoMapper;
import com.deepspc.filtergate.modular.warm.mapper.WarmMapper;
import com.deepspc.filtergate.modular.warm.model.ModelData;
import com.deepspc.filtergate.modular.warm.model.ModelRoomDto;
import com.deepspc.filtergate.modular.warm.service.IWarmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Description 供暖服务类
 * @Author didoguan
 * @Date 2020/4/6
 **/
@Service
public class WarmServiceImpl implements IWarmService {
	@Resource
	private ModelInfoMapper modelInfoMapper;
	@Resource
	private WarmMapper warmMapper;

	@Override
	public ModelData getAllModels(Long customerId) {
		ModelData data = new ModelData();
		QueryWrapper<ModelInfo> miWrapper = new QueryWrapper<>();
		miWrapper.eq("customer_id", customerId);
		miWrapper.orderByAsc("model_id");
		List<ModelInfo> modelInfos = modelInfoMapper.selectList(miWrapper);
		data.setModelInfos(modelInfos);
		if (null != modelInfos && !modelInfos.isEmpty()) {
			for (ModelInfo modelInfo : modelInfos) {
				//获取运行中模式的所有房间
				if (modelInfo.getStatus().intValue() == 3) {
					long modelId = modelInfo.getModelId().longValue();
					List<ModelRoomDto> list = warmMapper.getModelRooms(modelId, customerId);
					data.setModelRooms(list);
					break;
				}
			}
		}
		return data;
	}
}
