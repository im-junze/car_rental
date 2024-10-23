package com.coder.auto_rental.config;


import com.coder.auto_rental.security.*;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Resource
    private LoginSuccessHandler loginSucdessHandler;
    @Resource
    private LoginFailHandle loginFailHandler;
    @Resource
    private CustomAccessDeniedHandler customerAccessDeniedHandler;
    @Resource
    private CustomerAnonymousEntryPoint customerAnonymousEntryPoint;
    @Resource
    private CustomUserDetailsService customerUserDetailsService;
@Resource
private VerifyTokenFilter verificationTokenFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        登录前过滤
        http.addFilterBefore(verificationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.formLogin()
                .loginProcessingUrl("/auto_rental/user/login")
                .successHandler(loginSucdessHandler)
                .failureHandler(loginFailHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/auto_rental/user/login")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customerAnonymousEntryPoint)
                .accessDeniedHandler(customerAccessDeniedHandler)
                .and()
                .cors()
                .and()
                .csrf().disable()
                .userDetailsService(customerUserDetailsService);
        return http.build();
    }
}
