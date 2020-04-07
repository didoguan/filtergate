package com.deepspc.filtergate.modular.temperature.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.modular.temperature.entity.Temperature;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface TemperatureMapper extends BaseMapper<Temperature> {

	/**
	 * 查询所有温度信息
	 * @param page 分页对象
	 * @param customerName 客户名称
	 * @return
	 */
	Page<Map<String, Object>> getTemperatureInfos(@Param("page") Page page, @Param("customerName") String customerName);
}
