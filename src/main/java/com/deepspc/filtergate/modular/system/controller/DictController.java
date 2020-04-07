package com.deepspc.filtergate.modular.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.core.common.annotion.BussinessLog;
import com.deepspc.filtergate.core.common.annotion.Permission;
import com.deepspc.filtergate.core.common.constant.Const;
import com.deepspc.filtergate.core.common.constant.dictmap.DictMap;
import com.deepspc.filtergate.core.common.constant.factory.ConstantFactory;
import com.deepspc.filtergate.core.common.node.ZTreeNode;
import com.deepspc.filtergate.core.common.page.LayuiPageFactory;
import com.deepspc.filtergate.core.log.LogObjectHolder;
import com.deepspc.filtergate.core.reqres.response.ResponseData;
import com.deepspc.filtergate.modular.controller.BaseController;
import com.deepspc.filtergate.modular.system.entity.Dict;
import com.deepspc.filtergate.modular.system.model.DictDto;
import com.deepspc.filtergate.modular.system.service.DictService;
import com.deepspc.filtergate.modular.system.warpper.DictWrapper;
import com.deepspc.filtergate.utils.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 字典控制器
 *
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {

    private String PREFIX = "/modular/system/dict/";

    @Autowired
    private DictService dictService;
    @Autowired
    private ConstantFactory constantFactory;

    /**
     * 跳转到字典管理首页
     *
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dict.html";
    }

    /**
     * 跳转到添加字典类型
     *
     */
    @RequestMapping("/dict_add_type")
    public String deptAddType() {
        return PREFIX + "dict_add_type.html";
    }

    /**
     * 跳转到添加字典条目
     *
     */
    @RequestMapping("/dict_add_item")
    public String deptAddItem(@RequestParam("dictId") Long dictId, Model model) {
        model.addAttribute("dictTypeId", dictId);
        model.addAttribute("dictTypeName", ConstantFactory.me().getDictName(dictId));
        return PREFIX + "dict_add_item.html";
    }

    /**
     * 新增字典
     *
     */
    @RequestMapping(value = "/add")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData add(@Valid DictDto dictDto) {
        this.dictService.addDict(dictDto);
		CacheUtil.remove("COMMON", "Dicts");
        return SUCCESS_TIP;
    }

    /**
     * 获取所有字典列表
     *
     */
    @RequestMapping(value = "/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(String condition) {
        Page<Map<String, Object>> list = this.dictService.list(condition);
        Page<Map<String, Object>> warpper = new DictWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(warpper);
    }

    /**
     * 删除字典记录
     *
     */
    @BussinessLog(value = "删除字典记录", key = "dictId", dict = DictMap.class)
    @RequestMapping(value = "/delete")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData delete(@RequestParam Long dictId) {

        //缓存被删除的名称
        LogObjectHolder.me().set(ConstantFactory.me().getDictName(dictId));

        this.dictService.delteDict(dictId);

        return SUCCESS_TIP;
    }

	@RequestMapping(value = "/getChildDicts")
	@ResponseBody
    public ResponseData getChildDicts(@RequestParam(required = false) String parentCode, @RequestParam String code) {
		List<Dict> dicts = constantFactory.getChildDicts(parentCode, code);
		ResponseData resp = new ResponseData();
		resp.setCode(200);
		resp.setSuccess(true);
		resp.setData(dicts);
		return resp;
	}

	@RequestMapping(value = "/getRefDicts")
	@ResponseBody
	public ResponseData getRefDicts(@RequestParam Long id) {
		List<Dict> dicts = constantFactory.getRefDicts(id);
		ResponseData resp = new ResponseData();
		resp.setCode(200);
		resp.setSuccess(true);
		resp.setData(dicts);
		return resp;
	}

	@RequestMapping(value = "/reftree")
	@ResponseBody
	public List<ZTreeNode> reftree() {
		List<ZTreeNode> tree = this.dictService.reftree();
		tree.add(ZTreeNode.createParent());
		return tree;
	}

}
