package com.deepspc.filtergate.modular.customer.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepspc.filtergate.core.common.page.LayuiPageFactory;
import com.deepspc.filtergate.core.exception.BizExceptionEnum;
import com.deepspc.filtergate.core.exception.ServiceException;
import com.deepspc.filtergate.modular.customer.entity.Customer;
import com.deepspc.filtergate.modular.customer.mapper.CustomerMapper;
import com.deepspc.filtergate.modular.customer.service.ICustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;

/**
 * @Description 客户管理服务实现类
 * @Author didoguan
 * @Date 2020/3/2
 **/
@Service
@EnableTransactionManagement
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

	@Override
	public Page<Map<String, Object>> getCustomers(String customerName) {
		Page page = LayuiPageFactory.defaultPage();
		return this.baseMapper.getCustomers(page, customerName);
	}

	@Override
	public void saveUpdateCustomer(Customer customer) {
		if (null == customer) {
			throw new ServiceException(BizExceptionEnum.SAVE_OBJ_ERROR.getCode(),
					BizExceptionEnum.SAVE_OBJ_ERROR.getMessage());
		}
		this.saveOrUpdate(customer);
	}

	/**
	 * 获取客户对象
	 * @param customerId
	 * @return
	 */
	@Override
	public Customer getCustomer(Long customerId) {
		return this.getById(customerId);
	}

	@Override
	public void removeCustomer(Long customerId) {
		this.removeById(customerId);
	}
}
