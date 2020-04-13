package com.deepspc.filtergate.modular.equipment.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepspc.filtergate.core.common.page.LayuiPageFactory;
import com.deepspc.filtergate.modular.equipment.entity.Equipment;
import com.deepspc.filtergate.modular.equipment.mapper.EquipmentMapper;
import com.deepspc.filtergate.modular.equipment.model.QueryParam;
import com.deepspc.filtergate.modular.equipment.service.IEquipmentService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description 设备服务实现类
 * @Author didoguan
 * @Date 2020/4/13
 **/
@Service
public class EquipmentService extends ServiceImpl<EquipmentMapper, Equipment> implements IEquipmentService {
    @Override
    public Page<Map<String, Object>> getEquipments(QueryParam queryParam) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.getEquipments(page, queryParam);
    }
}
