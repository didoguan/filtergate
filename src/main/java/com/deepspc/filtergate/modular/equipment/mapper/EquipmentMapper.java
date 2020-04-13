package com.deepspc.filtergate.modular.equipment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.modular.equipment.entity.Equipment;
import com.deepspc.filtergate.modular.equipment.model.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface EquipmentMapper extends BaseMapper<Equipment> {

    /**
     * 查询设备
     * @param page 分页对象
     * @param queryParam 过滤条件
     * @return
     */
    Page<Map<String, Object>> getEquipments(@Param("page") Page page, @Param("queryParam") QueryParam queryParam);
}
