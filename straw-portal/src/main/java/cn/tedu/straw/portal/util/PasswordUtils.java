package cn.tedu.straw.portal.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

public class PasswordUtils {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static String encode(String rawPassword){

        return "{bcrypt}"+passwordEncoder.encode(rawPassword);
    }
}
