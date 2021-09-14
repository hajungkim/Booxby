package com.ssafy.booxby.web;

import com.ssafy.booxby.domain.user.User;
import com.ssafy.booxby.service.UserService;
import com.ssafy.booxby.web.dto.UserDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "로그인", notes = "로그인 성공 시 (token, userId) 반환 / 회원정보가 없을 경우 false 반환", response = ControllerResponse.class)
    @PostMapping("/login")
    public ControllerResponse login(@RequestBody UserDto.loginRequest request) {
        System.out.println("ok");
        ControllerResponse response = null;
        try {
            User loginUser = userService.login(request);
            if (loginUser == null) {
                response = new ControllerResponse("success", false);
            } else {
                String token = userService.create(loginUser);
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
            userService.createUser(request);
            response = new ControllerResponse("success", "회원가입 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }
}
