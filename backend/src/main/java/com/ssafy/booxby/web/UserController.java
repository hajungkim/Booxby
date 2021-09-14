package com.ssafy.booxby.web;

import com.ssafy.booxby.domain.user.User;
import com.ssafy.booxby.service.UserService;
import com.ssafy.booxby.web.dto.UserDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "로그인", notes = "로그인 성공 시 (token, userId) 반환 / 회원정보가 없을 경우 false 반환", response = ControllerResponse.class)
    @PostMapping("/login")
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
    @PostMapping("/signup")
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

    @ApiOperation(value = "id로 유저정보 가져오기", notes = "존재하는 유저면 정보 반환", response = ControllerResponse.class)
    @GetMapping("/{id}")
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

    @ApiOperation(value = "유저 정보 수정", notes = "일단 닉네임,패스워드,프로필,해시태그 해놓음", response = ControllerResponse.class)
    @PutMapping("/{id}")
    public ControllerResponse updateUser(@PathVariable("id") Long userId, @RequestBody UserDto.updateRequest request) {
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
    @DeleteMapping("/{id}")
    public ControllerResponse deleteUser(@PathVariable("id") Long userId) {
        ControllerResponse response = null;
        try {
            userService.deleteUser(userId);
            response = new ControllerResponse("success", "회원 수정 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }
}
