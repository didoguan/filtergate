package com.deepspc.filtergate.modular.warm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepspc.filtergate.modular.warm.entity.ModelInfo;
import com.deepspc.filtergate.modular.warm.mapper.ModelInfoMapper;
import com.deepspc.filtergate.modular.warm.service.IModelInfoService;
import org.springframework.stereotype.Service;

/**
 * @Description 模式服务实现类
 * @Author didoguan
 * @Date 2020/4/8
 **/
@Service
public class ModelInfoImpl extends ServiceImpl<ModelInfoMapper, ModelInfo> implements IModelInfoService {

}
