package com.hongying.controller;

import com.hongying.CookieStore;
import com.hongying.enums.ErrorCodeEnum;
import com.hongying.response.BaseResponse;
import com.hongying.service.AnnotationService;
import com.hongying.service.dto.EntityCategoryDTO;
import com.hongying.service.request.AddFeedbackRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "ap/annotation")
public class AnnotationController {
    @Autowired
    private AnnotationService annotationService;

    @Autowired
    private CookieStore cookieStore;

    @ResponseBody
    @GetMapping(value = "/create")
    public BaseResponse<Boolean> createTestUser(@RequestParam("pwd") String pwd) {
        if (!pwd.equals("hongying.duan")) {
            return BaseResponse.buildFailedResponse("hello word");
        }
        return BaseResponse.buildSuccessResponse(annotationService.initData());
    }

    @ResponseBody
    @GetMapping(value = "/next")
    public BaseResponse<EntityCategoryDTO> getNextEntityCategory(@RequestParam("type") Integer type){
        Long userId = cookieStore.getUserId();
        return BaseResponse.buildSuccessResponse(annotationService.getNextEntityCategory(userId,type));
    }

    @ResponseBody
    @PostMapping(value = "/feedback")
    public BaseResponse<Boolean> feedback(@RequestBody AddFeedbackRequest request){
        Long userId = cookieStore.getUserId();
        if(request == null || CollectionUtils.isEmpty(request.getUserFeedbacks())){
            return BaseResponse.buildFailedResponse(ErrorCodeEnum.PARAM_ERROR);
        }
        annotationService.feedback(userId,request);
        return BaseResponse.buildSuccessResponse(true);
    }

}
