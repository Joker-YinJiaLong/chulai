package com.chulai.interceptor;

import com.chulai.context.SessionContext;
import com.chulai.enums.WeChatSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId=request.getParameter("sessionId");
        if(StringUtils.isBlank(sessionId)){
            return false;
        }
        SessionContext sessionContext=SessionContext.getInstance();
        HttpSession session=sessionContext.getSession(sessionId);
        if(null==session){
            return false;
        }
        String openid=(String)session.getAttribute(WeChatSession.OPENID.key());
        if(StringUtils.isBlank(openid)){
            return false;
        }
        request.setAttribute(WeChatSession.OPENID.key(),openid);
        return true;
    }
}
