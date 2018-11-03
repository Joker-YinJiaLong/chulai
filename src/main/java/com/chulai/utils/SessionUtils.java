package com.chulai.utils;

import javax.servlet.http.HttpSession;

public class SessionUtils {

    private static String CURRENT_USER_ID="current_user_id";

    public static Long getCurrentUserId(HttpSession session){
        Object currentUserId=session.getAttribute(CURRENT_USER_ID);
        if(null!=currentUserId){
            return Long.valueOf(String.valueOf(currentUserId));
        }
        return null;
    }
}
