package com.example.security.config;

import com.example.security.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity  //Spring Security 활성화
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //WebSecurityConfigurerAdapter => WebSecurityConfig 에서 Spring Security 를 설정하기 위해 상속해야 하는 클래스

    private final UserService userService;

    @Override   //인증을 무시할 경로를 설정해 놓을 수 있음
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/h2-console/**"); //static 의 하위 폴더는 무조건 접근이 가능해야 하므로 인증을 무시해줌
    }

    @Override   //http 관련 인증 설정이 가능함
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()    //접근에 대한 인증 설정이 가능
                .antMatchers("/login", "/signup", "/user").permitAll()  //누구나 접근이 가능
                .antMatchers("/").hasRole("USER")          //특정 권한이 있는 사람만 접근 가능 => USER
                .antMatchers("/admin").hasRole("ADMIN")    //특정 권한이 있는 사람만 접근 가능 => ADMIN
                .anyRequest().authenticated()                         //종류에 상관없이 권한이 있으면 무조건 접근 가능

                .and()
                .formLogin()                //로그인에 관한 설정을 의미
                .loginPage("/login")    //로그인 페이지 링크
                .defaultSuccessUrl("/") //로그인 성공 후 리다이렉트 링크

                .and()
                .logout()                           //로그아웃에 관한 설정을 의미
                .logoutSuccessUrl("/login")     //로그아웃 성공 후 리다이렉트 링크
                .invalidateHttpSession(true);   //로그아웃 이후 세션 전체 삭제 여부
    }

    @Override   //로그인할 때 필요한 정보를 가져오는 곳
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService)        //유저 정보를 가져오는 서비스를 UserService 로 지정
                //userService 에서 UserDetailService 를 implements 해서 loadUserByUsername() 구현해야 함
                .passwordEncoder(new BCryptPasswordEncoder());  //패스워드 인코더는 빈으로 등록해놓은 passwordEncoder() 를 사용 (BCrypt)
    }
}
