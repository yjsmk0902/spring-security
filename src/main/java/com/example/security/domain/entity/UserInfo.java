package com.example.security.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo implements UserDetails {

    @Id
    @Column(name = "code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "auth")
    private String auth;

    @Builder
    public UserInfo(String email, String password, String auth) {
        this.email = email;
        this.password = password;
        this.auth = auth;
    }

    @Override   //사용자의 권한을 컬렉션 형태로 반환 / 단 클래스의 자료형은 GrantedAuthority 를 구현해야함
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override   //사용자의 password 를 반환
    public String getPassword() {
        return password;
    }

    @Override   //사용자의 id를 반환 (unique value)
    public String getUsername() {
        return email;
    }

    @Override   //계정 만료 여부 반환
    public boolean isAccountNonExpired() {
        //만료되었는지 확인
        return true;    //true => 만료 안됨
    }

    @Override   //계정 잠금 여부 반환
    public boolean isAccountNonLocked() {
        //잠금되어있는지 확인
        return true;    //true => 잠금 안됨
    }

    @Override   //패스워드 만료 여부 반환
    public boolean isCredentialsNonExpired() {
        //만료되었는지 확인
        return true;    //true => 만료 안됨
    }

    @Override   //계정 사용가능 여부 반환
    public boolean isEnabled() {
        //사용 가능한지 확인
        return true;    //true => 사용 가능
    }
}