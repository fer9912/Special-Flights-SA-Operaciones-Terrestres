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
import com.springboot.app.services.ApisRequests;
import com.springboot.app.services.model.Flight;

@Service
public class BaggageService {

	@Autowired
	private BaggageRepository baggageRepository;

	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private ApisRequests apiRequest;

	public List<BaggageResponse> getBaggages(BaggageRequest request) {
		List<BaggageResponse> response = new ArrayList<>();
		String lastFlight = "";
		String lastFlightStatus = "";

		List<BaggageDE> baggages = searchBaggages(request);

		for (BaggageDE baggage : baggages) {
			BaggageResponse resp = new BaggageResponse();

			try {
				if (baggage.getIdVuelo().equals(lastFlight)) {
					resp.setStatus(lastFlightStatus);
					baggage.setEstado(lastFlightStatus);
					baggageRepository.save(baggage);
				} else {
					List<Flight> flightResp = apiRequest.getFlight(baggage.getIdVuelo());
					Flight flight = flightResp.get(0);
					resp.setStatus(flight.getEstado().toUpperCase());
					lastFlight = flight.getIdvuelo();
					lastFlightStatus = flight.getEstado().toUpperCase();
					baggage.setEstado(flight.getEstado().toUpperCase());
					baggageRepository.save(baggage);
				}
			} catch (Exception e) {
				resp.setStatus(baggage.getEstado());
			}

			resp.setBaggageId(String.valueOf(baggage.getId()));
			resp.setFlightId(baggage.getIdVuelo());
			resp.setBaggageType(baggage.getTipo());
			resp.setWeight(String.valueOf(baggage.getWeight()));

			resp.setIsPassengerBaggage(String.valueOf(baggage.getBoolPassenger()));

			if (baggage.getBoolPassenger() == 'Y') {

				PassengerDE passenger = passengerRepository.findByIdPassenger(baggage.getIdPassenger());

				resp.setDocType(passenger.getDocType());
				resp.setDocumentNumber(passenger.getDocNumber());
			} else {
				resp.setDocType("-");
				resp.setDocumentNumber("-");
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
