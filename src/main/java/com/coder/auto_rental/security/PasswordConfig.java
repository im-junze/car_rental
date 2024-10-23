package com.coder.auto_rental.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
@Data
public class PasswordConfig  {
    @Value("${encoder.ctype.strength}")
    private int strength=6;
    @Value("${encoder.ctype.secret}")
    private String secret="secret";
   @Bean
    public   BCryptPasswordEncoder passwordEncoder(){
 //        随机生成secret生成一个SecureRandom实例，增加加密过程随机想
        SecureRandom secureRandom = new SecureRandom(secret.getBytes());
        return new BCryptPasswordEncoder(strength,secureRandom);
//       return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new PasswordConfig().passwordEncoder();
        String encode = passwordEncoder.encode("12");
        System.out.println(encode);
    }


}
