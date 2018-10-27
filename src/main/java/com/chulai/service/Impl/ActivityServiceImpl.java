package com.chulai.service.Impl;

import com.chulai.domain.Activity;
import com.chulai.repository.ActivityRepository;
import com.chulai.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Long createActivety(Activity activity) {
        return null;
    }

    @Override
    public List<Activity> findActivety() {
        return null;
    }
}
