package com.deepspc.filtergate.core.jwt;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.deepspc.filtergate.utils.CacheUtil;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Description JWT过滤器
 * @Author didoguan
 * @Date 2020/4/6
 **/
public class JwtFilter extends BasicHttpAuthenticationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		response.setCharacterEncoding("UTF-8");
		//获取请求头中的authorization
		final String token = request.getHeader("authorization");
		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			if (StrUtil.isBlank(token)) {
				return false;
			} else {
				try {
					Map<String, Claim> claimMap = JwtUtil.verifyToken(token);
					Claim claim = claimMap.get("id");
					long id = claim.asLong().longValue();
					request.setAttribute("tokenUserId", id);
					String cacheToken = CacheUtil.get(CacheUtil.JWT, "rpc_" + id);
					if (StrUtil.isBlank(cacheToken)) {
						return false;
					}
				} catch (JWTVerificationException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}
}
