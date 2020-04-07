package com.deepspc.filtergate.modular.customer.warpper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.core.common.constant.factory.ConstantFactory;
import com.deepspc.filtergate.core.warpper.BaseControllerWrapper;
import com.deepspc.filtergate.utils.ToolUtil;

import java.util.Map;

/**
 * @Description 封装类
 * @Author didoguan
 * @Date 2020/3/2
 **/
public class CustomerWrapper extends BaseControllerWrapper {

	public CustomerWrapper(Page<Map<String, Object>> page) {
		super(page);
	}

	@Override
	protected void wrapTheMap(Map<String, Object> map) {
		map.put("provinceText", ConstantFactory.me().getDictName(Long.parseLong(map.get("provinceValue").toString())));
		map.put("cityText", ConstantFactory.me().getDictName(Long.parseLong(map.get("cityValue").toString())));
		map.put("districtText", ConstantFactory.me().getDictName(Long.parseLong(map.get("districtValue").toString())));
		String fullAddress = ToolUtil.changeString(map.get("provinceText")) + ToolUtil.changeString(map.get("cityText")) + ToolUtil.changeString(map.get("districtText")) + ToolUtil.changeString(map.get("subAddress"));
		map.put("fullAddress", fullAddress);
	}
}
