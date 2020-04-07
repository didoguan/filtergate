package com.deepspc.filtergate.modular.system.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 字典信息
 */
@Data
public class DictDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型id
     */
    private Long dictTypeId;
    /**
     * 名称
     */
    @NotEmpty(message="字典名称不能为空")
    private String name;
    /**
     * 编码
     */
    @NotEmpty(message="字典编码不能为空")
    private String code;
	/**
	 * 关联字典
	 */
	private Long refId;
    /**
     * 备注
     */
    private String description;
    /**
     * 序号
     */
    private Integer sort;
}
