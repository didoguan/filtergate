package com.deepspc.filtergate.modular.warm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.core.common.page.LayuiPageFactory;
import com.deepspc.filtergate.core.exception.BizExceptionEnum;
import com.deepspc.filtergate.core.exception.ServiceException;
import com.deepspc.filtergate.core.reqres.response.ResponseData;
import com.deepspc.filtergate.modular.controller.BaseController;
import com.deepspc.filtergate.modular.warm.entity.EquipmentInfo;
import com.deepspc.filtergate.modular.warm.model.QueryParam;
import com.deepspc.filtergate.modular.warm.service.IEquipmentInfoService;
import com.deepspc.filtergate.modular.warm.warpper.EquipmentInfoWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description 设备管理控制器
 * @Author didoguan
 * @Date 2020/4/13
 **/
@Controller
@RequestMapping("/equipment")
public class EquipmentInfoController extends BaseController {

    private String PREFIX = "/modular/equipment/";

    @Autowired
    private IEquipmentInfoService equipmentInfoService;

    @RequestMapping("")
    public String list() {
        return PREFIX + "equipment.html";
    }

    @RequestMapping(value = "/addPage")
    public String addPage() {
        return PREFIX + "/equipment_add.html";
    }

    @RequestMapping(value = "/editPage")
    public String editPage() {
        return PREFIX + "/equipment_edit.html";
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "equipmentName", required = false) String equipmentName,
					   @RequestParam(value = "customerId", required = false) Long customerId) {
		QueryParam queryParam = new QueryParam();
		queryParam.setEquipmentName(equipmentName);
		queryParam.setCustomerId(customerId);
        Page<Map<String, Object>> list = equipmentInfoService.getEquipments(queryParam);
        Page<Map<String, Object>> wrap = new EquipmentInfoWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

	@RequestMapping(value = "/view/{equipmentId}")
	@ResponseBody
	public ResponseData view(@PathVariable Long equipmentId) {
		if (null == equipmentId) {
			throw new ServiceException(BizExceptionEnum.FIELD_UNAVAIL.getCode(),
					BizExceptionEnum.FIELD_UNAVAIL.getMessage());
		}
		EquipmentInfo info = equipmentInfoService.getEquipmentInfo(equipmentId);
		return ResponseData.success(info);
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public ResponseData addEquipmentInfo(EquipmentInfo equipmentInfo) {
		equipmentInfoService.saveUpdateEquipment(equipmentInfo);
		return SUCCESS_TIP;
	}

	@RequestMapping(value = "/remove")
	@ResponseBody
	public ResponseData remove(@RequestParam Long equipmentId) {
		equipmentInfoService.removeEquipment(equipmentId);
		return SUCCESS_TIP;
	}
}