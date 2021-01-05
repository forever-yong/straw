package cn.tedu.straw.api.user.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static String encode(String rawPassword){

        return "{bcrypt}"+passwordEncoder.encode(rawPassword);
    }
}
