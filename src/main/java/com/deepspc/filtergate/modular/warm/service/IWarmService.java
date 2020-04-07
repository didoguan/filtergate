package com.deepspc.filtergate.modular.warm.service;

import com.deepspc.filtergate.modular.warm.model.ModelData;

public interface IWarmService {

	/**
	 * 获取所有模式
	 * @param customerId
	 * @return
	 */
	ModelData getAllModels(Long customerId);
}
