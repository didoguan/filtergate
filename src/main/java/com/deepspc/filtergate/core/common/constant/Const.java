package com.deepspc.filtergate.core.common.constant;

import cn.hutool.core.collection.CollectionUtil;

import java.util.List;

/**
 * 系统常量
 */
public interface Const {

    /**
     * 系统默认的管理员密码
     */
    String DEFAULT_PWD = "111111";

    /**
     * 管理员角色的名字
     */
    String ADMIN_NAME = "administrator";

    /**
     * 管理员id
     */
    Long ADMIN_ID = 1L;

    /**
     * 超级管理员角色id
     */
    Long ADMIN_ROLE_ID = 1L;

    /**
     * 接口文档的菜单名
     */
    String API_MENU_NAME = "接口文档";

    /**
     * 不需要权限验证的资源表达式
     */
    List<String> NONE_PERMISSION_RES = CollectionUtil.newLinkedList("/assets/**",
			"/stoneApi/**",
			"/login",
			"/rpclogin",
			"/global/sessionError",
			"/kaptcha",
			"/error",
			"/global/error");

}
