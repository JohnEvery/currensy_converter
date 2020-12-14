package com.eve.emphasoft.user.repository;

import com.eve.emphasoft.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
}
