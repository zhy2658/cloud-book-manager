package com.example;

import static org.junit.Assert.assertTrue;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.entity.Tuser;
import com.example.utils.JWTUtils;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AuthApplicationTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

        Tuser tuser =new Tuser();
        tuser.setUname("yousa");
        tuser.setId(1);
        tuser.setRole("admin");
        String jwtcode= JWTUtils.getToken(tuser);
        System.out.println(jwtcode);
        DecodedJWT decodedJWT =JWTUtils.verify(jwtcode);

        System.out.println(JWTUtils.getUser(decodedJWT));
    }
}
