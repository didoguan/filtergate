package com.deepspc.filtergate.modular.customer.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.core.common.annotion.Permission;
import com.deepspc.filtergate.core.common.constant.factory.ConstantFactory;
import com.deepspc.filtergate.core.common.page.LayuiPageFactory;
import com.deepspc.filtergate.core.exception.BizExceptionEnum;
import com.deepspc.filtergate.core.exception.ServiceException;
import com.deepspc.filtergate.core.reqres.response.ResponseData;
import com.deepspc.filtergate.modular.controller.BaseController;
import com.deepspc.filtergate.modular.customer.entity.Customer;
import com.deepspc.filtergate.modular.customer.service.ICustomerService;
import com.deepspc.filtergate.modular.customer.warpper.CustomerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @Description 客户管理
 * @Author didoguan
 * @Date 2020/3/2
 **/
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

	private String PREFIX = "/modular/customer/";

	@Autowired
	private ICustomerService customerService;
	@Autowired
	private ConstantFactory constantFactory;

	@RequestMapping("")
	public String list() {
		return PREFIX + "customer.html";
	}

	@RequestMapping(value = "/addPage")
	public String addPage() {
		return PREFIX + "/customer_add.html";
	}

	@RequestMapping(value = "/editPage")
	public String editPage() {
		return PREFIX + "/customer_edit.html";
	}

	@RequestMapping(value = "/selCustomerPage")
	public String selectCustomerPage() {
		return PREFIX + "/customer_select.html";
	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(value = "customerName", required = false) String customerName) {
		Page<Map<String, Object>> list = customerService.getCustomers(customerName);
		Page<Map<String, Object>> wrap = new CustomerWrapper(list).wrap();
		return LayuiPageFactory.createPageInfo(wrap);
	}

	@RequestMapping(value = "/view/{customerId}")
	@ResponseBody
	public ResponseData view(@PathVariable Long customerId) {
		if (null == customerId) {
			throw new ServiceException(BizExceptionEnum.FIELD_UNAVAIL.getCode(),
					BizExceptionEnum.FIELD_UNAVAIL.getMessage());
		}
		Customer customer = customerService.getCustomer(customerId);
		Map<String, Object> map = BeanUtil.beanToMap(customer);
		String cityText = constantFactory.getDictName(customer.getCityValue());
		String distText = constantFactory.getDictName(customer.getDistrictValue());
		map.put("cityText", cityText);
		map.put("distText", distText);
		return ResponseData.success(map);
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	public ResponseData addCustomer(Customer customer) {
		customerService.saveUpdateCustomer(customer);
		return SUCCESS_TIP;
	}

	@RequestMapping(value = "/edit")
	@ResponseBody
	public ResponseData editCustomer(Customer customer) {
		customerService.saveUpdateCustomer(customer);
		return SUCCESS_TIP;
	}

	@RequestMapping(value = "/remove")
	@ResponseBody
	public ResponseData remove(@RequestParam Long customerId) {
		customerService.removeCustomer(customerId);
		return SUCCESS_TIP;
	}

}
