package com.chulai.repository;

import com.chulai.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource
public interface UserRepository extends JpaSpecificationExecutor<User>,JpaRepository<User, Long> {

    List<User> findByUsername(String username);

    Page<User> findById(Long id, Pageable pageable);

    @Query(value = "select o from User o where username='?1' and profession like '%?2%' and city='?3'")
    Page<User> findByUsernameAndProfession(String username, String profession, String city, Pageable pageable);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Modifying
    @Query("update User where username= :username where id=:id ")
    int editUser(User user);

}
