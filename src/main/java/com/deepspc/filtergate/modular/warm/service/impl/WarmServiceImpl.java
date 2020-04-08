package com.deepspc.filtergate.modular.warm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepspc.filtergate.modular.warm.entity.ModelInfo;
import com.deepspc.filtergate.modular.warm.entity.ModelRoom;
import com.deepspc.filtergate.modular.warm.entity.RoomInfo;
import com.deepspc.filtergate.modular.warm.mapper.ModelInfoMapper;
import com.deepspc.filtergate.modular.warm.mapper.WarmMapper;
import com.deepspc.filtergate.modular.warm.model.ModelData;
import com.deepspc.filtergate.modular.warm.model.ModelRoomDto;
import com.deepspc.filtergate.modular.warm.model.ModelSaveDto;
import com.deepspc.filtergate.modular.warm.service.IModelInfoService;
import com.deepspc.filtergate.modular.warm.service.IRoomInfoService;
import com.deepspc.filtergate.modular.warm.service.IWarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private IModelInfoService modelInfoService;
    @Autowired
    private IRoomInfoService roomInfoService;
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

	@Transactional
    @Override
    public void saveUpdateModelRoom(ModelSaveDto dto, Long customerId) {
        ModelInfo modelInfo = dto.getModelInfo();
        List<RoomInfo> modelRooms = dto.getModelRooms();
        if (null == modelRooms || modelRooms.isEmpty()) {
            return;
        }
        modelInfoService.saveOrUpdate(modelInfo);
        for (RoomInfo roomInfo : modelRooms) {
            roomInfo.setCustomerId(customerId);
            roomInfo.setModelId(modelInfo.getModelId());
        }
        //先删除当前模式下所有房间
        QueryWrapper<RoomInfo> delWrapper = new QueryWrapper<>();
        delWrapper.eq("customer_id", customerId);
        roomInfoService.remove(delWrapper);
        roomInfoService.saveBatch(modelRooms);
    }

    @Override
    @Transactional
    public void deleteModel(Long modelId) {
        modelInfoService.removeById(modelId);
    }

    @Override
    @Transactional
    public void addModelsRoom(List<RoomInfo> roomInfos) {
        roomInfoService.saveBatch(roomInfos);
    }

    @Override
    @Transactional
    public void delteModelsRooms(String ids) {
        String[] delId = ids.split(",");
        for (String id : delId) {
            roomInfoService.removeById(Long.valueOf(id));
        }
    }
}
