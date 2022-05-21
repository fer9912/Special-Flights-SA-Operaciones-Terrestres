package com.springboot.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.springboot.app.business.user.model.UserDE;

@Repository
public interface UserRepository extends JpaRepository<UserDE, Long>, JpaSpecificationExecutor<UserDE> {

	UserDE findByUserAndPassword(String user, String password);
}