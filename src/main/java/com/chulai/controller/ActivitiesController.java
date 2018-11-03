package com.chulai.controller;

import com.chulai.domain.Activity;
import com.chulai.domain.ActivityType;
import com.chulai.domain.BaseResult;
import com.chulai.enums.ResultEnum;
import com.chulai.service.ActivityService;
import com.chulai.service.ActivityTypeService;
import com.chulai.utils.SessionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Api(value = "activities")
@Controller
@RequestMapping("/api/activities")
public class ActivitiesController {

    @Autowired
    private ActivityService activityService;

    @ApiOperation(value = "findAllActivityType", notes = "findAllActivityType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "page", required = true, dataType = "Int"),
            @ApiImplicitParam(name = "size", value = "size", required = true, dataType = "Int")
    })
    @ResponseBody
    @RequestMapping(value = {"/template"},method = RequestMethod.GET)
    public ResponseEntity<BaseResult> findTemplateByUser(@RequestParam(value = "page") Integer page,@RequestParam(value = "size") Integer size,HttpSession session) {
        Long currentUserId= SessionUtils.getCurrentUserId(session);
        if(null==currentUserId){
            return ResponseEntity.ok(BaseResult.failure(ResultEnum.USER_NOT_LOGGED_IN));
        }
        Sort sort=Sort.by(Sort.Direction.DESC,"createTime");
        Pageable pageable=PageRequest.of(page,size,sort);
        Page<Activity> activityPage=activityService.findActivetyTemplateByUserId(currentUserId,pageable);
        return ResponseEntity.ok(BaseResult.success(activityPage));
    }

    @ApiOperation(value = "create activity", notes = "create activity")
    @ApiImplicitParams({
    })
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BaseResult> createActivity(@RequestBody Activity activity) {
        Long activityId=activityService.createActivety(activity);
        return ResponseEntity.ok(BaseResult.success(activityId));
    }

    @ApiOperation(value = "find activity by id", notes = "find activity by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "activityId", required = true, dataType = "Long",paramType = "path")
    })
    @ResponseBody
    @RequestMapping(value = {"/{activityId}"},method = RequestMethod.GET)
    public ResponseEntity<BaseResult> findActivityById(@PathVariable(value = "activityId",required = true)Long activityId) {
        Activity activity=activityService.findById(activityId);
        return ResponseEntity.ok(BaseResult.success(activity));
    }

    @ApiOperation(value = "update activity", notes = "update activity")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "activityId", required = true, dataType = "Long",paramType = "path")
    })
    @ResponseBody
    @RequestMapping(value = {"/{activityId}"},method = RequestMethod.PUT)
    public ResponseEntity<BaseResult> updateActivity(@RequestBody Activity activity,@PathVariable(value = "activityId",required = true)Long activityId) {
        if(null==activity||null==activityId){
            return ResponseEntity.ok(BaseResult.failure(ResultEnum.PARAM_NOT_COMPLETE));
        }
        activity.setId(activityId);
        activityService.updateActivety(activity);
        return ResponseEntity.ok(BaseResult.success());
    }

    @ApiOperation(value = "search activity by keyword", notes = "search activity by keyword")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "keyword", required = false, dataType = "String"),
            @ApiImplicitParam(name = "page", value = "page", required = true, dataType = "Int"),
            @ApiImplicitParam(name = "size", value = "size", required = true, dataType = "Int")
    })
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BaseResult> findActivity(@RequestParam(value = "keyword") String keyword,@RequestParam(value = "page") Integer page,@RequestParam(value = "size") Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"startTime");
        Pageable pageable=PageRequest.of(page,size,sort);
        Page<Activity> activityPage=activityService.searchActivetyByKeyword(keyword,pageable);
        return ResponseEntity.ok(BaseResult.success(activityPage));
    }
}