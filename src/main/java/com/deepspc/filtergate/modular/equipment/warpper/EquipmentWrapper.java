package com.deepspc.filtergate.modular.equipment.warpper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.core.warpper.BaseControllerWrapper;

import java.util.Map;

/**
 * @Description 结果封装处理类
 * @Author didoguan
 * @Date 2020/4/13
 **/
public class EquipmentWrapper extends BaseControllerWrapper {

    public EquipmentWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        int type = Integer.parseInt(map.get("equipmentType").toString());
        int statusVal = Integer.parseInt(map.get("status").toString());
        String equipmentType = "", status = "";
        if (1 == type) {
            equipmentType = "控制器主机";
        } else if (2 == type) {
            equipmentType = "温度执行器";
        }
        if (0 == statusVal) {
            status = "停用";
        } else if (1 == statusVal) {
            status = "启用";
        }
        map.put("equipmentType", equipmentType);
        map.put("status", status);
    }
}
