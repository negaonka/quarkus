package com.quicktutorialz.learnmicroservices.FirstToDos.utilities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.algorithm.SignatureAlgorithm;
import io.smallrye.jwt.build.Jwt;
import io.vertx.core.http.HttpServerRequest;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * This class provides method in order to generate and validate JSON Web Tokens
 */
@ApplicationScoped
public class JwtUtils {

    /* USEFUL LINKS:
        https://stormpath.com/blog/jwt-java-create-verify
        https://stormpath.com/blog/beginners-guide-jwts-in-java
        https://github.com/jwtk/jjwt
    */


    public String generateJwt(String email, String name, Date date) throws java.io.UnsupportedEncodingException {
		return name;

		/*
		 * String jwt = Jwt.builder() .setSubject(email) .setExpiration(date)
		 * .claim("name", name) .signWith( SignatureAlgorithm.HS256,
		 * "myPersonalSecretKey12345".getBytes("UTF-8") ) .compact();
		 * 
		 * return jwt;
		 */
    }


    public Map<String, Object> jwt2Map(String jwt) throws java.io.UnsupportedEncodingException {

    	Map<String, Object> userData = new HashMap<>(); userData.put("email", jwt);
		/*
		 * Jws<Claims> claim = Jwts.parser()
		 * .setSigningKey("myPersonalSecretKey12345".getBytes("UTF-8"))
		 * .parseClaimsJws(jwt);
		 * 
		 * String name = claim.getBody().get("name", String.class);
		 * 
		 * Date expDate = claim.getBody().getExpiration(); String email =
		 * claim.getBody().getSubject();
		 * 
		 * Map<String, Object> userData = new HashMap<>(); userData.put("email", email);
		 * userData.put("name", name); userData.put("exp_date", expDate);
		 * 
		 * Date now = new Date(); if (now.after(expDate)) { throw new
		 * ExpiredJwtException(null, null, "Session expired!"); }
		 */

        return userData;
    }


    /**
     * this method extracts the jwt from the header or the cookie in the Http request
     *
     * @param request
     * @return jwt
     */
    public String getJwtFromHttpRequest(HttpServerRequest request) {
        String jwt = null;
        if (request.getHeader("jwt") != null) {
            jwt = request.getHeader("jwt");     //token present in header
        }
        return jwt;
    }

}

