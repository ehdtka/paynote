package com.ds.paynote.security;

import com.ds.paynote.domain.User;
import com.ds.paynote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId)
            throws UsernameNotFoundException {

        // DB에서 사용자 조회
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("존재하지 않는 사용자")
                );

        // Spring Security가 이해할 수 있는 UserId,Pw로 변환
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserId())   // 로그인 ID
                .password(user.getUserPw())       // 🔥 암호화된 비밀번호
                .roles("USER")                    // 권한 (ROLE_USER)
                .build();
    }

}
