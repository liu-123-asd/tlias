package com.itheima.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    // 更换符合256bit长度的Base64密钥（安全标准）
    private static final String BASE64_SIGN_KEY = "Zm9vYmFyZm9vYmFyZm9vYmFyZm9vYmFyZm9vYmFyZm9vYmFyZm9vYmFy";
    private static final Long expire = 43200000L; // 12小时

    // 将Base64字符串转为标准SecretKey
    private static SecretKey getSecretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(BASE64_SIGN_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 生成JWT令牌
     */
    public static String generateJwt(Map<String, Object> claims) {
        SecretKey key = getSecretKey();
        return Jwts.builder()
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
    }

    /**
     * 解析JWT令牌
     */
    public static Claims parseJWT(String jwt) {
        SecretKey key = getSecretKey();
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
}