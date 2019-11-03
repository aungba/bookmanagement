package com.bookmanagement.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * This is JwtTokenUtil class
 * 
 * @author Aung Ba
 *
 */
@Component
public class JwtTokenUtil implements Serializable {

	/**
	 * generated serial version uid
	 */
	private static final long serialVersionUID = -4569924747869510659L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt-secret}")
	private String secret;

	/**
	 * This method is used to get username from token
	 * 
	 * @param token token
	 * @return username
	 */
	public String getUsernameFromToken(String token) {
		return getAllClaimsFromToken(token).getSubject();
	}

	/**
	 * This method is used to get the expired date from token
	 * 
	 * @param token token
	 * @return expired date
	 */
	public Date getExpirationDateFromToken(String token) {
		return getAllClaimsFromToken(token).getExpiration();
	}

	/**
	 * This method is used to get claims from token
	 * 
	 * @param token token
	 * @return claims
	 */
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	/**
	 * This method is used to check the token is expired or not
	 * 
	 * @param token token
	 * @return true or false
	 */
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	/**
	 * This method is used to generate the token
	 * 
	 * @param userDetails userDetails
	 * @return token
	 */
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	/**
	 * This method is used to generate the token
	 * 
	 * @param claims claim object
	 * @param username username
	 * @return generated token
	 */
	private String doGenerateToken(Map<String, Object> claims, String username) {
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	/**
	 * This method is used to check the token is valid or not
	 * 
	 * @param token token
	 * @param userDetails userDetails
	 * @return true or false
	 */
	public Boolean validToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
