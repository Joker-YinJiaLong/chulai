package com.chulai.enums;

public enum WeChatSession {
    ERRCODE("errcode"),
    ERRMSG("errmsg"),
    OPENID("openid"),
    SESSIONKEY("session_key");
    private String key;
    private WeChatSession(String key){
        this.key=key;
    }

    public String key(){
        return this.key;
    }
}
