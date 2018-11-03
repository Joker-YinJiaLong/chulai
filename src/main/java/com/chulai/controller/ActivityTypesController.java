package com.chulai.controller;

import com.alibaba.fastjson.JSONObject;
import com.chulai.config.WeChatConfig;
import com.chulai.domain.ActivityType;
import com.chulai.domain.BaseResult;
import com.chulai.enums.ResultEnum;
import com.chulai.enums.WeChatSession;
import com.chulai.service.ActivityTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.List;

@Api(value = "activityTypes")
@Controller
@RequestMapping("/api/activityTypes")
public class ActivityTypesController {

    @Autowired
    private ActivityTypeService activityTypeService;

    @ApiOperation(value = "findAllActivityType", notes = "findAllActivityType")
    @ApiImplicitParams({
    })
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BaseResult> findAllActivityType() {
        List<ActivityType> activityTypeList=activityTypeService.findAllActivityType();
        return ResponseEntity.ok(BaseResult.success(activityTypeList));
    }
}