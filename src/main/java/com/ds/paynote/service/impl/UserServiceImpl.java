package com.ds.paynote.service.impl;

import com.ds.paynote.domain.User;
import com.ds.paynote.repository.UserRepository;
import com.ds.paynote.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {

        // 평문 비밀번호 BCrypt 암호화
        String encodedPw = passwordEncoder.encode(user.getUserPw());

        // 기존 User 객체에 암호화된 비밀번호 세팅
        user.setUserPw(encodedPw);

        // db에 데이터 저장
        userRepository.save(user);
    }
}
