package com.chulai.repository;

import com.chulai.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ActivityRepository extends JpaSpecificationExecutor<Activity>, JpaRepository<Activity, Long> {

}
