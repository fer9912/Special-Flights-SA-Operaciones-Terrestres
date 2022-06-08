package com.springboot.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.app.business.user.model.RoleDE;

@Repository
public interface RoleRepository extends JpaRepository<RoleDE, Long>, JpaSpecificationExecutor<RoleDE> {

	@Query(value = "SELECT * FROM `role` as r \n" + "INNER JOIN `user` as u ON u.rol = r.id \n"
			+ "WHERE u.`user` = :user ", nativeQuery = true)
	RoleDE findByUser(@Param("user") String user);
}