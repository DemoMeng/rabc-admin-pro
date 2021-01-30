package com.hlw.manage.core.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author mqz
 * @description jwt工具
 * @since 2020/4/24
 */
@Component
public class JwtTools{

    private static String secret;

    @Value("${my.jwt.secret}")
    public void setSecret(String secret){
        JwtTools.secret = secret;
    }

    /**
     * 生成jwt
     * @param uuid
     * @param userId
     * @param subject
     * @return
     */
    public static String getJwt(String uuid,String userId,String subject){
        //签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //密钥签名
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                            .setId(uuid)
                            .setIssuedAt(new Date())
                            .setIssuer(userId)
                            .setSubject(subject)
                            .signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

    /**
     * 解析jwt
     * @param jwt
     * @return
     */
    public static Claims toJwt(String jwt){
        Claims claims = Jwts.parser()
                        .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                        .parseClaimsJws(jwt).getBody();
        return claims;
    }












}
