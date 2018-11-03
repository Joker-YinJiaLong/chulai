package com.chulai.controller;

import com.chulai.domain.ActivityType;
import com.chulai.domain.BaseResult;
import com.chulai.service.ActivityTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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