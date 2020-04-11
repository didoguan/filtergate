package com.deepspc.filtergate.modular.warm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepspc.filtergate.modular.warm.entity.CustomerConf;
import com.deepspc.filtergate.modular.warm.mapper.CustomerConfMapper;
import com.deepspc.filtergate.modular.warm.service.ICustomerConfService;
import org.springframework.stereotype.Service;

/**
 * @Description 用户配置服务实现类
 * @Author didoguan
 * @Date 2020/4/9
 **/
@Service
public class CustomerConfServiceImpl extends ServiceImpl<CustomerConfMapper, CustomerConf> implements ICustomerConfService {
}
