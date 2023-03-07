package study.springsecurity.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;

@RequiredArgsConstructor
@Component
public class JwtProvider {
    @Value("${jwt.secret.key}")
    private String salt;

    private Key secretKey;

    private final long expiredTime = 1000L * 60 * 60; //한시간

    private final JpaUserDetailService userDetailService;

    @PostConstruct
    protected void init() {
        secretKey = Keys
    }
}
