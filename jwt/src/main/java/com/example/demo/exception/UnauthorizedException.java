package com.example.demo.exception;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = -2238030302650813813L;
	
	public UnauthorizedException() {
		super("권한이 유효하지 않습니다.");
	}
}
