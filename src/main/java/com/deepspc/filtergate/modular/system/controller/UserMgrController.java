package com.deepspc.filtergate.modular.system.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deepspc.filtergate.config.properties.FiltergateProperties;
import com.deepspc.filtergate.core.common.annotion.BussinessLog;
import com.deepspc.filtergate.core.common.annotion.Permission;
import com.deepspc.filtergate.core.common.constant.Const;
import com.deepspc.filtergate.core.common.constant.dictmap.UserDict;
import com.deepspc.filtergate.core.common.constant.factory.ConstantFactory;
import com.deepspc.filtergate.core.common.page.LayuiPageFactory;
import com.deepspc.filtergate.core.datascope.DataScope;
import com.deepspc.filtergate.core.enums.BizEnum;
import com.deepspc.filtergate.core.exception.BizExceptionEnum;
import com.deepspc.filtergate.core.exception.ServiceException;
import com.deepspc.filtergate.core.log.LogObjectHolder;
import com.deepspc.filtergate.core.reqres.response.ResponseData;
import com.deepspc.filtergate.core.shiro.ShiroKit;
import com.deepspc.filtergate.modular.controller.BaseController;
import com.deepspc.filtergate.modular.system.entity.User;
import com.deepspc.filtergate.modular.system.factory.UserFactory;
import com.deepspc.filtergate.modular.system.model.UserDto;
import com.deepspc.filtergate.modular.system.service.UserService;
import com.deepspc.filtergate.modular.system.warpper.UserWrapper;
import com.deepspc.filtergate.utils.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 系统管理员控制器
 *
 */
@Controller
@RequestMapping("/mgr")
public class UserMgrController extends BaseController {

    private static String PREFIX = "/modular/system/user/";

    @Autowired
    private FiltergateProperties filtergateProperties;

    @Autowired
    private UserService userService;

    /**
     * 跳转到查看管理员列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "user.html";
    }

    /**
     * 跳转到查看管理员列表的页面
     *
     */
    @RequestMapping("/user_add")
    public String addView() {
        return PREFIX + "user_add.html";
    }

    /**
     * 跳转到角色分配页面
     *
     */
    @Permission
    @RequestMapping("/role_assign")
    public String roleAssign(@RequestParam Long userId, Model model) {
        if (null == userId) {
            throw new ServiceException(BizExceptionEnum.FIELD_UNAVAIL.getCode(),
                                        BizExceptionEnum.FIELD_UNAVAIL.getMessage());
        }
        model.addAttribute("userId", userId);
        return PREFIX + "user_roleassign.html";
    }

    /**
     * 跳转到编辑管理员页面
     *
     */
    @Permission
    @RequestMapping("/user_edit")
    public String userEdit(@RequestParam Long userId) {
        if (null == userId) {
            throw new ServiceException(BizExceptionEnum.FIELD_UNAVAIL.getCode(),
                                        BizExceptionEnum.FIELD_UNAVAIL.getMessage());
        }
        User user = this.userService.getById(userId);
        LogObjectHolder.me().set(user);
        return PREFIX + "user_edit.html";
    }

    /**
     * 获取用户详情
     *
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Object getUserInfo(@RequestParam Long userId) {
        if (null == userId) {
            throw new ServiceException(BizExceptionEnum.FIELD_UNAVAIL.getCode(),
                                        BizExceptionEnum.FIELD_UNAVAIL.getMessage());
        }

        this.userService.assertAuth(userId);
        User user = this.userService.getById(userId);
        Map<String, Object> map = UserFactory.removeUnSafeFields(user);

        HashMap<Object, Object> hashMap = CollectionUtil.newHashMap();
        hashMap.putAll(map);
        hashMap.put("roleName", ConstantFactory.me().getRoleName(user.getRoleId()));
        hashMap.put("deptName", ConstantFactory.me().getDeptName(user.getDeptId()));

        return ResponseData.success(hashMap);
    }

    /**
     * 修改当前用户的密码
     *
     */
    @RequestMapping("/changePwd")
    @ResponseBody
    public Object changePwd(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        if (StrUtil.isBlank(oldPassword) || StrUtil.isBlank(newPassword)) {
            throw new ServiceException(BizExceptionEnum.FIELD_UNAVAIL.getCode(),
                                        BizExceptionEnum.FIELD_UNAVAIL.getMessage());
        }
        this.userService.changePwd(oldPassword, newPassword);
        return SUCCESS_TIP;
    }

    /**
     * 查询管理员列表
     *
     */
    @RequestMapping("/list")
    @Permission
    @ResponseBody
    public Object list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) String timeLimit,
                       @RequestParam(required = false) Long deptId) {

        //拼接查询条件
        String beginTime = "";
        String endTime = "";

        if (StrUtil.isNotEmpty(timeLimit)) {
            String[] split = timeLimit.split(" - ");
            beginTime = split[0];
            endTime = split[1];
        }

        if (ShiroKit.isAdmin()) {
            Page<Map<String, Object>> users = userService.selectUsers(null, name, beginTime, endTime, deptId);
            Page wrapped = new UserWrapper(users).wrap();
            return LayuiPageFactory.createPageInfo(wrapped);
        } else {
            DataScope dataScope = new DataScope(ShiroKit.getDeptDataScope());
            Page<Map<String, Object>> users = userService.selectUsers(dataScope, name, beginTime, endTime, deptId);
            Page wrapped = new UserWrapper(users).wrap();
            return LayuiPageFactory.createPageInfo(wrapped);
        }
    }

    /**
     * 添加管理员
     *
     */
    @RequestMapping("/add")
    @BussinessLog(value = "添加管理员", key = "account", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData add(@Valid UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.SERVER_ERROR.getCode(),
                                        BizExceptionEnum.SERVER_ERROR.getMessage());
        }
        this.userService.addUser(user);
        return SUCCESS_TIP;
    }

    /**
     * 修改管理员
     *
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "修改管理员", key = "account", dict = UserDict.class)
    @ResponseBody
    public ResponseData edit(@Valid UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            throw new ServiceException(BizExceptionEnum.SERVER_ERROR.getCode(),
                                        BizExceptionEnum.SERVER_ERROR.getMessage());
        }
        this.userService.editUser(user);
        return SUCCESS_TIP;
    }

    /**
     * 删除管理员（逻辑删除）
     *
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "删除管理员", key = "userId", dict = UserDict.class)
    @Permission
    @ResponseBody
    public ResponseData delete(@RequestParam Long userId) {
        if (null == userId) {
            throw new ServiceException(BizExceptionEnum.FIELD_UNAVAIL.getCode(),
                                        BizExceptionEnum.FIELD_UNAVAIL.getMessage());
        }
        this.userService.deleteUser(userId);
        return SUCCESS_TIP;
    }

    /**
     * 查看管理员详情
     *
     */
    @RequestMapping("/view/{userId}")
    @ResponseBody
    public User view(@PathVariable Long userId) {
        if (null == userId) {
            throw new ServiceException(BizExceptionEnum.FIELD_UNAVAIL.getCode(),
                                        BizExceptionEnum.FIELD_UNAVAIL.getMessage());
        }
        this.userService.assertAuth(userId);
        return this.userService.getById(userId);
    }

    /**
     * 重置管理员的密码
     *
     */
    @RequestMapping("/reset")
    @BussinessLog(value = "重置管理员密码", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData reset(@RequestParam Long userId) {
        if (null == userId) {
            throw new ServiceException(BizExceptionEnum.FIELD_UNAVAIL.getCode(),
                                        BizExceptionEnum.FIELD_UNAVAIL.getMessage());
        }
        this.userService.assertAuth(userId);
        User user = this.userService.getById(userId);
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(Const.DEFAULT_PWD, user.getSalt()));
        this.userService.updateById(user);
        return SUCCESS_TIP;
    }

    /**
     * 冻结用户
     *
     */
    @RequestMapping("/freeze")
    @BussinessLog(value = "冻结用户", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData freeze(@RequestParam Long userId) {
        if (null == userId) {
            throw new ServiceException(BizExceptionEnum.FIELD_UNAVAIL.getCode(),
                                        BizExceptionEnum.FIELD_UNAVAIL.getMessage());
        }
        //不能冻结超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new ServiceException(BizExceptionEnum.CANT_FREEZE_ADMIN.getCode(),
                                        BizExceptionEnum.CANT_FREEZE_ADMIN.getMessage());
        }
        this.userService.assertAuth(userId);
        this.userService.setStatus(userId, BizEnum.FREEZED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 解除冻结用户
     *
     */
    @RequestMapping("/unfreeze")
    @BussinessLog(value = "解除冻结用户", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData unfreeze(@RequestParam Long userId) {
        if (null == userId) {
            throw new ServiceException(BizExceptionEnum.FIELD_UNAVAIL.getCode(),
                                        BizExceptionEnum.FIELD_UNAVAIL.getMessage());
        }
        this.userService.assertAuth(userId);
        this.userService.setStatus(userId, BizEnum.ENABLE.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 分配角色
     *
     */
    @RequestMapping("/setRole")
    @BussinessLog(value = "分配角色", key = "userId,roleIds", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData setRole(@RequestParam("userId") Long userId, @RequestParam("roleIds") String roleIds) {
        if (null == userId || StrUtil.isBlank(roleIds)) {
            throw new ServiceException(BizExceptionEnum.FIELD_UNAVAIL.getCode(),
                                        BizExceptionEnum.FIELD_UNAVAIL.getMessage());
        }
        //不能修改超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new ServiceException(BizExceptionEnum.CANT_CHANGE_ADMIN.getCode(),
                                        BizExceptionEnum.CANT_CHANGE_ADMIN.getMessage());
        }
        this.userService.assertAuth(userId);
        this.userService.setRoles(userId, roleIds);
        return SUCCESS_TIP;
    }

    /**
     * 上传图片
     *
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture) throws IOException, IllegalStateException {

        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        try {
            String fileSavePath = filtergateProperties.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (IOException | IllegalStateException e) {
            throw e;
        }
        return pictureName;
    }
}
