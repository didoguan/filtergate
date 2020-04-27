package com.deepspc.filtergate.modular.warm.model;

import com.deepspc.filtergate.modular.warm.entity.ModelInfo;
import com.deepspc.filtergate.modular.warm.entity.RoomInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 保存模式及所属房间
 * @Author didoguan
 * @Date 2020/4/8
 **/
@Data
public class ModelSaveDto implements Serializable {
    private static final long serialVersionUID = -7933316385309271826L;

    private Long customerId;

    private ModelInfo modelInfo;

    private List<Long> modelRooms;

    public ModelSaveDto() {

    }
}
