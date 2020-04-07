package com.deepspc.filtergate.modular.warm.controller;

import com.deepspc.filtergate.core.reqres.response.ResponseData;
import com.deepspc.filtergate.modular.controller.BaseController;
import com.deepspc.filtergate.modular.warm.model.ModelData;
import com.deepspc.filtergate.modular.warm.service.IWarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description 供暖监控控制器
 * @Author didoguan
 * @Date 2020/4/6
 **/
@Controller
@RequestMapping("/rpc/warm")
public class WarmController extends BaseController {

	@Autowired
	private IWarmService warmService;

	/**
	 * 获取所有模式
	 * @return
	 */
	@RequestMapping(value = "/getAllModels")
	@ResponseBody
	public Object getAllModels() {
		ResponseData resp = new ResponseData(true, 200, null, null);
		Object id = this.getHttpServletRequest().getAttribute("tokenUserId");
		if (null != id) {
			String tokenUserId = id.toString();
			ModelData modelData = warmService.getAllModels(Long.parseLong(tokenUserId));
			resp.setData(modelData);
		}
		return resp;
	}
}
