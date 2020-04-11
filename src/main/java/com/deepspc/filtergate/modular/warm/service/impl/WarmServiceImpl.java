package com.deepspc.filtergate.modular.warm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepspc.filtergate.modular.warm.entity.*;
import com.deepspc.filtergate.modular.warm.mapper.MessageMapper;
import com.deepspc.filtergate.modular.warm.mapper.ModelInfoMapper;
import com.deepspc.filtergate.modular.warm.mapper.WarmMapper;
import com.deepspc.filtergate.modular.warm.model.ModelData;
import com.deepspc.filtergate.modular.warm.model.ModelRoomDto;
import com.deepspc.filtergate.modular.warm.model.ModelSaveDto;
import com.deepspc.filtergate.modular.warm.service.*;
import com.deepspc.filtergate.utils.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
	private MessageMapper messageMapper;
	@Autowired
	private ICustomerConfService customerConfService;
	@Autowired
	private IRoomHisService roomHisService;
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
        delWrapper.eq("model_id", modelInfo.getModelId());
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

	@Override
	public List<Message> getAllMessage(Long customerId) {
		QueryWrapper<Message> msgWrapper = new QueryWrapper<>();
		msgWrapper.eq("customer_id", customerId);
		msgWrapper.orderByDesc("create_time");
		return messageMapper.selectList(msgWrapper);
	}

	@Override
	public void deleteMessage(Long messageId) {
		messageMapper.deleteById(messageId);
	}

	@Override
	public Message getMsgDetail(Long messageId) {
		return messageMapper.selectById(messageId);
	}

	@Override
	public void saveUpdateCustomerConfig(List<CustomerConf> customerConfs) {
		customerConfService.saveOrUpdateBatch(customerConfs);
	}

	@Override
	public List<CustomerConf> getCustomerConfigs(Long customerId) {
		QueryWrapper<CustomerConf> confWrapper = new QueryWrapper<>();
		confWrapper.eq("customer_id", customerId);
		return customerConfService.list(confWrapper);
	}

	@Override
	public List<RoomHis> getRoomHistory(String type, String uniqueNo) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		String startTime;
		Date date;
		switch (type) {
			case "D" :
				//往后一天
				date = ToolUtil.moveDay(-1);
				sdf.applyPattern("yyyy-MM-dd HH");
				startTime = sdf.format(date);
				break;
			case "M" :
				//往后一月
				date = ToolUtil.moveMonth(-1);
				sdf.applyPattern("yyyy-MM-dd");
				startTime = sdf.format(date);
				break;
			case "Y" ://往后一年
				date = ToolUtil.moveYear(-1);
				sdf.applyPattern("yyyy-MM-dd");
				startTime = sdf.format(date);
				break;
			default : return null;
		}
		Date current = new Date();
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		String endTime = sdf.format(current);
		return roomHisService.roomTmpHumStatistics(type, uniqueNo, startTime, endTime);
	}

	@Override
	public RoomInfo getRoomInfo(String uniqueNo) {
		QueryWrapper<RoomInfo> rmWrapper = new QueryWrapper<>();
		rmWrapper.eq("unique_no", uniqueNo);
		return roomInfoService.getOne(rmWrapper);
	}
}
