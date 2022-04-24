package jg.com.gonhub.users;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    @GetMapping("/")
    public String hello(@CurrentUser User user){
        String username = user == null? "anonymous" : user.getUsername();

        return "hello" + username;
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
