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

    private String profilePath;

    private LocalDateTime createdDate;

    private String age;

    private int gender;

    private String hashtag;

    private int worldcupScore;

    private int hashScore;


    @Builder
    public User(String email, String nickname, String password, String age, int gender, String hashtag, int worldcupScore, int hashScore) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
        this.hashtag = hashtag;
        this.createdDate = LocalDateTime.now();
        this.worldcupScore = worldcupScore;
        this.hashScore = hashScore;
    }

    public void updateUser(UserDto.updateUserRequest request) {
        this.nickname = request.getNickname();
        this.password = request.getPassword();
        this.profilePath = request.getProfilePath();
        this.hashtag = request.getHashtag();
    }

}
