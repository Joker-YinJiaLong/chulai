package com.chulai.service;

import com.chulai.domain.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ActivityService {

    /**
     * 添加活动
     * @param activity
     * @return
     */
    Long createActivety(Activity activity);

    /**
     *通过关键字搜索活动信息
     * @return
     */
    Page<Activity> searchActivetyByKeyword(Long activityTypeId,String keyword,Pageable pageable);

    /**
     * 查询用户的模板列表
     * @param userId
     * @return
     */
    Page<Activity> findActivetyTemplateByUserId(Long userId, Pageable pageable);

    /**
     * 根据id查找活动信息
     * @param activityId
     * @return
     */
    Activity findById(Long activityId);

    /**
     * 更新活动信息
     * @param activity
     */
    void updateActivety(Activity activity);
}
