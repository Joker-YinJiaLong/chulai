package com.chulai.repository;

import com.chulai.domain.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface ActivityRepository extends JpaSpecificationExecutor<Activity>, JpaRepository<Activity, Long> {

   List<Activity> findByActivityTypeOrderByStartTimeAsc(Long activityType);

   Page<Activity> findByCreatorIdAndTemplateTrue(Long userId,Pageable pageable);

   @Query(value = "select o from Activity o where activityType='?1' and title like '%?2%' and startTime>='?3'")
   Page<Activity> findByTitleAndStartTime(Long activityTypeId,String title, Date startTime, Pageable pageable);
}
