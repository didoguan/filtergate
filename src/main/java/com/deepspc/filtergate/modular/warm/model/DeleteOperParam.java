package com.deepspc.filtergate.modular.warm.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 删除操作的参数对象
 * @Author didoguan
 * @Date 2020/4/27
 **/
@Data
public class DeleteOperParam implements Serializable {
    private static final long serialVersionUID = -8037786805756725453L;

    private Long customerId;

    private Long modelId;

    private String ids;

    public DeleteOperParam() {

    }
}
