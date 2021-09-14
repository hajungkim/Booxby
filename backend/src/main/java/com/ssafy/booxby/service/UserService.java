package com.ssafy.booxby.service;

import com.ssafy.booxby.domain.user.User;
import com.ssafy.booxby.domain.user.UserRepository;
import com.ssafy.booxby.web.dto.UserDto;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private String signature = "LOGINTOKEN";
    private Long expireMin = 30L;

    private final UserRepository userRepository;

    //로그인 성공 시 사용자 정보 기반으로 JWTToken 생성하여 반환
    public String create(User user) {
        JwtBuilder jwtBuilder = Jwts.builder();
        //Header 설정
        jwtBuilder.setHeaderParam("typ", "JWT");    //토큰 타입으로 고정값
        //Payload 설정
        jwtBuilder
                .setSubject("로그인 토큰")   //토큰 제목
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))    //유효기간
                .claim("user", user).claim("greeting", "환영합니다 " + user.getNickname());  //담을 정보
        //signature 설정
        jwtBuilder.signWith(SignatureAlgorithm.HS256, signature.getBytes());

        //마지막 직렬화 처리
        String jwt = jwtBuilder.compact();
        return jwt;
    }

    public void checkValid(String jwt) {
        Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
    }

    public Map<String, Object> get(String jwt) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
        } catch (final Exception e) {
            throw new RuntimeException();
        }

        return claims.getBody();
    }

    @Transactional
    public User login(UserDto.loginRequest request) {
        return userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
    }

    @Transactional
    public UserDto.loginResponse createTokenUserId(String token, Long userId) {
        UserDto.loginResponse response = UserDto.loginResponse.builder()
                .token(token).userId(userId).build();
        return response;
    }

    @Transactional
    public void saveUser(UserDto.signupRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .age(request.getAge())
                .gender(request.getGender())
                .hashtag(request.getHashtag())
                .build();
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }

    @Transactional
    public void updateUser(Long userId, UserDto.updateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
        user.updateUser(request);
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
