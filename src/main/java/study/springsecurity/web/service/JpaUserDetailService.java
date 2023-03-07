package study.springsecurity.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import study.springsecurity.domain.entity.Member;
import study.springsecurity.domain.repository.MemberRepository;
import study.springsecurity.web.security.CustomUserDetails;

@Service
@RequiredArgsConstructor
public class JpaUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByAccount(username).orElseThrow(
                () -> new UsernameNotFoundException("Invalid authentication!!!")
        );
        return new CustomUserDetails(member);
    }
}
