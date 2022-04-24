package jg.com.gonhub.users;

import jg.com.gonhub.security.JwtTokenProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @Test
    @DisplayName("회원 가입 테스트")
    void signUp() {
        SignupRequest signupRequest = new SignupRequest("jaegon", "1234");
        Long id = userService.signUp(signupRequest);

        User user = userRepository.findById(id).orElse(null);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getUsername()).isEqualTo(signupRequest.getUsername());
        Assertions.assertThat(user.getRole()).isEqualTo(Role.USER);
        Assertions.assertThat(user.getPassword()).isNotEqualTo(("1234"));
    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    void duplicateNameSignupTest() {
        User user = User.builder()
                .username("jaegon")
                .password("1234")
                .role(Role.USER).build();
        userRepository.save(user);

        SignupRequest signupRequest = new SignupRequest("jaegon", "1234");
        Long newUserId = userService.signUp(signupRequest);
        Assertions.assertThat(newUserId == null).isTrue();
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    void loginTest() {
        SignupRequest signupRequest = new SignupRequest("jaegon", "1234");
        userService.signUp(signupRequest);


        String token = userService.login(new LoginRequest("jaegon", "1234"));

        Assertions.assertThat(jwtTokenProvider.validateToken(token)).isTrue();

    }

    @Test
    @DisplayName("로그인 실패 테스트")
    void failLoginTest() {
        SignupRequest signupRequest = new SignupRequest("jaegon", "1234");
        userService.signUp(signupRequest);
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,()->{
            userService.login(new LoginRequest("jaegon","1233"));
        });

        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,()->{
            userService.login(new LoginRequest("jaego","1234"));
        });

    }

}