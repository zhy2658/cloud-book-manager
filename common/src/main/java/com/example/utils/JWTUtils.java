package com.example.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.entity.AuthTuser;
import com.example.entity.Tuser;
import org.springframework.util.StringUtils;

import java.util.Calendar;

public class JWTUtils {

    /**
     * 获取token
     * @param u user
     * @return token
     */
    final static String TOKEN_SECRET="12345654894891321131561";
    public static String getToken(Tuser u) {
        Calendar instance = Calendar.getInstance();
        //默认令牌过期时间7天
        instance.add(Calendar.DATE, 2);

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("userId", u.getId().toString())
                .withClaim("username", u.getUname())
                .withClaim("role",u.getRole());

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(TOKEN_SECRET));
    }

    /**
     * 验证token合法性 成功返回token
     */
    public static DecodedJWT verify(String token) throws RuntimeException {
        if(StringUtils.isEmpty(token)){
            throw new RuntimeException("token不能为空");
        }

        JWTVerifier build = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
        return build.verify(token);
    }

    public static AuthTuser getUser(DecodedJWT decodedJWT){
        AuthTuser tuser= new AuthTuser();
        tuser.setId(Integer.parseInt(decodedJWT.getClaim("userId").asString()));
        tuser.setUname(decodedJWT.getClaim("username").asString());
        tuser.setRole(decodedJWT.getClaim("role").asString());
        return tuser;
    }

}
