package com.deepspc.filtergate.modular.warm.controller;

import cn.hutool.core.util.StrUtil;
import com.deepspc.filtergate.core.reqres.response.ResponseData;
import com.deepspc.filtergate.modular.controller.BaseController;
import com.deepspc.filtergate.modular.warm.entity.*;
import com.deepspc.filtergate.modular.warm.model.IconInfoDto;
import com.deepspc.filtergate.modular.warm.model.ModelSaveDto;
import com.deepspc.filtergate.modular.warm.service.IWarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description 供暖监控控制器
 * @Author didoguan
 * @Date 2020/4/6
 **/
@Controller
@RequestMapping("/rpc/warm")
public class WarmController extends BaseController {

	@Autowired
	private IWarmService warmService;

	/**
	 * 获取所有模式
	 * @return
	 */
	@RequestMapping(value = "/getAllModels")
	@ResponseBody
	public Object getAllModels(@RequestParam Long customerId) {
		ResponseData resp = new ResponseData(true, 200, null, null);
		resp.setData(warmService.getAllModels(customerId));
		return resp;
	}

	/**
	 * 添加或修改模式
	 * @param dto
	 * @return
	 */
    @RequestMapping(value = "/addUpdateModel")
    @ResponseBody
	public Object addUpdateModel(@RequestBody ModelSaveDto dto) {
        ResponseData resp = new ResponseData(true, 200, null, null);
        if (null != dto && null != dto.getCustomerId()) {
            warmService.saveUpdateModelRoom(dto, dto.getCustomerId());
        } else {
            resp.setCode(202);
            resp.setMessage("客户标识不能为空");
            resp.setSuccess(false);
        }
        return resp;
    }

	/**
	 * 删除模式
	 * @param modelId
	 * @return
	 */
	@RequestMapping(value = "/removeModel")
    @ResponseBody
    public Object removeModel(@RequestParam Long modelId) {
        ResponseData resp = new ResponseData(true, 200, null, null);
        if (null != modelId) {
            warmService.deleteModel(modelId);
        } else {
        	resp.setCode(201);
        	resp.setSuccess(false);
        	resp.setMessage("模式标识为空");
		}
        return resp;
    }

	/**
	 * 增加房间
	 * @param roomInfos
	 * @return
	 */
	@RequestMapping(value = "/addRoom")
    @ResponseBody
    public Object addRoom(@RequestBody List<RoomInfo> roomInfos) {
        ResponseData resp = new ResponseData(true, 200, null, null);
        if (null != roomInfos && !roomInfos.isEmpty()) {
            warmService.addModelsRoom(roomInfos);
        } else {
			resp.setCode(201);
			resp.setSuccess(false);
			resp.setMessage("房间信息为空");
		}
        return resp;
    }

	@RequestMapping(value = "/editRoom")
	@ResponseBody
    public Object editRoom(@RequestBody RoomInfo roomInfo) {
		ResponseData resp = new ResponseData(true, 200, null, null);
		if (null != roomInfo) {
			warmService.updateModelRoom(roomInfo);
		} else {
			resp.setCode(201);
			resp.setSuccess(false);
			resp.setMessage("房间信息为空");
		}
		return resp;
	}

	/**
	 * 删除房间
	 * @param roomIds
	 * @return
	 */
	@RequestMapping(value = "/removeRoom")
    @ResponseBody
    public Object removeRoom(String roomIds) {
        ResponseData resp = new ResponseData(true, 200, null, null);
        if (StrUtil.isNotBlank(roomIds)) {
            warmService.delteModelsRooms(roomIds);
        } else {
			resp.setCode(201);
			resp.setSuccess(false);
			resp.setMessage("房间标识为空");
		}
        return resp;
    }

	/**
	 * 获取房间详情
	 * @param uniqueNo 房间唯一码
	 * @return
	 */
	@RequestMapping(value = "/getRoomInfo")
	@ResponseBody
	public Object getRoomInfo(@RequestParam String uniqueNo) {
		ResponseData resp = new ResponseData(true, 200, null, null);
		if (StrUtil.isNotBlank(uniqueNo)) {
			RoomInfo roomInfo = warmService.getRoomInfo(uniqueNo);
			resp.setData(roomInfo);
		} else {
			resp.setCode(201);
			resp.setSuccess(false);
			resp.setMessage("房间唯一码为空");
		}
		return resp;
	}

	/**
	 * 获取所有消息
	 * @return
	 */
	@RequestMapping(value = "/getAllMessage")
	@ResponseBody
    public Object getAllMessage() {
		ResponseData resp = new ResponseData(true, 200, null, null);
		Object id = this.getHttpServletRequest().getAttribute("tokenUserId");
		if (null != id) {
			List<Message> list = warmService.getAllMessage(Long.valueOf(id.toString()));
			resp.setData(list);
		} else {
			resp.setCode(201);
			resp.setSuccess(false);
			resp.setMessage("客户标识为空");
		}
		return resp;
	}

	/**
	 * 删除消息
	 * @param messageId
	 * @return
	 */
	@RequestMapping(value = "/removeMessage")
	@ResponseBody
	public Object removeMessage(@RequestParam Long messageId) {
		ResponseData resp = new ResponseData(true, 200, null, null);
		if (null != messageId) {
			warmService.deleteMessage(messageId);
		} else {
			resp.setCode(201);
			resp.setSuccess(false);
			resp.setMessage("消息标识为空");
		}
		return resp;
	}

	/**
	 * 获取消息详情
	 * @param messageId
	 * @return
	 */
	@RequestMapping(value = "/getMessage")
	@ResponseBody
	public Object getMessage(@RequestParam Long messageId) {
		ResponseData resp = new ResponseData(true, 200, null, null);
		if (null != messageId) {
			Message msg = warmService.getMsgDetail(messageId);
			resp.setData(msg);
		} else {
			resp.setCode(201);
			resp.setSuccess(false);
			resp.setMessage("消息标识为空");
		}
		return resp;
	}

	/**
	 * 保存用户配置信息
	 * @param dtos
	 * @return
	 */
	@RequestMapping(value = "/saveCustomerConfig")
	@ResponseBody
	public Object customerConfig(@RequestBody List<CustomerConf> dtos) {
		ResponseData resp = new ResponseData(true, 200, null, null);
		if (null != dtos && !dtos.isEmpty()) {
			warmService.saveUpdateCustomerConfig(dtos);
		} else {
			resp.setCode(201);
			resp.setSuccess(false);
			resp.setMessage("用户配置信息为空");
		}
		return resp;
	}

	/**
	 * 获取用户配置信息
	 * @param customerId
	 * @return
	 */
	@RequestMapping(value = "/getCustomerConfigs")
	@ResponseBody
	public Object getCustomerConfigs(@RequestParam Long customerId) {
		ResponseData resp = new ResponseData(true, 200, null, null);
		if (null != customerId) {
			List<CustomerConf> list = warmService.getCustomerConfigs(customerId);
			resp.setData(list);
		} else {
			resp.setCode(201);
			resp.setSuccess(false);
			resp.setMessage("客户标识为空");
		}
		return resp;
	}

	@RequestMapping(value = "/getRoomHistory")
	@ResponseBody
	public Object getRoomHistory(@RequestParam String type, @RequestParam String uniqueNo) {
		ResponseData resp = new ResponseData(true, 200, null, null);
		if (StrUtil.isNotBlank(type)) {
			List<RoomHis> list = warmService.getRoomHistory(type, uniqueNo);
			resp.setData(list);
		} else {
			resp.setCode(201);
			resp.setSuccess(false);
			resp.setMessage("过滤类型不合法");
		}
		return resp;
	}

	/**
	 * 获取所有可选的小图标
	 * @param iconType 1-头像 2-小图标
	 * @return
	 */
	@RequestMapping(value = "/getAllSelIcon")
	@ResponseBody
	public Object getAllSelIcon(@RequestParam(required = false) Integer iconType) {
		ResponseData resp = new ResponseData(true, 200, null, null);
		List<IconInfoDto> icons = warmService.getAllAccessIcon(iconType);
		resp.setData(icons);
		return resp;
	}
}
