package com.ssafy.booxby.web;

import com.ssafy.booxby.domain.user.User;
import com.ssafy.booxby.service.UserService;
import com.ssafy.booxby.web.dto.EmailDto;
import com.ssafy.booxby.web.dto.UserDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "로그인", notes = "로그인 성공 시 (token, userId) 반환 / 회원정보가 없을 경우 false 반환", response = ControllerResponse.class)
    @PostMapping("/user/login")
    public ControllerResponse login(@RequestBody UserDto.loginRequest request) {
        ControllerResponse response = null;
        try {
            User loginUser = userService.login(request);
            if (loginUser == null) {
                response = new ControllerResponse("success", false);
            } else {
                String token = userService.createToken(loginUser);
                UserDto.loginResponse loginResponse = userService.createTokenUserId(token, loginUser.getUserId());
                response = new ControllerResponse("success", loginResponse);
            }
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "회원가입", notes = "회원가입 성공 시 '회원가입 성공' 반환", response = ControllerResponse.class)
    @PostMapping("/user/signup")
    public ControllerResponse signup(@RequestBody UserDto.signupRequest request) {
        ControllerResponse response = null;
        try {
            userService.saveUser(request);
            response = new ControllerResponse("success", "회원가입 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "이메일 중복 확인", notes = "이메일 사용가능하면 true, 중복이면 false", response = ControllerResponse.class)
    @PostMapping("/user/check")
    public ControllerResponse checkEmail(@RequestBody UserDto.checkEmailRequest request) {
        ControllerResponse response = null;
        try {
            User user = userService.findEmailUser(request);
            if (user == null) {
                response = new ControllerResponse("success", true);
            } else {
                response = new ControllerResponse("success", false);
            }
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "id로 유저정보 가져오기", notes = "존재하는 유저면 정보 반환", response = ControllerResponse.class)
    @GetMapping("/user/{id}")
    public ControllerResponse findUser(@PathVariable("id") Long userId) {
        ControllerResponse response = null;
        try {
            User user = userService.findUser(userId);
            response = new ControllerResponse("success", user);
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "유저 정보 수정", notes = "닉네임, 비밀번호, 해시태그 수정하기", response = ControllerResponse.class)
    @PutMapping("/user/{id}")
    public ControllerResponse updateUser(@PathVariable("id") Long userId, @RequestBody UserDto.updateUserRequest request) {
        ControllerResponse response = null;
        try {
            userService.updateUser(userId, request);
            response = new ControllerResponse("success", "회원 수정 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "유저 정보 삭제", notes = "", response = ControllerResponse.class)
    @DeleteMapping("/user/{id}")
    public ControllerResponse deleteUser(@PathVariable("id") Long userId) {
        ControllerResponse response = null;
        try {
            userService.deleteUser(userId);
            response = new ControllerResponse("success", "회원 삭제 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "임시 비밀번호 발급", notes = "임시비밀번호 발급 및 이메일 전송 성공시 '전송 성공' 반환 / 실패 시 에러메시지", response = ControllerResponse.class)
    @PutMapping("/changepw")
    public ControllerResponse sendTempPwEmail(@RequestBody EmailDto emailDto){
        ControllerResponse response = null;

        User user = userService.findUserByEmail(emailDto.getEmail());
        try{
            userService.sendTempPwEmail(user);
            response = new ControllerResponse("success", "전송 성공");
        }catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

}
