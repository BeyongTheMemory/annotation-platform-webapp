package com.hongying.controller;

import com.hongying.CookieStore;
import com.hongying.enums.ErrorCodeEnum;
import com.hongying.response.BaseResponse;
import com.hongying.service.AnnotationService;
import com.hongying.service.SentenceService;
import com.hongying.service.dto.EntityCategoryDTO;
import com.hongying.service.dto.FeedBackInitDTO;
import com.hongying.service.dto.SentenceDTO;
import com.hongying.service.request.AddFeedbackRequest;
import com.hongying.service.request.AddSentenceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "ap/sentence")
public class SentenceController {
    @Autowired
    private SentenceService sentenceService;

    @Autowired
    private CookieStore cookieStore;

    @ResponseBody
    @GetMapping(value = "/create")
    public BaseResponse<Boolean> init(@RequestParam("pwd") String pwd) {
        if (!pwd.equals("hongying.duan")) {
            return BaseResponse.buildFailedResponse("hello word");
        }
        return BaseResponse.buildSuccessResponse(sentenceService.init());
    }

    @ResponseBody
    @GetMapping(value = "/count")
    public BaseResponse<Integer> count(@RequestParam("name") String name) {
        return BaseResponse.buildSuccessResponse(sentenceService.count(name));
    }

    @ResponseBody
    @GetMapping(value = "/next")
    public BaseResponse<SentenceDTO> getNext(){
        Long userId = cookieStore.getUserId();
        return BaseResponse.buildSuccessResponse(sentenceService.getNext(userId));
    }

    @ResponseBody
    @PostMapping(value = "/feedback")
    public BaseResponse<Boolean> feedback(@RequestBody AddSentenceRequest request){
        Long userId = cookieStore.getUserId();
        if(request == null || request.getSentenceId() == null){
            return BaseResponse.buildFailedResponse(ErrorCodeEnum.PARAM_ERROR);
        }
        sentenceService.feedback(userId,request.getSentenceId(),request.getReason());
        return BaseResponse.buildSuccessResponse(true);
    }

    @ResponseBody
    @PostMapping(value = "/init_feedback")
    public BaseResponse<Boolean> initFeedback(@RequestBody List<FeedBackInitDTO> request) {
        return BaseResponse.buildSuccessResponse(sentenceService.initFeedBack(request));
    }
}
