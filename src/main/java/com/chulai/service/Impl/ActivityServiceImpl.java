package com.chulai.service.Impl;

import com.chulai.domain.Activity;
import com.chulai.repository.ActivityRepository;
import com.chulai.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Long createActivety(Activity activity) {
         activityRepository.save(activity);
        return activity.getId();
    }

    @Override
    public Page<Activity> searchActivetyByKeyword(Long activityTypeId,String keyword,Pageable pageable) {
        Date startTime=new Date();
        //TODO
        return activityRepository.findByTitleAndStartTime(activityTypeId,keyword,startTime,pageable);
    }

    @Override
    public Page<Activity> findActivetyTemplateByUserId(Long userId, Pageable pageable) {
        return activityRepository.findByCreatorIdAndTemplateTrue(userId,pageable);
    }

    @Override
    public Activity findById(Long activityId) {
        Optional<Activity> optional=activityRepository.findById(activityId);
        if(null!=optional&&optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void updateActivety(Activity activity) {
        activityRepository.save(activity);
    }
}
