package com.deepspc.filtergate.modular.warm.service;

import com.deepspc.filtergate.modular.warm.entity.*;
import com.deepspc.filtergate.modular.warm.model.IconInfoDto;
import com.deepspc.filtergate.modular.warm.model.ModelSaveDto;

import java.util.List;

public interface IWarmService {

	/**
	 * 获取所有模式
	 * @param customerId
	 * @return
	 */
    List<ModelInfo> getAllModels(Long customerId);

    /**
     * 获取所有房间
     * @param customerId
     * @return
     */
    List<RoomInfo> getAllRooms(Long customerId);

    /**
     * 新增或更新模式及房间信息
     * @param dto
     */
	void saveUpdateModelRoom(ModelSaveDto dto, Long customerId);

    /**
     * 删除模式
     * @param modelId
     */
	void deleteModel(Long modelId);

    /**
     * 添加房间，返回已保存的房间标识
     */
	Long addRoom(RoomInfo roomInfo);

    /**
     * 删除模式下的房间
     */
	void deleteModelRooms(Long modelId, String roomIds);

    /**
     * 删除房间
     * @param ids
     */
	void deleteRooms(String ids);

	/**
	 * 修改房间
	 * @param roomInfo
	 */
	void updateRoom(RoomInfo roomInfo);

	/**
	 * 获取所有消息
	 * @param customerId
	 * @return
	 */
	List<Message> getAllMessage(Long customerId);

	/**
	 * 删除消息
	 * @param messageId
	 */
	void deleteMessage(Long messageId);

	/**
	 * 获取消息详情
	 * @param messageId
	 * @return
	 */
	Message getMsgDetail(Long messageId);

	/**
	 * 添加或更新用户配置
	 * @param customerConfs
	 */
	void saveUpdateCustomerConfig(List<CustomerConf> customerConfs);

	/**
	 * 获取用户配置信息
	 * @param customerId
	 * @return
	 */
	List<CustomerConf> getCustomerConfigs(Long customerId);

	/**
	 * 获取房间历史温湿度
	 * @param type 按天(D)，月(M)，年(Y)过滤
	 * @return
	 */
	List<RoomHis> getRoomHistory(String type, String uniqueNo);

	/**
	 * 查询房间详情
	 * @param uniqueNo 房间唯一码
	 * @return
	 */
	RoomInfo getRoomInfo(String uniqueNo);

	/**
	 * 根据类型获取所有图标
	 * @param iconType 1-头像 2-小图标
	 * @return
	 */
	List<IconInfoDto> getAllAccessIcon(Integer iconType);
}
