package com.deepspc.filtergate.modular.examinegate.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepspc.filtergate.core.common.page.LayuiPageFactory;
import com.deepspc.filtergate.core.exception.BizExceptionEnum;
import com.deepspc.filtergate.core.exception.ServiceException;
import com.deepspc.filtergate.modular.examinegate.entity.ExamineGate;
import com.deepspc.filtergate.modular.examinegate.mapper.ExamineGateMapper;
import com.deepspc.filtergate.modular.examinegate.service.IExamineGateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;

/**
 * @Description 检测门服务实现
 * @Author didoguan
 * @Date 2020/3/4
 **/
@Service
@EnableTransactionManagement
public class ExamineGateServiceImpl extends ServiceImpl<ExamineGateMapper, ExamineGate> implements IExamineGateService {

	@Override
	public Page<Map<String, Object>> getExamineGates(String customerName) {
		Page page = LayuiPageFactory.defaultPage();
		return this.baseMapper.getExamineGates(page, customerName);
	}

	@Override
	public void saveUpdateExamineGate(ExamineGate examineGate) {
		if (null == examineGate) {
			throw new ServiceException(BizExceptionEnum.SAVE_OBJ_ERROR.getCode(),
					BizExceptionEnum.SAVE_OBJ_ERROR.getMessage());
		}
		this.saveOrUpdate(examineGate);
	}

	@Override
	public ExamineGate getExamineGate(Long gateId) {
		return this.getById(gateId);
	}

	@Override
	public void removeExamineGate(Long gateId) {
		this.removeById(gateId);
	}
}
