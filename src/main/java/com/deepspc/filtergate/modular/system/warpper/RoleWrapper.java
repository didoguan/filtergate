package com.deepspc.filtergate.modular.system.warpper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.core.common.constant.factory.ConstantFactory;
import com.deepspc.filtergate.core.page.PageResult;
import com.deepspc.filtergate.core.warpper.BaseControllerWrapper;

import java.util.List;
import java.util.Map;

/**
 * 角色列表的包装类
 *
 */
public class RoleWrapper extends BaseControllerWrapper {

    public RoleWrapper(Map<String, Object> single) {
        super(single);
    }

    public RoleWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public RoleWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public RoleWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("pName", ConstantFactory.me().getSingleRoleName((Long) map.get("pid")));
        if (null != map.get("deptId")) {
            map.put("deptName", ConstantFactory.me().getDeptName((Long) map.get("deptId")));
        }
    }

}
