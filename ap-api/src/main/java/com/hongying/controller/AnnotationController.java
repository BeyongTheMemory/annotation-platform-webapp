package com.hongying.controller;

import com.hongying.response.BaseResponse;
import com.hongying.service.AnnotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "ap/annotation")
public class AnnotationController {
    @Autowired
    private AnnotationService annotationService;

    @ResponseBody
    @GetMapping(value = "/create")
    public BaseResponse<Boolean> createTestUser(@RequestParam("pwd") String pwd) {
        if (!pwd.equals("hongying.duan")) {
            return BaseResponse.buildFailedResponse("hello word");
        }
        return BaseResponse.buildSuccessResponse(annotationService.initData());
    }

}
