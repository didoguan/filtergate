package com.deepspc.filtergate.modular.customer.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.modular.customer.entity.Customer;

import java.util.Map;

/**
 * @Description 客户管理服务接口
 * @Author didoguan
 * @Date 2020/3/2
 **/
public interface ICustomerService {

	/**
	 * 查询客户信息
	 * @param customerName 客户名称
	 * @return
	 */
	Page<Map<String, Object>> getCustomers(String customerName);

	/**
	 * 新增修改客户信息
	 * @param customer
	 */
	void saveUpdateCustomer(Customer customer);

	/**
	 * 获取客户信息
	 * @param customerId 主键标识
	 * @return
	 */
	Customer getCustomer(Long customerId);

	/**
	 * 删除客户信息
	 * @param customerId 主键标识
	 */
	void removeCustomer(Long customerId);
}
