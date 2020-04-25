package com.deepspc.filtergate.modular.warm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepspc.filtergate.modular.warm.entity.ModelRoom;
import com.deepspc.filtergate.modular.warm.mapper.ModelRoomMapper;
import com.deepspc.filtergate.modular.warm.service.IModelRoomService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author didoguan
 * @Date 2020/4/25
 **/
@Service
public class ModelRoomService extends ServiceImpl<ModelRoomMapper, ModelRoom> implements IModelRoomService{
}
