package com.deepspc.filtergate.modular.temperature.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepspc.filtergate.core.common.page.LayuiPageFactory;
import com.deepspc.filtergate.modular.temperature.entity.Temperature;
import com.deepspc.filtergate.modular.temperature.mapper.TemperatureMapper;
import com.deepspc.filtergate.modular.temperature.service.ITemperatureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;

/**
 * @Description 温度服务实现
 * @Author didoguan
 * @Date 2020/3/5
 **/
@Service
@EnableTransactionManagement
public class TemperatureServiceImpl extends ServiceImpl<TemperatureMapper, Temperature> implements ITemperatureService{

	@Override
	public Page<Map<String, Object>> getTemperatureInfos(String customerName) {
		Page page = LayuiPageFactory.defaultPage();
		return this.baseMapper.getTemperatureInfos(page, customerName);
	}
}
