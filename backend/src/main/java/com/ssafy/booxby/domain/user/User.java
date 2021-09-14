package com.ssafy.booxby.domain.user;

import com.ssafy.booxby.web.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;

    private String nickname;

    private String password;

    private int emotionScore;

    private String profilePath;

    private LocalDateTime createdDate;

    private int age;

    private int gender;

    private String hashtag;


    @Builder
    public User(String email, String nickname, String password, int age, int gender, String hashtag) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
        this.hashtag = hashtag;
        this.createdDate = LocalDateTime.now();
    }

    public void updateUser(UserDto.updateRequest request) {
        this.nickname = request.getNickname();
        this.password = request.getPassword();
        this.profilePath = request.getProfilePath();
        this.hashtag = request.getHashtag();
    }

}
