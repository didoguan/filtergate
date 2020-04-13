package com.deepspc.filtergate.modular.warm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deepspc.filtergate.modular.warm.entity.EquipmentInfo;
import com.deepspc.filtergate.modular.warm.model.QueryParam;

import java.util.Map;

public interface IEquipmentInfoService extends IService<EquipmentInfo> {

    /**
     * 查询设备
     * @param queryParam 过滤条件
     * @return
     */
    Page<Map<String, Object>> getEquipments(QueryParam queryParam);
}
