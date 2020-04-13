package com.deepspc.filtergate.modular.warm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.modular.warm.entity.EquipmentInfo;
import com.deepspc.filtergate.modular.warm.model.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface EquipmentInfoMapper extends BaseMapper<EquipmentInfo> {
    /**
     * 查询设备
     * @param page 分页对象
     * @param queryParam 过滤条件
     * @return
     */
    Page<Map<String, Object>> getEquipments(@Param("page") Page page, @Param("queryParam") QueryParam queryParam);
}
