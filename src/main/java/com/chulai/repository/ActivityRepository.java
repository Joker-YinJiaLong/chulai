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

   @Query(value = "select o from Activity o where title like '%?1%' and startTime>='?2'")
   Page<Activity> findByTitleAndStartTime(String title, Date startTime, Pageable pageable);
}
