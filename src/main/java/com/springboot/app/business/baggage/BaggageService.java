package com.springboot.app.business.baggage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.business.baggage.model.BaggageDE;
import com.springboot.app.business.baggage.model.BaggageRequest;
import com.springboot.app.business.baggage.model.BaggageResponse;
import com.springboot.app.business.passenger.model.PassengerDE;
import com.springboot.app.repositories.BaggageRepository;
import com.springboot.app.repositories.PassengerRepository;

@Service
public class BaggageService {

	@Autowired
	private BaggageRepository baggageRepository;

	@Autowired
	private PassengerRepository passengerRepository;

	public List<BaggageResponse> getBaggages(BaggageRequest request) {
		List<BaggageResponse> response = new ArrayList<>();

		List<BaggageDE> baggages = searchBaggages(request);

		for (BaggageDE baggage : baggages) {
			BaggageResponse resp = new BaggageResponse();

			resp.setId(String.valueOf(baggage.getId()));
			resp.setIdVuelo(baggage.getIdVuelo());
			resp.setTipo(baggage.getTipo());
			resp.setPeso(String.valueOf(baggage.getWeight()));
			resp.setCargaPasajero(String.valueOf(baggage.getBoolPassenger()));

			if (baggage.getBoolPassenger() == 'Y') {

				PassengerDE passenger = passengerRepository.findByIdPassenger(baggage.getIdPassenger());

				resp.setTipoDoc(passenger.getDocType());
				resp.setDoc(passenger.getDocNumber());
			} else {
				resp.setTipoDoc("-");
				resp.setDoc("-");
			}

			response.add(resp);
		}

		return response;
	}

	public List<BaggageDE> searchBaggages(BaggageRequest request) {

		List<BaggageDE> baggages = new ArrayList<>();

		if (request.getIdEquipaje() != null && !request.getIdEquipaje().isBlank()) {
			Long idEquipaje = Long.valueOf(request.getIdEquipaje());

			Optional<BaggageDE> opBag = baggageRepository.findById(idEquipaje);

			if (opBag.isPresent()) {
				BaggageDE baggage = opBag.get();
				baggages.add(baggage);
			}

			return baggages;
		}

		if (request.getIdPasajero() != null && !request.getIdPasajero().isBlank()
				&& (request.getIdVuelo() == null || request.getIdVuelo().isBlank())) {

			PassengerDE passenger = passengerRepository.findByDocNumber(request.getIdPasajero());

			List<BaggageDE> baggage = baggageRepository.findByIdPassengerOrderByIdVuelo(passenger.getIdPassenger());

			return baggage;
		}

		if ((request.getIdPasajero() == null || request.getIdPasajero().isBlank())
				&& (request.getIdVuelo() != null && !request.getIdVuelo().isBlank())) {

			List<BaggageDE> baggage = baggageRepository.findByIdVueloOrderByIdPassenger(request.getIdVuelo());

			return baggage;
		}

		PassengerDE passenger = passengerRepository.findByDocNumber(request.getIdPasajero());

		List<BaggageDE> baggage = baggageRepository.findByIdVueloAndIdPassengerOrderByIdVuelo(request.getIdVuelo(),
				passenger.getIdPassenger());

		return baggage;
	}

}
