package com.example.restdoc1_app.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRequest {

    @Data //다른 어노테이션을 다 포함하고있음
    public static class JoinDTO {

        @Size(min = 3, max = 20)
        @NotEmpty
        private String username;

        @Size(min = 4, max = 20)
        @NotEmpty
        private String password;

        @Size(min = 10, max = 20)
        @NotEmpty
        private String email;

        public User toEntity(){
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        }
    }

    @Data //다른 어노테이션을 다 포함하고있음
    public static class LoginDTO {

        @Size(min = 3, max = 20)
        @NotEmpty
        private String username;

        @Size(min = 4, max = 20)
        @NotEmpty
        private String password;

    }
}
