package com.springboot.app.business.parameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.business.parameters.model.ParametersDE;
import com.springboot.app.business.parameters.model.ParametersTO;
import com.springboot.app.repositories.ParametersRepository;

@Service
public class ParametersService {
	@Autowired
	private ParametersRepository parametersRepository;

	public ParametersTO getParameters() {
		ParametersDE de = parametersRepository.findById(1);
		return ParametersMapper.mapTo(de);
	}

	public ParametersTO saveParameters(ParametersTO parameters) {
		ParametersDE de = ParametersMapper.mapDE(parameters);
		de = parametersRepository.save(de);
		return ParametersMapper.mapTo(de);
	}

}
