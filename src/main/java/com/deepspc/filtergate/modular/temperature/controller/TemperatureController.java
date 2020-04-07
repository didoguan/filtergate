package com.deepspc.filtergate.modular.temperature.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.core.common.page.LayuiPageFactory;
import com.deepspc.filtergate.modular.temperature.service.ITemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Description 温度管理控制器
 * @Author didoguan
 * @Date 2020/3/2
 **/
@Controller
@RequestMapping("/temperature")
public class TemperatureController {

	private String PREFIX = "/modular/temperature/";

	@Autowired
	private ITemperatureService temperatureService;

	@RequestMapping("")
	public String list() {
		return PREFIX + "temperature.html";
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(value = "customerName", required = false) String customerName) {
		Page<Map<String, Object>> list = temperatureService.getTemperatureInfos(customerName);
		return LayuiPageFactory.createPageInfo(list);
	}
}
