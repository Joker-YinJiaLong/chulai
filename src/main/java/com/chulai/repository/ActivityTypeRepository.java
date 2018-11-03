package com.chulai.repository;

import com.chulai.domain.Activity;
import com.chulai.domain.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ActivityTypeRepository extends JpaSpecificationExecutor<ActivityType>, JpaRepository<ActivityType, Long> {

}
