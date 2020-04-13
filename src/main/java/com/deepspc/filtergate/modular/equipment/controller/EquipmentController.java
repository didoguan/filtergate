package com.deepspc.filtergate.modular.equipment.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.core.common.page.LayuiPageFactory;
import com.deepspc.filtergate.modular.controller.BaseController;
import com.deepspc.filtergate.modular.equipment.model.QueryParam;
import com.deepspc.filtergate.modular.equipment.service.IEquipmentService;
import com.deepspc.filtergate.modular.equipment.warpper.EquipmentWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Description 设备管理控制器
 * @Author didoguan
 * @Date 2020/4/13
 **/
@Controller
@RequestMapping("/equipment")
public class EquipmentController extends BaseController {

    private String PREFIX = "/modular/equipment/";

    @Autowired
    private IEquipmentService equipmentService;

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
    public Object list(@RequestBody QueryParam queryParam) {
        Page<Map<String, Object>> list = equipmentService.getEquipments(queryParam);
        Page<Map<String, Object>> wrap = new EquipmentWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }
}