package jg.com.gonhub.users;

import io.jsonwebtoken.Jwt;
import jg.com.gonhub.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService  {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;



    public Long signUp(SignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return null;
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();

        return userRepository.save(user).getId();
    }

    public String login(LoginRequest request){
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new IllegalArgumentException("잘못된 아이디입니다"));
        log.info("pw = {}", request.getPassword());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
        List<String> role = new ArrayList<>();
        role.add(user.getRole().getAuthority());
        return jwtTokenProvider.createToken(user.getUsername(), role);

    }


}
