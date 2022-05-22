package com.springboot.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.springboot.app.business.checkCommissariat.model.CheckCommissariatDE;

@Repository
public interface CheckCommissariatRepository
		extends JpaRepository<CheckCommissariatDE, Long>, JpaSpecificationExecutor<CheckCommissariatDE> {

	CheckCommissariatDE findByIdCheckCommissariat(int idCheckCommissariatt);

	CheckCommissariatDE findByCode(String code);

}