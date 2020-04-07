package com.deepspc.filtergate.modular.examinegate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.modular.examinegate.entity.ExamineGate;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ExamineGateMapper extends BaseMapper<ExamineGate> {

	/**
	 * 查询检测门信息
	 * @param customerName 客户名称
	 * @return
	 */
	Page<Map<String, Object>> getExamineGates(@Param("page") Page page, @Param("customerName") String customerName);
}
