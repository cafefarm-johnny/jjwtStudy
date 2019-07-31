package com.example.demo.jwt;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class WDjwt {
	
	private static final String SALT = "JohnnySecretabcdefghijklmnopzxczxvzxvczbvzxvczxczxvzvxczxczxzxczxczxczxczxczxczxczxczxczxcc";
	
	private WDjwt() {}
	
	public static String createToken(String key, String data, String subject) {
		return Jwts.builder()
				.setHeaderParam("Auth", "auth")
				.setHeaderParam("type", "JWT")
				.setHeaderParam("createDate", System.currentTimeMillis())
				.setSubject(subject)
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.claim(key,  data)
				.signWith(generateKey(), SignatureAlgorithm.HS512)
				.compact();
	}
	
	public static boolean isUsable(String token) {
		try 
		{
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey(generateKey())
					.parseClaimsJws(token);
			return claims == null ? false : true;
		}
		catch (JwtException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	private static Key generateKey() {
		byte[] key = Decoders.BASE64.decode(SALT);
		return Keys.hmacShaKeyFor(key);
	}
}
