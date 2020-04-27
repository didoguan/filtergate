package com.deepspc.filtergate.modular.warm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepspc.filtergate.core.exception.ServiceException;
import com.deepspc.filtergate.modular.warm.entity.*;
import com.deepspc.filtergate.modular.warm.mapper.*;
import com.deepspc.filtergate.modular.warm.model.IconInfoDto;
import com.deepspc.filtergate.modular.warm.model.ModelSaveDto;
import com.deepspc.filtergate.modular.warm.service.*;
import com.deepspc.filtergate.utils.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

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
	@Resource
	private IconInfoMapper iconInfoMapper;
	@Autowired
	private IModelRoomService modelRoomService;
	@Autowired
	private ICustomerConfService customerConfService;
	@Autowired
	private IRoomHisService roomHisService;
	@Resource
	private WarmMapper warmMapper;

	@Override
	public List<ModelInfo> getAllModels(Long customerId) {
		QueryWrapper<ModelInfo> miWrapper = new QueryWrapper<>();
		miWrapper.eq("customer_id", customerId);
		miWrapper.orderByAsc("model_id");
		List<ModelInfo> modelInfos = modelInfoMapper.selectList(miWrapper);
		if (null != modelInfos && !modelInfos.isEmpty()) {
			for (ModelInfo modelInfo : modelInfos) {
                long modelId = modelInfo.getModelId();
                modelInfo.setRooms(warmMapper.getModelRooms(modelId, customerId));
			}
		}
		return modelInfos;
	}

    @Override
    public List<RoomInfo> getAllRooms(Long customerId) {
	    QueryWrapper<RoomInfo> queryWrapper = new QueryWrapper<>();
	    queryWrapper.orderByAsc("room_id");
        return roomInfoService.list(queryWrapper);
    }

    @Transactional
    @Override
    public void saveUpdateModelRoom(ModelSaveDto dto, Long customerId) {
        ModelInfo modelInfo = dto.getModelInfo();
        int status = modelInfo.getStatus().intValue();
        Map<String, Object> params = new HashMap<>(16);
		params.put("relationId", modelInfo.getIconId());
        if (1 == status) {
			params.put("useType", 1);
		} else if (0 == status) {
			params.put("useType", 0);
		}
		List<IconInfoDto> dtos = iconInfoMapper.getIconInfos(params);
		if (null != dtos && !dtos.isEmpty()) {
			IconInfoDto iconInfoDto = dtos.get(0);
			modelInfo.setIconId(iconInfoDto.getIconId());
			modelInfo.setIconPath(iconInfoDto.getAccessPath());
		}
        modelInfoService.saveOrUpdate(modelInfo);

        List<Long> modelRooms = dto.getModelRooms();
		if (null != modelRooms && !modelRooms.isEmpty()) {
            //先删除当前模式下所有房间
            QueryWrapper<ModelRoom> delWrapper = new QueryWrapper<>();
            delWrapper.eq("model_id", modelInfo.getModelId());
            modelRoomService.remove(delWrapper);

            List<ModelRoom> mrs = new ArrayList<>(16);
            for (Long roomId : modelRooms) {
                ModelRoom mr = new ModelRoom();
                mr.setModelId(modelInfo.getModelId());
                mr.setRoomId(roomId);
                mrs.add(mr);
            }
            //保存模式和房间关系
            modelRoomService.saveBatch(mrs);
        }
    }

    @Override
    @Transactional
    public void deleteModel(Long modelId) {
        modelInfoService.removeById(modelId);
    }

    @Override
    @Transactional
    public Long addRoom(RoomInfo roomInfo) {
        QueryWrapper<RoomInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unique_no", roomInfo.getUniqueNo());
        queryWrapper.eq("serial_no", roomInfo.getSerialNo());
        RoomInfo room = roomInfoService.getOne(queryWrapper);
        if (null != room) {
            throw new ServiceException(401, "房间" + room.getRoomName()+" 已经存在");
        }
        roomInfoService.save(roomInfo);
        return roomInfo.getRoomId();
    }

    @Override
    @Transactional
    public void deleteModelRooms(Long modelId, String roomIds) {
	    String[] roomId = roomIds.split(",");
	    String ids = "";
        for (int i = 0; i < roomId.length; i++) {
            if (i > 0) {
                ids += ",";
            }
            ids += "'" + roomId[i] + "'";
        }
        modelRoomService.deleteModelRooms(modelId, ids);
    }

    @Override
    @Transactional
    public void deleteRooms(String ids) {
        String[] delId = ids.split(",");
        for (String id : delId) {
            roomInfoService.removeById(Long.valueOf(id));
        }
    }

	@Override
	@Transactional
    public void updateRoom(RoomInfo roomInfo) {
		Map<String, Object> params = new HashMap<>(16);
		params.put("relationId", roomInfo.getIconId());
		int status = roomInfo.getStatus().intValue();
		if (1 == status) {
			params.put("useType", 1);
		} else if (0 == status) {
			params.put("useType", 0);
		}
		List<IconInfoDto> dtos = iconInfoMapper.getIconInfos(params);
		if (null != dtos && !dtos.isEmpty()) {
			IconInfoDto dto = dtos.get(0);
			roomInfo.setIconId(dto.getIconId());
			roomInfo.setIconPath(dto.getAccessPath());
		}
		QueryWrapper<RoomInfo> updWrapper = new QueryWrapper<>();
		updWrapper.eq("unique_no", roomInfo.getUniqueNo());
		roomInfoService.update(roomInfo, updWrapper);
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startTime;
		Date date;
		switch (type) {
			case "D" :
				//往后一天
				date = ToolUtil.moveDay(-1);
				startTime = sdf.format(date);
				break;
			case "M" :
				//往后一月
				date = ToolUtil.moveMonth(-1);
				startTime = sdf.format(date);
				break;
			case "Y" ://往后一年
				date = ToolUtil.moveYear(-1);
				startTime = sdf.format(date);
				break;
			default : return null;
		}
		Date current = new Date();
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		String endTime = sdf.format(current);

		Map<String, Object> param = new HashMap<>(4);
		param.put("type", type);
		param.put("uniqueNo", uniqueNo);
		param.put("startTime", startTime + " 00:00:00");
		param.put("endTime", endTime);

		return roomHisService.roomTmpHumStatistics(param);
	}

	@Override
	public RoomInfo getRoomInfo(String uniqueNo) {
		QueryWrapper<RoomInfo> rmWrapper = new QueryWrapper<>();
		rmWrapper.eq("unique_no", uniqueNo);
		return roomInfoService.getOne(rmWrapper);
	}

	@Override
	public List<IconInfoDto> getAllAccessIcon(Integer iconType) {
		return iconInfoMapper.getAllAccessIcon(iconType);
	}
}
