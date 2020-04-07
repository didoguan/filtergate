package com.deepspc.filtergate.modular.system.warpper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.core.common.constant.factory.ConstantFactory;
import com.deepspc.filtergate.core.enums.BizEnum;
import com.deepspc.filtergate.core.page.PageResult;
import com.deepspc.filtergate.core.warpper.BaseControllerWrapper;

import java.util.List;
import java.util.Map;

/**
 * 菜单列表的包装类
 *
 */
public class MenuWrapper extends BaseControllerWrapper {

    public MenuWrapper(Map<String, Object> single) {
        super(single);
    }

    public MenuWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public MenuWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public MenuWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {
        map.put("statusName", ConstantFactory.me().getMenuStatusName((String) map.get("status")));

        String menuFlag = (String) map.get("menuFlag");
        if (BizEnum.YES.getCode().equals(menuFlag)) {
            map.put("isMenuName", BizEnum.YES.getMessage());
        } else if (BizEnum.NO.getCode().equals(menuFlag)) {
            map.put("isMenuName", BizEnum.NO.getMessage());
        }
    }

}
