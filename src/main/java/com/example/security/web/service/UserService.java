package com.example.security.web.service;

import com.example.security.domain.dto.UserDto;
import com.example.security.domain.entity.User;
import com.example.security.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    //Spring Security 필수 메소드 구현
    //  @Param email 이메일
    //  @return UserDetails
    //  @throws UsernameNotFoundException 유저가 없을 때 예외 발생

    @Override   //기본 반환 타입은 UserDetails, UserDetails 를 상속받은 User 로 반환타입 지정 (자동 다운 캐스팅)
    public User loadUserByUsername(String email) {  //Security 에서 지정한 서비스 -> 이 메소드를 필수적으로 구현해야함
        return userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(email));
    }

    public Long save(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        return userRepository.save(User.builder()
                .email(userDto.getEmail())
                .auth(userDto.getAuth())
                .password(userDto.getPassword()).build()).getCode();
    }
}
