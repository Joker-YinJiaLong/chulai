package com.zhiyun.eternal.repository;

import com.zhiyun.eternal.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ActivityRepository extends JpaSpecificationExecutor<Activity>, JpaRepository<Activity, Long> {

}
