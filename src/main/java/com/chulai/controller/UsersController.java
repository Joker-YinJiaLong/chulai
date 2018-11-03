package com.chulai.controller;

import com.alibaba.fastjson.JSONObject;
import com.chulai.config.WeChatConfig;
import com.chulai.domain.BaseResult;
import com.chulai.enums.ResultEnum;
import com.chulai.enums.WeChatSession;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Api(value = "currencies")
@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private WeChatConfig weChatConfig;

    @ApiOperation(value = "login", notes = "login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsCode", value = "jsCode", required = true, dataType = "String")
    })

    @PostMapping(value = { "/login" })
    public ResponseEntity<BaseResult> login(@RequestParam(value = "jsCode") String jsCode, HttpSession session) {
        String sessionHost=weChatConfig.getSessionHost();
        String secret=weChatConfig.getSecret();
        String appid=weChatConfig.getAppid();
        String grantType=weChatConfig.getGrantType();
        RestTemplate restTemplate = new RestTemplate();
        sessionHost=sessionHost.endsWith("?")?sessionHost:sessionHost+"?";
        sessionHost+="secret="+secret;
        sessionHost+="&appid="+appid;
        sessionHost+="&grant_type="+grantType;
        sessionHost+="&js_code="+jsCode;
        ResponseEntity<String>  responseEntity = restTemplate.exchange(sessionHost, HttpMethod.GET, null, String.class);
        if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK)
        {
            String sessionData = responseEntity.getBody();
            //解析从微信服务器获得的openid和session_key;
            JSONObject sessionObj=JSONObject.parseObject(sessionData);
            Integer errCode=sessionObj.getInteger(WeChatSession.ERRCODE.key());
            if(null==errCode){
                String openid = sessionObj.getString(WeChatSession.OPENID.key());
                String sessionKey = sessionObj.getString(WeChatSession.SESSIONKEY.key());
                session.setAttribute(WeChatSession.OPENID.key(),openid);
                session.setAttribute(WeChatSession.SESSIONKEY.key(),sessionKey);
                return ResponseEntity.ok(BaseResult.success(session.getId()));
            }else {
                BaseResult result=new BaseResult();
                result.setCode(errCode);
                String errMsg=sessionObj.getString(WeChatSession.ERRMSG.key());
                result.setMsg(errMsg);
                return ResponseEntity.ok(result);
            }
        }
        return ResponseEntity.ok(BaseResult.failure(ResultEnum.INTERFACE_OUTER_INVOKE_ERROR));
    }
}