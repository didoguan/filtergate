package com.deepspc.filtergate.core.jwt;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.deepspc.filtergate.core.common.constant.cache.Cache;
import com.deepspc.filtergate.utils.CacheUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Description JWT过滤器
 * @Author didoguan
 * @Date 2020/4/6
 **/
@WebFilter(filterName = "JwtFilter", urlPatterns = "/rpc/**")
public class JwtFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;

		response.setCharacterEncoding("UTF-8");
		//获取请求头中的authorization
		final String token = request.getHeader("authorization");

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			filterChain.doFilter(request, response);
		} else {
			if (StrUtil.isBlank(token)) {
				response.getWriter().write("TokenRequire");
				return;
			} else {
				try {
					Map<String, Claim> claimMap = JwtUtil.verifyToken(token);
					Claim claim = claimMap.get("id");
					String id = claim.asString();
					request.setAttribute("tokenUserId", id);
					String cacheToken = CacheUtil.get(CacheUtil.JWT, id);
					if (StrUtil.isBlank(cacheToken)) {
						response.getWriter().write("TokenExpiration");
						return;
					}
				} catch (JWTVerificationException e) {
					response.getWriter().write("TokenInvalid");
					return;
				}
			}
		}
	}

	@Override
	public void destroy() {

	}
}
