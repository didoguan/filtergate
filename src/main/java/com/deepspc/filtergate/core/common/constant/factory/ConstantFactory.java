package com.deepspc.filtergate.core.common.constant.factory;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepspc.filtergate.core.enums.BizEnum;
import com.deepspc.filtergate.core.log.LogObjectHolder;
import com.deepspc.filtergate.modular.system.entity.*;
import com.deepspc.filtergate.modular.system.mapper.*;
import com.deepspc.filtergate.utils.CacheUtil;
import com.deepspc.filtergate.utils.SpringContextHolder;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量的生产工厂
 */
@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {

    private RoleMapper roleMapper = SpringContextHolder.getBean(RoleMapper.class);
    private DeptMapper deptMapper = SpringContextHolder.getBean(DeptMapper.class);
    private DictMapper dictMapper = SpringContextHolder.getBean(DictMapper.class);
    private UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
    private MenuMapper menuMapper = SpringContextHolder.getBean(MenuMapper.class);
    private NoticeMapper noticeMapper = SpringContextHolder.getBean(NoticeMapper.class);

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    @Override
    public String getUserNameById(Long userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            return user.getName();
        } else {
            return "--";
        }
    }

    @Override
    public String getUserAccountById(Long userId) {
        User user = userMapper.selectById(userId);
        if (user != null) {
            return user.getAccount();
        } else {
            return "--";
        }
    }

    @Override
    public String getRoleName(String roleIds) {
        if (StrUtil.isEmpty(roleIds)) {
            return "";
        }
        Long[] roles = Convert.toLongArray(roleIds);
        StringBuilder sb = new StringBuilder();
        for (Long role : roles) {
            Role roleObj = roleMapper.selectById(role);
            if (null != roleObj && StrUtil.isNotEmpty(roleObj.getName())) {
                sb.append(roleObj.getName()).append(",");
            }
        }
        return StrUtil.removeSuffix(sb.toString(), ",");
    }

    @Override
    public String getSingleRoleName(Long roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = roleMapper.selectById(roleId);
        if (null != roleId && StrUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getName();
        }
        return "";
    }

    @Override
    public String getSingleRoleTip(Long roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = roleMapper.selectById(roleId);
        if (null != roleObj && StrUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getDescription();
        }
        return "";
    }

    @Override
    public String getDeptName(Long deptId) {
        if (deptId == null) {
            return "";
        } else if (deptId == 0L) {
            return "顶级";
        } else {
            Dept dept = deptMapper.selectById(deptId);
            if (null != dept && StrUtil.isNotEmpty(dept.getFullName())) {
                return dept.getFullName();
            }
            return "";
        }
    }

    @Override
    public String getMenuNames(String menuIds) {
        Long[] menus = Convert.toLongArray(menuIds);
        StringBuilder sb = new StringBuilder();
        for (Long menu : menus) {
            Menu menuObj = menuMapper.selectById(menu);
            if (null != menuObj && StrUtil.isNotEmpty(menuObj.getName())) {
                sb.append(menuObj.getName()).append(",");
            }
        }
        return StrUtil.removeSuffix(sb.toString(), ",");
    }

    @Override
    public String getMenuName(Long menuId) {
        if (null == menuId) {
            return "";
        } else {
            Menu menu = menuMapper.selectById(menuId);
            if (menu == null) {
                return "";
            } else {
                return menu.getName();
            }
        }
    }

    @Override
    public Menu getMenuByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return new Menu();
        } else if (code.equals("0")) {
            return new Menu();
        } else {
            Menu param = new Menu();
            param.setCode(code);
            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>(param);
            Menu menu = menuMapper.selectOne(queryWrapper);
            if (menu == null) {
                return new Menu();
            } else {
                return menu;
            }
        }
    }

    @Override
    public String getMenuNameByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return "";
        } else if (code.equals("0")) {
            return "顶级";
        } else {
            Menu param = new Menu();
            param.setCode(code);
            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>(param);
            Menu menu = menuMapper.selectOne(queryWrapper);
            if (menu == null) {
                return "";
            } else {
                return menu.getName();
            }
        }
    }

    @Override
    public Long getMenuIdByCode(String code) {
        if (StrUtil.isEmpty(code)) {
            return 0L;
        } else if (code.equals("0")) {
            return 0L;
        } else {
            Menu menu = new Menu();
            menu.setCode(code);
            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>(menu);
            Menu tempMenu = this.menuMapper.selectOne(queryWrapper);
            return tempMenu.getMenuId();
        }
    }

	@Override
    public List<Dict> getAllDict() {
		List<Dict> dicts = CacheUtil.get("COMMON", "Dicts");
		if (null == dicts || dicts.isEmpty()) {
			dicts = dictMapper.selectList(null);
			CacheUtil.put("COMMON", "Dicts", dicts);
		}
		return dicts;
	}

    @Override
    public String getDictName(long dictId) {
		List<Dict> dicts = getAllDict();
		if (null != dicts && !dicts.isEmpty()) {
			for (Dict dict : dicts) {
				if (dictId == dict.getDictId().longValue()) {
					return dict.getName();
				}
			}
		}
        return "";
    }

	@Override
	public String getDictName(String parentCode, String code) {
		if (StrUtil.isBlank(code)) {
			return "";
		} else {
			List<Dict> dicts = getAllDict();
			if (null != dicts && !dicts.isEmpty()) {
				if (StrUtil.isBlank(parentCode)) {
					for (Dict dict : dicts) {
						if (code.equals(dict.getCode())) {
							return dict.getName();
						}
					}
				} else {
					for (Dict dict : dicts) {
						if (parentCode.equals(dict.getCode())) {
							for (Dict sub : dicts) {
								if (dict.getDictId().longValue() == sub.getPid().longValue()
										&& code.equals(sub.getCode())) {
									return sub.getName();
								}
							}
						}
					}
				}
			}
		}
		return "";
	}

    @Override
    public String getNoticeTitle(Long dictId) {
        if (null == dictId) {
            return "";
        } else {
            Notice notice = noticeMapper.selectById(dictId);
            if (notice == null) {
                return "";
            } else {
                return notice.getTitle();
            }
        }
    }

	/**
	 * 获取子字典
	 * @param code 字典编码
	 * @return
	 */
	@Override
	public List<Dict> getChildDicts(String parentCode, String code) {
		if (StrUtil.isBlank(code)) {
			return null;
		} else {
			List<Dict> dicts = getAllDict();
			if (null != dicts && !dicts.isEmpty()) {
				List<Dict> childs = new ArrayList<>(16);
				if (StrUtil.isBlank(parentCode)) {
					for (Dict dict : dicts) {
						if (code.equals(dict.getCode())) {
							for (Dict sub : dicts) {
								if (dict.getDictId().longValue() == sub.getPid().longValue()) {
									childs.add(sub);
								}
							}
							return childs;
						}
					}
				} else {
					for (Dict dict : dicts) {
						if (parentCode.equals(dict.getCode())) {
							for (Dict sub : dicts) {
								if (dict.getDictId().longValue() == sub.getPid().longValue()
										&& code.equals(sub.getCode())) {
									for (Dict child : dicts) {
										if (child.getPid().longValue() == sub.getDictId().longValue()) {
											childs.add(child);
										}
									}
									return childs;
								}
							}
						}
					}
				}
			}
			return null;
		}
	}

	/**
	 * 获取子字典
	 * @param id 字典标识
	 * @return
	 */
	@Override
	public List<Dict> getChildDicts(long id) {
		List<Dict> dicts = getAllDict();
		if (null != dicts && !dicts.isEmpty()) {
			List<Dict> childs = new ArrayList<>(16);
			for (Dict dict : dicts) {
				if (dict.getPid().longValue() == id) {
					childs.add(dict);
				}
			}
			return childs;
		}
		return null;
	}

	/**
	 * 获取关联字典
	 * @param id 主键值
	 * @return
	 */
	@Override
	public List<Dict> getRefDicts(long id) {
		List<Dict> dicts = getAllDict();
		if (null != dicts && !dicts.isEmpty()) {
			List<Dict> childs = new ArrayList<>(16);
			for (Dict dict : dicts) {
				if (null != dict.getRefId() && dict.getRefId().longValue() == id) {
					childs.add(dict);
				}
			}
			return childs;
		}
		return null;
	}

    @Override
    public String getSexName(String sexCode) {
        return getDictName("SEX", sexCode);
    }

    @Override
    public String getStatusName(String status) {
        return BizEnum.getMessage(status);
    }

    @Override
    public String getMenuStatusName(String status) {
        return BizEnum.getMessage(status);
    }

    @Override
    public List<Dict> findInDict(Long id) {
        if (null == id) {
            return null;
        } else {
            QueryWrapper<Dict> wrapper = new QueryWrapper<>();
            List<Dict> dicts = dictMapper.selectList(wrapper.eq("PID", id));
            if (dicts == null || dicts.size() == 0) {
                return null;
            } else {
                return dicts;
            }
        }
    }

    @Override
    public String getCacheObject(String para) {
        return LogObjectHolder.me().get().toString();
    }

    @Override
    public List<Long> getSubDeptId(Long deptId) {
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        wrapper = wrapper.like("PIDS", "%[" + deptId + "]%");
        List<Dept> depts = this.deptMapper.selectList(wrapper);

        ArrayList<Long> deptids = new ArrayList<>();

        if (depts != null && depts.size() > 0) {
            for (Dept dept : depts) {
                deptids.add(dept.getDeptId());
            }
        }

        return deptids;
    }

    @Override
    public List<Long> getParentDeptIds(Long deptId) {
        Dept dept = deptMapper.selectById(deptId);
        String pids = dept.getPids();
        String[] split = pids.split(",");
        ArrayList<Long> parentDeptIds = new ArrayList<>();
        for (String s : split) {
            parentDeptIds.add(Long.valueOf(StrUtil.removeSuffix(StrUtil.removePrefix(s, "["), "]")));
        }
        return parentDeptIds;
    }


}
