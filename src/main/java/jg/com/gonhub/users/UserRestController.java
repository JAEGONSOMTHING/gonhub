package jg.com.gonhub.users;

import jg.com.gonhub.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Deque;
import java.util.LinkedList;
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    @GetMapping("/")
    public String hello(@CurrentUser User user){

        return "hello";
    }

    @PostMapping("/auth/signup")
    public String signup(@RequestBody SignupRequest request){
        userService.signUp(request);

        return "ok";
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody LoginRequest request){

        return userService.login(request);

    }
}
