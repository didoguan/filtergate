package com.deepspc.filtergate.modular.equipment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deepspc.filtergate.modular.equipment.entity.Equipment;
import com.deepspc.filtergate.modular.equipment.model.QueryParam;

import java.util.Map;

public interface IEquipmentService extends IService<Equipment> {

    /**
     * 查询设备
     * @param queryParam 过滤条件
     * @return
     */
    Page<Map<String, Object>> getEquipments(QueryParam queryParam);
}
