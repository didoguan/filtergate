package com.deepspc.filtergate.modular.customer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.modular.customer.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface CustomerMapper extends BaseMapper<Customer> {

	/**
	 * 查询客户信息
	 * @param customerName 客户名称
	 * @return
	 */
	Page<Map<String, Object>> getCustomers(@Param("page") Page page, @Param("customerName") String customerName);
}
