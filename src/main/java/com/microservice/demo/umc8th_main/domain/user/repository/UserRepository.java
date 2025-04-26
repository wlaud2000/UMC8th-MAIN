package com.microservice.demo.umc8th_main.domain.user.repository;

import com.microservice.demo.umc8th_main.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    // 기본 JPA 메서드는 자동으로 제공됨
}
