package com.ssafy.booxby.service;

import com.ssafy.booxby.domain.user.User;
import com.ssafy.booxby.domain.user.UserRepository;
import com.ssafy.booxby.handler.EmailHandler;
import com.ssafy.booxby.web.dto.UserDto;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final String signature = "LOGINTOKEN";
    private final Long expireMin = 30L;

    private final UserRepository userRepository;

    private final JavaMailSenderImpl mailSender;
    private boolean lowerCheck;
    private int size;

    /*
    로그인 토큰 부분
     */
    //로그인 성공 시 사용자 정보 기반으로 JWTToken 생성하여 반환
    public String createToken(User user) {
        JwtBuilder jwtBuilder = Jwts.builder();
        //Header 설정
        jwtBuilder.setHeaderParam("typ", "JWT");    //토큰 타입으로 고정값
        //Payload 설정
        jwtBuilder
                .setSubject("로그인 토큰")   //토큰 제목
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin));    //유효기간
        //signature 설정
        jwtBuilder.signWith(SignatureAlgorithm.HS256, signature.getBytes());

        //마지막 직렬화 처리
        String jwt = jwtBuilder.compact();
        return jwt;
    }

    public void checkValid(String jwt) {
        Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
    }

    public Map<String, Object> getToken(String jwt) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
        } catch (final Exception e) {
            throw new RuntimeException();
        }

        return claims.getBody();
    }

    /*
    유저 부분
     */
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
                .worldcupScore(request.getWorldcupScore())
                .hashScore(request.getHashScore())
                .profilePath(request.getProfilePath())
                .build();
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findEmailUser(UserDto.checkEmailRequest request) {
        return userRepository.findByEmail(request.getEmail());
    }

    @Transactional(readOnly = true)
    public User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }

    @Transactional
    public void updateUser(Long userId, UserDto.updateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
        user.updateUser(request);
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    public User findUserByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }

    @Transactional
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String getKey(int size, boolean lowerCheck) {
        this.size = size;
        this.lowerCheck = lowerCheck;
        return makeAuthKey();
    }

    //난수 생성
    private String makeAuthKey() {
        Random ran = new Random();
        StringBuffer sb = new StringBuffer();
        int num = 0;
        do {
            num = ran.nextInt(75) + 48;
            if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
                sb.append((char) num);
            } else {
                continue;
            }
        } while (sb.length() < size);
        if (lowerCheck) {
            return sb.toString().toLowerCase();
        }
        return sb.toString();
    }


    @Transactional
    public void sendTempPwEmail(User user) {
        String tempKey = getKey(10, true);

        try{
            EmailHandler mailHandler = new EmailHandler(mailSender);
            mailHandler.setTo(user.getEmail());
            mailHandler.setSubject("[Booxby] 북스비 임시비밀번호 안내");
            String message = makeContent("📚 북스비 임시비밀번호 발급",user.getNickname(), tempKey);
            mailHandler.setText(message, true);
            mailHandler.send();

            user.updatePassword(tempKey);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 이메일 내용 생성해서 전송
     */
    private String makeContent(String title, String nickname, String key) {
        StringBuffer sb = new StringBuffer();

        sb.append(
                "<!DOCTYPE html><html><body>"
                        + "<div style=\"font-family: 'Apple SD Gothic Neo' , 'sans-serif'; width:600px; height:600px; border-top:5px solid #00bfa5; margin:10px auto; padding:30px 0; box-sizing:border-box;\">"
                        + "<h1 style=\"marginx:0; padding:0 5px; font-size:28px; font-weight:400;\">"
                        + "<span style=\"font-size:15px; margin: 0 0 10px 3px;\">[Booxby]</span><br />"
                        + "<span style=\"color:00bfa5\">"
                        + title
                        + " 안내입니다.</h1>"
                        + "<p style=\"font-size:16px; line-height:26px; margin-top:50px; padding:0 5px;\">"
                        +  nickname
                        + " 님 안녕하세요. <br />"
        );

        sb.append(
                "임시 비밀번호를 발급해 드립니다. <br />"
                        + "아래 <b style=\"color:#00bfa5\"> '임시비밀번호'</b>로 로그인하여 비밀번호 변경을 진행해 주세요.<br />"
                        + "감사합니다😀</p>"
        );

        sb.append(
                "<p style=\"display:inline-block; width:210px; height:45px; margin:30px auto; background: #00bfa5; vertical-align:middle; font-size:25px; text-align:center; color:#FFFFFF\">"
                        + key
                        + "</p>"

                        + "<div style=\"border-top:1px solid #DDD; padding:5px;\"></div>"
                        + "</div>"
                        + "</body></html>"
        );

        return sb.toString();
    }

}
