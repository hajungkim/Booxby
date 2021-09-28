package com.ssafy.booxby.web.dto;

import lombok.*;

public class UserDto {

    @Getter
    @NoArgsConstructor
    public static class loginRequest {
        private String email;
        private String password;
    }

    @Getter
    @NoArgsConstructor
    public static class loginResponse {
        private String token;
        private Long userId;

        @Builder
        public loginResponse(String token, Long userId) {
            this.token = token;
            this.userId = userId;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class tokenResponse {
        private String token;
    }

    @Getter
    @NoArgsConstructor
    public static class signupRequest {
        private String email;
        private String nickname;
        private String password;
        private String age;
        private int gender;
        private String hashtag;
        private int worldcupScore;
        private int hashScore;
        private String profilePath;
    }

    @Getter
    @NoArgsConstructor
    public static class checkEmailRequest {
        private String email;
    }

    @Getter
    @NoArgsConstructor
    public static class updateUserRequest {
        private String nickname;
        private String password;
        private String hashtag;
        private int worldcupScore;
        private int hashScore;
    }

}
