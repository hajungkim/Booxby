package com.ssafy.booxby.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerResponse<T> {

    private String status;  //success, fail
    private T data;

}