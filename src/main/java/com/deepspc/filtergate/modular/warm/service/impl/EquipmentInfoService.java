package com.deepspc.filtergate.modular.warm.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepspc.filtergate.core.common.page.LayuiPageFactory;
import com.deepspc.filtergate.core.exception.BizExceptionEnum;
import com.deepspc.filtergate.core.exception.ServiceException;
import com.deepspc.filtergate.modular.warm.entity.EquipmentInfo;
import com.deepspc.filtergate.modular.warm.mapper.EquipmentInfoMapper;
import com.deepspc.filtergate.modular.warm.model.QueryParam;
import com.deepspc.filtergate.modular.warm.service.IEquipmentInfoService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description 设备服务实现类
 * @Author didoguan
 * @Date 2020/4/13
 **/
@Service
public class EquipmentInfoService extends ServiceImpl<EquipmentInfoMapper, EquipmentInfo> implements IEquipmentInfoService {
    @Override
    public Page<Map<String, Object>> getEquipments(QueryParam queryParam) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.getEquipments(page, queryParam);
    }

	@Override
	public EquipmentInfo getEquipmentInfo(Long equipmentId) {
		return this.getById(equipmentId);
	}

	@Override
	public void saveUpdateEquipment(EquipmentInfo equipmentInfo) {
		if (null == equipmentInfo) {
			throw new ServiceException(BizExceptionEnum.SAVE_OBJ_ERROR.getCode(),
					BizExceptionEnum.SAVE_OBJ_ERROR.getMessage());
		}
		this.saveOrUpdate(equipmentInfo);
	}

	@Override
	public void removeEquipment(Long equipmentId) {
		this.removeById(equipmentId);
	}
}
