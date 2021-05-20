package br.com.register.api.config;

public class SecurityConstants {
    static final String SECRET = "DevDojoFoda";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SIGN_UP_URL = "/user/sign-up";
    static final Long EXPIRATION_TIME = 86400000L;
}
