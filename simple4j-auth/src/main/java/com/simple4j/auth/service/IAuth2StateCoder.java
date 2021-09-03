package com.simple4j.auth.service;

public interface IAuth2StateCoder {
	String decode(String encodeState);
}
