package com.deepspc.filtergate.modular.examinegate.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.core.common.page.LayuiPageFactory;
import com.deepspc.filtergate.core.exception.BizExceptionEnum;
import com.deepspc.filtergate.core.exception.ServiceException;
import com.deepspc.filtergate.core.reqres.response.ResponseData;
import com.deepspc.filtergate.modular.controller.BaseController;
import com.deepspc.filtergate.modular.examinegate.entity.ExamineGate;
import com.deepspc.filtergate.modular.examinegate.service.IExamineGateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Description 检测门控制器
 * @Author didoguan
 * @Date 2020/3/2
 **/
@Controller
@RequestMapping("/gate")
public class ExamineGateController extends BaseController {

	private String PREFIX = "/modular/examinegate/";

	@Autowired
	private IExamineGateService examineGateService;

	@RequestMapping("")
	public String list() {
		return PREFIX + "examinegate.html";
	}

	@RequestMapping(value = "/addPage")
	public String addPage() {
		return PREFIX + "/examinegate_add.html";
	}

	@RequestMapping(value = "/editPage")
	public String editPage() {
		return PREFIX + "/examinegate_edit.html";
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(value = "customerName", required = false) String customerName) {
		Page<Map<String, Object>> list = examineGateService.getExamineGates(customerName);
		return LayuiPageFactory.createPageInfo(list);
	}

	@RequestMapping(value = "/view/{gateId}")
	@ResponseBody
	public ResponseData view(@PathVariable Long gateId) {
		if (null == gateId) {
			throw new ServiceException(BizExceptionEnum.FIELD_UNAVAIL.getCode(),
					BizExceptionEnum.FIELD_UNAVAIL.getMessage());
		}
		ExamineGate examineGate = examineGateService.getExamineGate(gateId);
		Map<String, Object> map = BeanUtil.beanToMap(examineGate);
		return ResponseData.success(map);
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	public ResponseData addExamineGate(ExamineGate examineGate) {
		examineGateService.saveUpdateExamineGate(examineGate);
		return SUCCESS_TIP;
	}

	@RequestMapping(value = "/edit")
	@ResponseBody
	public ResponseData editExamineGate(ExamineGate examineGate) {
		examineGateService.saveUpdateExamineGate(examineGate);
		return SUCCESS_TIP;
	}

	@RequestMapping(value = "/remove")
	@ResponseBody
	public ResponseData remove(@RequestParam Long gateId) {
		examineGateService.removeExamineGate(gateId);
		return SUCCESS_TIP;
	}
}
