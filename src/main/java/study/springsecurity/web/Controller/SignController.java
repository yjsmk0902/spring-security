package study.springsecurity.web.Controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.springsecurity.domain.entity.dto.SignRequest;
import study.springsecurity.domain.entity.dto.SignResponse;
import study.springsecurity.domain.repository.MemberRepository;
import study.springsecurity.web.service.SignService;

@RestController
@RequiredArgsConstructor
public class SignController {
    private final MemberRepository memberRepository;
    private final SignService signService;

    @PostMapping("/login")
    public ResponseEntity<SignResponse> login(@RequestBody SignRequest request) {
        return new ResponseEntity<>(signService.login(request), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@RequestBody SignRequest request) throws Exception {
        return new ResponseEntity<>(signService.signup(request), HttpStatus.OK);
    }

    @GetMapping("/user/get")
    public ResponseEntity<SignResponse> getUser(@RequestParam String account) throws Exception {
        return new ResponseEntity<>(signService.getMember(account), HttpStatus.OK);
    }

    @GetMapping("/admin/get")
    public ResponseEntity<SignResponse> getUserForAdmin(@RequestParam String account) throws Exception {
        return new ResponseEntity<>(signService.getMember(account), HttpStatus.OK);
    }
}
