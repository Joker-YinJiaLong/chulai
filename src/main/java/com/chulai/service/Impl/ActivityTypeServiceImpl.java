package com.chulai.service.Impl;

import com.chulai.domain.ActivityType;
import com.chulai.repository.ActivityTypeRepository;
import com.chulai.service.ActivityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityTypeServiceImpl implements ActivityTypeService{

    @Autowired
    private ActivityTypeRepository activityTypeRepository;

    @Override
    public List<ActivityType> findAllActivityType() {
        Sort sort=Sort.by(Sort.Direction.ASC,"order");
        return activityTypeRepository.findAll(sort);
    }
}
