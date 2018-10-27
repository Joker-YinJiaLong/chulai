package com.chulai.service;

import com.chulai.domain.Activity;

import java.util.List;

public interface ActivityService {

    /**
     * 添加活动
     * @param activity
     * @return
     */
    Long createActivety(Activity activity);

    /**
     *
     * @return
     */
    List<Activity> findActivety();
}
