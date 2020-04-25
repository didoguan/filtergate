package com.deepspc.filtergate.modular.warm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepspc.filtergate.modular.warm.entity.IconInfo;
import com.deepspc.filtergate.modular.warm.model.IconInfoDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IconInfoMapper extends BaseMapper<IconInfo> {

	/**
	 * 获取所有图标
	 * @param iconType 1-头像 2-小图标
	 * @return
	 */
	List<IconInfoDto> getAllAccessIcon(@Param("iconType") Integer iconType);

	/**
	 * 获取图标
	 * @param params 查询参数
	 * @return
	 */
	List<IconInfoDto> getIconInfos(@Param("params") Map<String, Object> params);
}
