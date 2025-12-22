package com.ds.paynote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/**", "/register", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/user/login")       // 커스텀 로그인 페이지
                        .loginProcessingUrl("/user/login") // 로그인 폼 action과 같아야 함
                        .usernameParameter("userId") // 폼의 username name
                        .passwordParameter("userPw") // 폼의 password name
                        .defaultSuccessUrl("/") // 로그인 성공 후 이동
                        .failureUrl("/user/login?error") // 로그인 실패 시
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .logoutSuccessUrl("/user/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    /*
    * 계정 패스워드 암호화
    * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
