package com.deepspc.filtergate.core.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWTVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description JWT公共类
 * @Author didoguan
 * @Date 2020/4/6
 **/
public class JwtUtil {

	private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

	private static final String SECRET = "iaf0tS1hkwy4140q";

	/**
	 * 过期时间，单位：秒
	 */
	private static final long EXPIRATION = 1800L;

	/**
	 * 创建Token
	 * @param userId 用户标识
	 * @return
	 */
	public static String createToken(Long userId) {
		Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
		Map<String, Object> map = new HashMap<>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");
		String token = JWT.create()
				.withHeader(map)
				.withClaim("id", userId)
				.withExpiresAt(expireDate)
				.withIssuedAt(new Date())
				.sign(Algorithm.HMAC256(SECRET));

		return token;
	}

	/**
	 * 获取jwt的Claim
	 * @param token 要验证的token
	 * @return
	 */
	public static Map<String, Claim> verifyToken(String token) throws JWTVerificationException {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
		DecodedJWT jwt = verifier.verify(token);

		return jwt.getClaims();
	}
}
