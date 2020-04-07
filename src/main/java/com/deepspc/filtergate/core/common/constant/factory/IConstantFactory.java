package com.deepspc.filtergate.core.common.constant.factory;


import com.deepspc.filtergate.modular.system.entity.Dict;
import com.deepspc.filtergate.modular.system.entity.Menu;

import java.util.List;

/**
 * 常量生产工厂的接口
 */
public interface IConstantFactory {

    /**
     * 根据用户id获取用户名称
     *
     */
    String getUserNameById(Long userId);

    /**
     * 根据用户id获取用户账号
     *
     */
    String getUserAccountById(Long userId);

    /**
     * 通过角色ids获取角色名称
     */
    String getRoleName(String roleIds);

    /**
     * 通过角色id获取角色名称
     */
    String getSingleRoleName(Long roleId);

    /**
     * 通过角色id获取角色英文名称
     */
    String getSingleRoleTip(Long roleId);

    /**
     * 获取部门名称
     */
    String getDeptName(Long deptId);

    /**
     * 获取菜单的名称们(多个)
     */
    String getMenuNames(String menuIds);

    /**
     * 获取菜单名称
     */
    String getMenuName(Long menuId);

    /**
     * 获取菜单通过编号
     */
    Menu getMenuByCode(String code);

    /**
     * 获取菜单名称通过编号
     */
    String getMenuNameByCode(String code);

    /**
     * 获取菜单名称通过编号
     */
    Long getMenuIdByCode(String code);

    /**
     * 获取字典名称
     */
    String getDictName(long dictId);

	/**
	 * 获取字典名称
	 * @return
	 */
	String getDictName(String parentCode, String code);

    /**
     * 获取通知标题
     */
    String getNoticeTitle(Long dictId);

	/**
	 * 获取所有字典(先查缓存)
	 * @return
	 */
	List<Dict> getAllDict();

	/**
	 * 获取子字典
	 * @param code 字典编码
	 * @return
	 */
	List<Dict> getChildDicts(String parentCode, String code);

	/**
	 * 获取子字典
	 * @param id
	 * @return
	 */
	List<Dict> getChildDicts(long id);

	/**
	 * 获取关联字典
	 * @param id 主键值
	 * @return
	 */
	List<Dict> getRefDicts(long id);
    /**
     * 获取性别名称
     */
    String getSexName(String sexCode);

    /**
     * 获取用户登录状态
     */
    String getStatusName(String status);

    /**
     * 获取菜单状态
     */
    String getMenuStatusName(String status);

    /**
     * 查询字典
     */
    List<Dict> findInDict(Long id);

    /**
     * 获取被缓存的对象(用户删除业务)
     */
    String getCacheObject(String para);

    /**
     * 获取子部门id
     */
    List<Long> getSubDeptId(Long deptId);

    /**
     * 获取所有父部门id
     */
    List<Long> getParentDeptIds(Long deptId);

}
