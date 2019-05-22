package com.itheima.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtil {
    private static BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public static String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String s = BCryptPasswordEncoderUtil.encodePassword("123");

        boolean matches = BCryptPasswordEncoderUtil.passwordEncoder.matches("123", "$2a$10$5Z2DtDAOKBktYHyIW8l0PuQ9GDsSDRHywUe5OSrW6mZ.uNbLabuuu");
        System.out.println(matches);
    }
}
