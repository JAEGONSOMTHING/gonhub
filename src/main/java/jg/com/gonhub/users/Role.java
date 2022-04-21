package jg.com.gonhub.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    USER("USER", "유저"), ADMIN("ADMIN", "관리자");
    private final String authority;
    private final String description;

}
