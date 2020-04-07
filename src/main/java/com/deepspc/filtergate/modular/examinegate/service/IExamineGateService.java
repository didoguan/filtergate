package com.deepspc.filtergate.modular.examinegate.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.modular.examinegate.entity.ExamineGate;

import java.util.Map;

/**
 * @Description 检测门服务接口
 * @Author didoguan
 * @Date 2020/3/4
 **/
public interface IExamineGateService {

	/**
	 * 查询检测门信息
	 * @param customerName 客户名称
	 * @return
	 */
	Page<Map<String, Object>> getExamineGates(String customerName);

	/**
	 * 新增修改检测门信息
	 * @param examineGate
	 */
	void saveUpdateExamineGate(ExamineGate examineGate);

	/**
	 * 获取检测门信息
	 * @param gateId 主键标识
	 * @return
	 */
	ExamineGate getExamineGate(Long gateId);

	/**
	 * 删除检测门信息
	 * @param gateId 主键标识
	 */
	void removeExamineGate(Long gateId);
}
