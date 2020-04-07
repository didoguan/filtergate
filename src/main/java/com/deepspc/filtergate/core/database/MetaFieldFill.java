package com.deepspc.filtergate.core.database;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.deepspc.filtergate.core.shiro.ShiroKit;
import com.deepspc.filtergate.core.shiro.ShiroUser;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 实体类自动填充字段
 */
@Component
public class MetaFieldFill implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        ShiroUser user = ShiroKit.getUser();
        if (null != user && null != user.getId()) {
            this.setFieldValByName("createUser", user.getId(), metaObject);
        }
        this.setFieldValByName("createTime", new Date(), metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        ShiroUser user = ShiroKit.getUser();
        if (null != user && null != user.getId()) {
            this.setFieldValByName("updateUser", user.getId(), metaObject);
        }
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
