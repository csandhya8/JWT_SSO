package com.hellokoding.sso.auth;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JwtUtil {
    public static String generateToken(String signingKey, String subject) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        System.out.println("**** signingKey -->"+signingKey);
        System.out.println("**** subject -->"+subject);
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, signingKey);
        System.out.println("**** builder -->"+builder);
        return builder.compact();
    }

    public static String getSubject(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey){
        String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        System.out.println("**** token -->"+token);
        if(token == null) return null;
        String jwt = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
        System.out.println("**** jwt -->"+jwt);
        return jwt;
    }
}
