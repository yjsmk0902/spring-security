package study.springsecurity.domain.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.springsecurity.domain.entity.Authority;
import study.springsecurity.domain.entity.Member;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignResponse {
    private Long id;
    private String account;
    private String username;
    private String name;
    private String email;
    private List<Authority> roles = new ArrayList<>();
    private String token;

    public SignResponse(Member member) {
        this.id = member.getId();
        this.account = member.getAccount();
        this.username = member.getUsername();
        this.name = member.getName();
        this.email = member.getEmail();
        this.roles = member.getRoles();
    }
}
