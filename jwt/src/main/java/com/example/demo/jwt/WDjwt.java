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
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WDjwt {
	
	private static final String SALT = "JohnnySecretabcdefghijklmnopzxczxvzxvczbvzxvczxczxvzvxczxczxzxczxczxczxczxczxczxczxczxczxcc";
	
	private WDjwt() {}
	
	/**
	 * JWT 토큰 생성
	 * @author Johnny
	 * @param key 토큰 키 명
	 * @param data 토큰 키 내용
	 * @param subject 토큰 제목
	 * @return
	 * header : {
	 * <pre>
	 * Auth: 인증 헤더
	 * type: 타입
	 * createDate: 토큰 생성일
	 * alg: 알고리즘 타입
	 * </pre>
	 * }, 
	 * body : {
	 * <pre>
	 * iss: 발급자
	 * sub: 토큰 제목
	 * exp: 토큰 만료일
	 * key: data
	 * </pre>
	 * }, 
	 * signature : 생성된 키
	 */
	public static String createToken(String key, String data, String subject) {
		return Jwts.builder()
				.setHeaderParam("Auth", "JohnnyAuth") // Header 영역
				.setHeaderParam("type", "JWT")
				.setHeaderParam("createDate", System.currentTimeMillis()) // Header 영역 끝
				.setIssuer("Johnny") // Body 영역
				.setSubject(subject)
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.claim(key,  data) // Body 영역 끝
				.signWith(generateKey(), SignatureAlgorithm.HS512) // Signature 영역
				.compact();
	}
	
	/**
	 * 토큰 유효성 검사
	 * @author Johnny
	 * @param token 발급한 토큰 값
	 * @return boolean
	 */
	public static boolean isUsable(String token) {
		try 
		{
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey(generateKey())
					.parseClaimsJws(token);
			
			log.info(">>>>> isUsable :: claims : " + claims);
			return claims == null ? false : true;
		}
		catch (JwtException e) 
		{
			log.info("" + e);
			return false;
		}
	}
	
	/**
	 * 랜덤 키 생성하기
	 * @author Johnny
	 * @return HMAC-SHA 알고리즘으로 암호화 된 생성 키
	 */
	private static Key generateKey() {
		byte[] key = Decoders.BASE64.decode(SALT);
		return Keys.hmacShaKeyFor(key);
	}
}
