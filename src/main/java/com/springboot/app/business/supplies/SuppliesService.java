package com.springboot.app.business.supplies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.business.supplies.model.SuppliesDE;
import com.springboot.app.business.supplies.model.SuppliesTO;
import com.springboot.app.repositories.SuppliesRepository;

@Service
public class SuppliesService {
	@Autowired
	private SuppliesRepository suppliesRepository;

	public SuppliesTO getSupplies(int id) {
		SuppliesDE de = suppliesRepository.findByIdFlight(id);
		return SuppliesMapper.mapTo(de);
	}

}
