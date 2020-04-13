package com.deepspc.filtergate.modular.warm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deepspc.filtergate.modular.warm.entity.EquipmentInfo;
import com.deepspc.filtergate.modular.warm.model.QueryParam;

import java.util.Map;

/**
 * 设备信息接口
 */
public interface IEquipmentInfoService extends IService<EquipmentInfo> {

    /**
     * 查询设备
     * @param queryParam 过滤条件
     * @return
     */
    Page<Map<String, Object>> getEquipments(QueryParam queryParam);

	/**
	 * 获取设备详情
	 * @param equipmentId 设备标识
	 * @return
	 */
	EquipmentInfo getEquipmentInfo(Long equipmentId);

	/**
	 * 保存更新设备信息
	 * @param equipmentInfo
	 */
	void saveUpdateEquipment(EquipmentInfo equipmentInfo);

	/**
	 * 删除设备
	 * @param equipmentId 设备标识
	 */
	void removeEquipment(Long equipmentId);
}
