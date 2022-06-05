package com.springboot.app.business.checkCommissariat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.business.checkCommissariat.model.CheckCommissariatDE;
import com.springboot.app.business.checkCommissariat.model.CheckCommissariatTO;
import com.springboot.app.repositories.CheckCommissariatRepository;

@Service
public class CheckCommissariatService {
	@Autowired
	private CheckCommissariatRepository checkCommissariatRepository;

	public CheckCommissariatTO getCheckCommissariat(String id) {
		CheckCommissariatDE de = checkCommissariatRepository.findByIdFlight(id);
		return CheckCommissariatMapper.mapTo(de);
	}

	public CheckCommissariatTO saveCheckCommissariat(CheckCommissariatTO checkCommissariat) {
		CheckCommissariatDE de = CheckCommissariatMapper.mapDE(checkCommissariat);
		de = checkCommissariatRepository.save(de);
		return CheckCommissariatMapper.mapTo(de);
	}

}
