package com.deepspc.filtergate.modular.system.controller;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.deepspc.filtergate.core.common.node.MenuNode;
import com.deepspc.filtergate.core.jwt.JwtUtil;
import com.deepspc.filtergate.core.log.LogManager;
import com.deepspc.filtergate.core.log.factory.LogTaskFactory;
import com.deepspc.filtergate.core.reqres.response.ResponseData;
import com.deepspc.filtergate.core.shiro.ShiroKit;
import com.deepspc.filtergate.core.shiro.ShiroUser;
import com.deepspc.filtergate.modular.controller.BaseController;
import com.deepspc.filtergate.modular.system.model.UserDto;
import com.deepspc.filtergate.modular.system.service.UserService;
import com.deepspc.filtergate.utils.CacheUtil;
import com.deepspc.filtergate.utils.RSAUtil;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.deepspc.filtergate.utils.HttpContext.getIp;


/**
 * 登录控制器
 *
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 跳转到主页
     *
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        //获取当前用户角色列表
        ShiroUser user = ShiroKit.getUserNotNull();
        List<Long> roleList = user.getRoleList();

        if (roleList == null || roleList.size() == 0) {
            ShiroKit.getSubject().logout();
            model.addAttribute("tips", "该用户没有角色，无法登陆");
            model.addAttribute("publicKey", RSAUtil.getPublicKey());
            return "/login.html";
        }

        List<MenuNode> menus = userService.getUserMenuNodes(roleList);
        model.addAttribute("menus", menus);
        return "/index.html";
    }

    /**
     * 跳转到登录页面
     *
     */
	@GetMapping("/login")
    public String login(Model model) {
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return REDIRECT + "/";
        } else {
			model.addAttribute("publicKey", RSAUtil.getPublicKey());
            return "/login.html";
        }
    }

    /**
     * 点击登录执行的动作
     *
     */
	@PostMapping("/login")
	@ResponseBody
    public ResponseData loginVali(String username, String password) {
		ResponseData resp = checkUserValid(username, password);
		if (200 == resp.getCode().intValue()) {
			//登录成功，记录登录日志
			ShiroUser shiroUser = ShiroKit.getUserNotNull();
			LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));
			ShiroKit.getSession().setAttribute("sessionFlag", true);
		}

        return resp;
    }

	@PostMapping("/rpclogin")
	@ResponseBody
	public ResponseData rpclogin(@RequestBody UserDto userDto) {
		ResponseData resp = null;
		String account = userDto.getAccount();
		String password = userDto.getPassword();
		if (StrUtil.isNotBlank(account) && StrUtil.isNotBlank(password)) {
			resp = checkUserValid(account, password);
			if (200 == resp.getCode().intValue()) {
				ShiroUser shiroUser = ShiroKit.getUserNotNull();
				Long userId = shiroUser.getId();
				String token = JwtUtil.createToken(userId.longValue());
				Map<String, String> map = new HashMap<>();
				map.put("userName", shiroUser.getName());
				map.put("token", token);
				CacheUtil.put(CacheUtil.JWT, userId, token);
				resp.setData(map);
			}
		} else {
			resp = new ResponseData();
			resp.setCode(202);
			resp.setSuccess(false);
			resp.setMessage("用户名和密码不能为空");
		}
		return resp;
	}

	private ResponseData checkUserValid(String username, String password) {
		ResponseData resp = new ResponseData();
		resp.setCode(200);
		resp.setSuccess(true);
		try {
			username = RSAUtil.decodePrivate(username);
			password = RSAUtil.decodePrivate(password);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException
				| InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
			resp.setCode(201);
			resp.setMessage("解密异常");
			resp.setSuccess(false);
			e.printStackTrace();
			return resp;
		}
		Subject currentUser = ShiroKit.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());

		try {
			//执行shiro登录操作
			currentUser.login(token);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setCode(401);
			resp.setSuccess(false);
			return resp;
		}

		return resp;
	}

    /**
     * 退出登录
     *
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUserNotNull().getId(), getIp()));
        ShiroKit.getSubject().logout();
        deleteAllCookie();

        return REDIRECT + "/login";
    }

	@PostMapping("/rpclogout")
	@ResponseBody
    public ResponseData rpclogout() {
		ResponseData resp = new ResponseData();
		resp.setCode(200);
		resp.setSuccess(true);
		String token = this.getHttpServletRequest().getHeader("authorization");
		try {
			Map<String, Claim> claimMap = JwtUtil.verifyToken(token);
			Claim claim = claimMap.get("id");
			CacheUtil.remove(CacheUtil.JWT, claim.asString());
		} catch (JWTVerificationException e) {

		}
		return resp;
	}
}
