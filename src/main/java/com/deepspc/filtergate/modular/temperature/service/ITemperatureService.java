package com.deepspc.filtergate.modular.temperature.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

public interface ITemperatureService {

	/**
	 * 查询所有温度信息
	 * @param customerName 客户名称
	 * @return
	 */
	Page<Map<String, Object>> getTemperatureInfos(String customerName);
}
