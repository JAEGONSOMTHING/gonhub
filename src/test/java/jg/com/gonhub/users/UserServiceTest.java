package jg.com.gonhub.users;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;



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
    void duplicateNameSignupTest(){
        User user= User.builder()
                .username("jaegon")
                .password("1234")
                .role(Role.USER).build();
        userRepository.save(user);

        SignupRequest signupRequest = new SignupRequest("jaegon", "1234");
        Long newUserId = userService.signUp(signupRequest);
        Assertions.assertThat(newUserId == null).isTrue();
    }
}