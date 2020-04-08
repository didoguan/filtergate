package com.deepspc.filtergate.modular.warm.controller;

import cn.hutool.core.util.StrUtil;
import com.deepspc.filtergate.core.reqres.response.ResponseData;
import com.deepspc.filtergate.modular.controller.BaseController;
import com.deepspc.filtergate.modular.warm.entity.RoomInfo;
import com.deepspc.filtergate.modular.warm.model.ModelData;
import com.deepspc.filtergate.modular.warm.model.ModelSaveDto;
import com.deepspc.filtergate.modular.warm.service.IModelInfoService;
import com.deepspc.filtergate.modular.warm.service.IRoomInfoService;
import com.deepspc.filtergate.modular.warm.service.IWarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public Object getAllModels() {
		ResponseData resp = new ResponseData(true, 200, null, null);
		Object id = this.getHttpServletRequest().getAttribute("tokenUserId");
		if (null != id) {
			String tokenUserId = id.toString();
			ModelData modelData = warmService.getAllModels(Long.parseLong(tokenUserId));
			resp.setData(modelData);
		}
		return resp;
	}

    @RequestMapping(value = "/addUpdateModel")
    @ResponseBody
	public Object addUpdateModel(@RequestBody ModelSaveDto dto) {
        ResponseData resp = new ResponseData(true, 200, null, null);
        Object id = this.getHttpServletRequest().getAttribute("tokenUserId");
        if (null != id) {
            warmService.saveUpdateModelRoom(dto, Long.valueOf(id.toString()));
        } else {
            resp.setCode(201);
            resp.setMessage("token不合法！");
            resp.setSuccess(false);
        }
        return resp;
    }

    @RequestMapping(value = "/removeModel")
    @ResponseBody
    public Object removeModel(Long modelId) {
        ResponseData resp = new ResponseData(true, 200, null, null);
        if (null != modelId) {
            warmService.deleteModel(modelId);
        }
        return resp;
    }

    @RequestMapping(value = "/addRoom")
    @ResponseBody
    public Object addRoom(@RequestBody List<RoomInfo> roomInfos) {
        ResponseData resp = new ResponseData(true, 200, null, null);
        if (null != roomInfos && !roomInfos.isEmpty()) {
            warmService.addModelsRoom(roomInfos);
        }
        return resp;
    }

    @RequestMapping(value = "/removeRoom")
    @ResponseBody
    public Object removeRoom(String roomIds) {
        ResponseData resp = new ResponseData(true, 200, null, null);
        if (StrUtil.isNotBlank(roomIds)) {
            warmService.delteModelsRooms(roomIds);
        }
        return resp;
    }
}
