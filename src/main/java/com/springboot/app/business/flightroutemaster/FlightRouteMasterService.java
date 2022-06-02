package com.springboot.app.business.flightroutemaster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.business.aircraft.AircraftService;
import com.springboot.app.business.aircraft.model.AircraftTO;
import com.springboot.app.business.airport.AirportService;
import com.springboot.app.business.airport.model.AirportTO;
import com.springboot.app.business.flightroutemaster.model.DayWeekDE;
import com.springboot.app.business.flightroutemaster.model.FlightRouteRequestApiTO;
import com.springboot.app.business.flightroutemaster.model.FlightRouteRequestTO;
import com.springboot.app.business.flightroutemaster.model.FlightRouteResponseApiTO;
import com.springboot.app.business.flightroutemaster.model.FlightRouteResponseTO;
import com.springboot.app.business.flightroutemaster.model.UnmannedAircraftResponse;
import com.springboot.app.business.flightroutemaster.model.Way;
import com.springboot.app.business.parameters.ParametersService;
import com.springboot.app.business.parameters.model.ParametersTO;
import com.springboot.app.repositories.DayWeekRepository;
import com.springboot.app.services.ApisRequests;
import com.springboot.app.services.model.Flight;
import com.springboot.app.utils.Util;

@Service
public class FlightRouteMasterService {
	int gananciaPorPersona = 65;
	int costoCombustiblePorLitro = 150;
	int costoLubricantePorLitro = 10;
	int costoDeInsumosPorPersona = 28;
	int promedioDePersonas = 60;
	int listrosDeCombustibleXKilometro = 0;
	int listrosDeLubricanteXMilKilometros = 0;
	int estimadoDePersonas = 0;
	int distanciaDeViaje = 0;
	List<AirportTO> airportsGlobal = new ArrayList<>();
	List<Flight> flightsGlobal = new ArrayList<>();
	int distance = 13400;
	int capacity = 290;

	@Autowired
	private AirportService airportService;
	@Autowired
	private DayWeekRepository dayWeekRepository;
	@Autowired
	private AircraftService aircraftService;
	@Autowired
	private ApisRequests apisRequests;
	@Autowired
	private ParametersService parametersService;

	public FlightRouteResponseTO generateFlightRoute(FlightRouteRequestTO request) {
		setParameters();
		airportsGlobal = this.airportService.getAirports();
		FlightRouteResponseTO response = new FlightRouteResponseTO();
		setParametersToWeight(request);
		response.setRoute(getOptimalRoute(request).getRoute());
		response.setDay(getWeekDay());
		response.setDistance(getDistance(response.getRoute()));
		response.setAircraft(request.getAircraft() != null ? request.getAircraft() : getOptimalAirCraft(response));
		response.setOptimalAircrafts(getOptimalAirCrafts(response));
		response.setDistance(getDistance(response.getRoute()));
		response.setPeopleEstimate(getPeopleEstimate(response.getRoute()));
		response.setCombustibleEstimado(calcularConsumoCombustible(response));
		response.setLubricanteEstimado(calcularConsumoLubricante(response));
		return response;
	}

	private int calcularConsumoCombustible(FlightRouteResponseTO response) {
		int distance = response.getDistance();
		int consumoCombustible = response.getAircraft().getFuelConsumption();
		return distance * consumoCombustible;
	}

	private int calcularConsumoLubricante(FlightRouteResponseTO response) {
		int distance = response.getDistance() / 1000;
		int consumoLubricante = response.getAircraft().getLubricantConsumption();
		return distance * consumoLubricante;
	}

	public UnmannedAircraftResponse calculateOptimalAircraft(String origin, String destination) {
		airportsGlobal = this.airportService.getAirports();
		List<AircraftTO> aeronaves = this.aircraftService.getAircrafts();
		aeronaves = aeronaves.stream().filter(a -> a.getPassengerCapacity() == 0).collect(Collectors.toList());
		UnmannedAircraftResponse response = new UnmannedAircraftResponse();
		int distancia = this.getDistance(origin, destination);
		int maxDistance = Integer.MAX_VALUE;
		AircraftTO aircraft = new AircraftTO();
		List<String> optimals = new ArrayList<>();
		for (AircraftTO aeronave : aeronaves) {
			if (aeronave.getMaxDistance() > distancia && aeronave.getMaxDistance() < maxDistance) {
				maxDistance = aeronave.getMaxDistance();
				aircraft = aeronave;
			}
			if (aeronave.getMaxDistance() > distancia) {
				optimals.add(aeronave.getModel());
			}
		}
		response.setAircraftModel(aircraft.getModel());
		response.setOptimalAircrafts(optimals);
		response.setDistance(distancia);
		return response;

	}

	public UnmannedAircraftResponse calculateOptimalAircraft2(double originLat, double originLon, double destinationLat,
			double destinationLon) {
		airportsGlobal = this.airportService.getAirports();
		List<AircraftTO> aeronaves = this.aircraftService.getAircrafts();
		aeronaves = aeronaves.stream().filter(a -> a.getPassengerCapacity() == 0).collect(Collectors.toList());
		UnmannedAircraftResponse response = new UnmannedAircraftResponse();
		int distancia = Util.calculateDistanceByHaversineFormula(originLon, originLat, destinationLon, destinationLat);
		int maxDistance = Integer.MAX_VALUE;
		AircraftTO aircraft = new AircraftTO();
		List<String> optimals = new ArrayList<>();
		for (AircraftTO aeronave : aeronaves) {
			if (aeronave.getMaxDistance() > distancia && aeronave.getMaxDistance() < maxDistance) {
				maxDistance = aeronave.getMaxDistance();
				aircraft = aeronave;
			}
			if (aeronave.getMaxDistance() > distancia) {
				optimals.add(aeronave.getModel());
			}
		}
		response.setAircraftModel(aircraft.getModel());
		response.setOptimalAircrafts(optimals);
		response.setDistance(distancia);
		return response;

	}

	private void setParameters() {
		ParametersTO parameters = this.parametersService.getParameters();
		promedioDePersonas = parameters.getPromedioDePersonas();
		gananciaPorPersona = parameters.getGananciaPorPersona();
		costoCombustiblePorLitro = parameters.getCostoLitroCombustible();
		costoLubricantePorLitro = parameters.getCostoLitroLubricante();
		costoDeInsumosPorPersona = parameters.getCostoInsumosPorPersona();

	}

	private void setParametersToWeight(FlightRouteRequestTO request) {
		flightsGlobal = this.apisRequests.getFlights();
		if (request.getAircraft() != null) {
			AircraftTO aeronave = this.aircraftService.getAircraft(request.getAircraft().getId());
			if ((aeronave.getPassengerCapacity() * 60 / 100) < promedioDePersonas) {
				promedioDePersonas = aeronave.getPassengerCapacity() * 60 / 100;
			}
			distance = aeronave.getMaxDistance();
			capacity = aeronave.getPassengerCapacity();
			listrosDeCombustibleXKilometro = aeronave.getFuelConsumption();
			listrosDeLubricanteXMilKilometros = aeronave.getLubricantConsumption();

		} else {
			List<AircraftTO> aeronaves = this.aircraftService.getAircrafts();
			listrosDeCombustibleXKilometro = getFuelCAverageByAircrafts(aeronaves);
			listrosDeLubricanteXMilKilometros = getLubricantCAverageByAircrafts(aeronaves);
		}

	}

	private List<AircraftTO> getOptimalAirCrafts(FlightRouteResponseTO response) {
		int distancia = response.getDistance();
		int estimado = cantMaxPersonas(response);
		List<AircraftTO> ret = new ArrayList<>();
		List<AircraftTO> aeronaves = this.aircraftService.getAircrafts();
		for (AircraftTO aeronave : aeronaves) {
			if (estimado > this.capacity && aeronave.getModel().equals("Airbus 330-200 8")) {
				ret.add(aeronave);
			}
			if (aeronave.getMaxDistance() > distancia + 300 && aeronave.getPassengerCapacity() > estimado) {
				ret.add(aeronave);
			}
		}
		return ret;
	}

	private AircraftTO getOptimalAirCraft(FlightRouteResponseTO response) {
		int distancia = response.getDistance();
		int estimado = cantMaxPersonas(response);
		int maxDistance = Integer.MAX_VALUE;
		AircraftTO ret = new AircraftTO();
		List<AircraftTO> aeronaves = this.aircraftService.getAircrafts();

		for (AircraftTO aeronave : aeronaves) {
			if (estimado > this.capacity && aeronave.getModel().equals("Airbus 330-200 8")) {
				ret = aeronave;
			}
			if (aeronave.getMaxDistance() > distancia + 300 && aeronave.getMaxDistance() < maxDistance
					&& aeronave.getPassengerCapacity() > estimado) {
				maxDistance = aeronave.getMaxDistance();
				ret = aeronave;
			}
		}
		return ret;
	}

	private Way getOptimalRoute(FlightRouteRequestTO request) {
		List<AirportTO> destinations = this.airportService.getAirports();
		destinations.removeAll(request.getExcludeDestinations());
		List<AirportTO> stops = request.getIncludeDestinations();
		AirportTO start = stops.get(0);
		AirportTO end = stops.get(stops.size() - 1);
		destinations = filterDestinations(destinations, start, end);
		stops.remove(start);
		stops.remove(end);
		if (!destinations.isEmpty()) {
			return getOptimalWay(start, end, stops, destinations);
		} else {
			Way optimalWay = new Way();
			List<AirportTO> optimalRoute = new ArrayList<>();
			optimalWay.setWeight(getWeight(start.getIata(), end.getIata(), null));
			optimalRoute.add(start);
			optimalRoute.add(end);
			optimalWay.setRoute(optimalRoute);
			return optimalWay;
		}
	}

	private List<AirportTO> filterDestinations(List<AirportTO> airports, AirportTO start, AirportTO end) {
		List<AirportTO> filterDestinations = new ArrayList<>();
		int maxRange = getDistance(start, end);
		int minRange = 300;
		for (AirportTO airport : airports) {
			int a = getDistance(airport, start);
			int b = getDistance(airport, end);
			if ((minRange < a && a < maxRange) && (minRange < b && b < maxRange)) {
				filterDestinations.add(airport);
			}
		}

		return filterDestinations;
	}

	public int getWeight(String start, String end, List<String> otherDestinations) { // + peso = - rentabilidad
		int weight = 0;
		setParametersToWeight(start, end, otherDestinations);
		int gananciaPorViaje = gananciaPorPersona * estimadoDePersonas;
		int costoPorViaje = ((distanciaDeViaje * listrosDeCombustibleXKilometro) * costoCombustiblePorLitro)
				+ ((distanciaDeViaje / 1000 * listrosDeLubricanteXMilKilometros) * costoLubricantePorLitro)
				+ (estimadoDePersonas * costoDeInsumosPorPersona);
		weight = gananciaPorViaje - costoPorViaje;
		return -weight;
	}

	private void setParametersToWeight(String start, String end, List<String> otherDestinations) {
		if (otherDestinations == null || otherDestinations.isEmpty()) {
			List<Flight> flights = getPlanDeVuelos(start, end, flightsGlobal);
			int personasABordoProm = promedioDePersonas(flights);
			if (flights.size() > 3 && personasABordoProm > 30) {
				distanciaDeViaje = getDistanciaPromedio(flights);
				estimadoDePersonas = personasABordoProm;
				listrosDeCombustibleXKilometro = getFuelCProm(flights);
				listrosDeLubricanteXMilKilometros = getOilCProm(flights);
			} else {
				distanciaDeViaje = getDistance(start, end);
				estimadoDePersonas = promedioDePersonas;
			}

		} else {
			List<Flight> flights = getPlanDeVuelos2(start, end, otherDestinations);
			if (!flights.isEmpty()) {
				List<String> list = new ArrayList<>();
				list.add(start);
				list.addAll(otherDestinations);
				list.add(end);
				for (int i = 0; i < list.size() - 1; i++) {
					List<Flight> vuelos = getPlanDeVuelos(list.get(i), list.get(i + 1), flights);
					distanciaDeViaje += getDistanciaPromedio(vuelos);
					estimadoDePersonas += promedioDePersonas(vuelos);
					listrosDeCombustibleXKilometro += getFuelCProm(vuelos);
					listrosDeLubricanteXMilKilometros += getOilCProm(vuelos);
				}
				estimadoDePersonas += promedioDePersonas(getPlanDeVuelos(start, end, flights));
				int size = otherDestinations.size();
				if (size > 1) {
					estimadoDePersonas += promedioDePersonas(getPlanDeVuelos(otherDestinations.get(0), end, flights));
					estimadoDePersonas += promedioDePersonas(
							getPlanDeVuelos(start, otherDestinations.get(size - 1), flights));
				}

			} else {
				distanciaDeViaje = getDistance(start, end, otherDestinations);
				estimadoDePersonas = promedioDePersonas * (otherDestinations.size() == 1 ? 3 : 6);
			}

		}

	}

	private int getPeopleEstimate(List<AirportTO> airports) {
		List<String> otherDestinations = airports.stream().map(AirportTO::getIata).collect(Collectors.toList());
		String start = otherDestinations.get(0);
		String end = otherDestinations.get(otherDestinations.size() - 1);
		otherDestinations.remove(start);
		otherDestinations.remove(end);
		int ret = 0;
		if (otherDestinations == null || otherDestinations.isEmpty()) {
			List<Flight> flights = getPlanDeVuelos(start, end, flightsGlobal);
			int personasABordoProm = promedioDePersonas(flights);
			if (flights.size() > 3 && personasABordoProm > 30) {
				ret = personasABordoProm;
			} else {
				ret = promedioDePersonas;
			}

		} else {
			List<Flight> flights = getPlanDeVuelos2(start, end, otherDestinations);
			if (!flights.isEmpty()) {
				List<String> list = new ArrayList<>();
				list.add(start);
				list.addAll(otherDestinations);
				list.add(end);
				for (int i = 0; i < list.size() - 1; i++) {
					List<Flight> vuelos = getPlanDeVuelos(list.get(i), list.get(i + 1), flights);
					ret += promedioDePersonas(vuelos);
				}
				ret += promedioDePersonas(getPlanDeVuelos(start, end, flights));
				int size = otherDestinations.size();
				if (size > 1) {
					ret += promedioDePersonas(getPlanDeVuelos(otherDestinations.get(0), end, flights));
					ret += promedioDePersonas(getPlanDeVuelos(start, otherDestinations.get(size - 1), flights));
				}

			} else {
				ret = promedioDePersonas * (otherDestinations.size() == 1 ? 3 : 6);
			}

		}
		return ret;

	}

	private List<Flight> getPlanDeVuelos2(String start, String end, List<String> otherDestinations) {
		List<Flight> ret = new ArrayList<>();
		List<String> list = new ArrayList<>();
		list.add(start);
		list.addAll(otherDestinations);
		list.add(end);
		for (int i = 0; i < list.size() - 1; i++) {
			List<Flight> flights = getPlanDeVuelos(list.get(i), list.get(i + 1), flightsGlobal);
			int personasABordoProm = promedioDePersonas(flights);
			if (flights.size() > 3 && personasABordoProm > 30) {
				ret.addAll(flights);
			} else {
				return Collections.emptyList();
			}
		}
		return ret;
	}

	private int getOilCProm(List<Flight> flights2) {
		int oilCProm = 0;
		for (Flight flight : flights2) {
			oilCProm += flight.getLubricantereal() / (flight.getKilometrajereal() / 1000);
		}
		return oilCProm / flights2.size();
	}

	public int getOilCPromTesteable(List<Flight> flights2) {
		return getOilCProm(flights2);
	}

	private int getFuelCProm(List<Flight> flights2) {
		int fuelCProm = 0;
		for (Flight flight : flights2) {
			fuelCProm += flight.getLtscombustiblereal() / flight.getKilometrajereal();
		}
		return fuelCProm / flights2.size();
	}

	public int getFuelCPromTesteable(List<Flight> flights2) {
		return getFuelCProm(flights2);
	}

	private int getDistanciaPromedio(List<Flight> flights2) {
		int distancia = 0;
		for (Flight flight : flights2) {
			distancia += flight.getKilometrajereal();
		}
		return distancia / flights2.size();
	}

	public int getDistanciaPromedioTesteable(List<Flight> flights2) {
		return getDistanciaPromedio(flights2);
	}

	private int promedioDePersonas(List<Flight> flights2) {
		int pasajerosQueHicieronEseViaje = 0;
		for (Flight flight : flights2) {
			pasajerosQueHicieronEseViaje += flight.getTotalpersonasabordo();
		}
		return flights2.isEmpty() ? 0 : pasajerosQueHicieronEseViaje / flights2.size();
	}

	public int promedioDePersonasTesteable(List<Flight> flights2) {
		return promedioDePersonas(flights2);
	}

	private List<Flight> getPlanDeVuelos(String a, String b, List<Flight> flights) {
		List<Flight> ret = new ArrayList<>();
		for (Flight flight : flights) {
			if (a.equals(flight.getOrigenreal_codiata()) && b.equals(flight.getDestinoreal_codiata())
					&& "Aterrizado".equals(flight.getEstado().trim())) {
				ret.add(flight);
			}
		}
		return ret;
	}

	private int getLubricantCAverageByAircrafts(List<AircraftTO> aeronaves) {
		int ret = 0;
		for (AircraftTO aeronave : aeronaves) {
			ret += aeronave.getLubricantConsumption();
		}
		return ret / aeronaves.size();
	}

	public int getLubricantCAverageByAircraftsTesteable(List<AircraftTO> aeronaves) {
		return getLubricantCAverageByAircrafts(aeronaves);
	}

	private int getFuelCAverageByAircrafts(List<AircraftTO> aeronaves) {
		int ret = 0;
		for (AircraftTO aeronave : aeronaves) {
			ret += aeronave.getFuelConsumption();
		}
		return ret / aeronaves.size();
	}

	public int getFuelCAverageByAircraftsTesteable(List<AircraftTO> aeronaves) {
		return getFuelCAverageByAircrafts(aeronaves);
	}

	private int getDistanceOfRoute(String start, String end, List<String> otherDestinations) {
		int distanciaV = 0;
		List<Flight> flights = getPlanDeVuelos2(start, end, otherDestinations);
		if (!flights.isEmpty()) {
			List<String> list = new ArrayList<>();
			list.add(start);
			list.addAll(otherDestinations);
			list.add(end);
			for (int i = 0; i < list.size() - 1; i++) {
				List<Flight> vuelos = getPlanDeVuelos(list.get(i), list.get(i + 1), flights);
				distanciaV += getDistanciaPromedio(vuelos);
			}

		} else {
			distanciaV = getDistance(start, end, otherDestinations);
		}
		return distanciaV;

	}

	public Way getOptimalWay(AirportTO start, AirportTO end, List<AirportTO> stops, List<AirportTO> destinations) {
		AirportTO empty = new AirportTO();
		empty.setIata("");
		destinations.remove(start);
		destinations.remove(end);
		destinations.add(empty);
		List<AirportTO> optimalRoute = new ArrayList<>();
		Way optimalWay = new Way();
		if (stops == null || stops.isEmpty()) {
			optimalWay.setWeight(getWeight(start.getIata(), end.getIata(), null));
			optimalRoute.add(start);
			optimalRoute.add(end);
			for (AirportTO A : destinations) {
				for (AirportTO B : destinations) {
					if (!A.getIata().equals(B.getIata())) {
						List<String> values = new ArrayList<>();
						values.add(A.getIata());
						if (B.getIata().equals("") || A.getIata().equals("")
								|| getDistance(A.getIata(), B.getIata()) > 300) {
							values.add(B.getIata());
						}
						if (values.contains("")) {
							values.remove("");
						}
						int weight = getWeight(start.getIata(), end.getIata(), values);
						List<AirportTO> layovers = getLayovers(destinations, values);
						if (weight < optimalWay.getWeight()
								&& (cantMaxPersonas(start.getIata(), end.getIata(), values) < capacity)
								&& getDistanceOfRoute(start.getIata(), end.getIata(), values) + 300 < distance) {
							System.out.println(A.getIata() + " - " + B.getIata() + " : " + weight);
							System.out.println(layovers.size() + " - " + values);
							optimalRoute.clear();
							optimalWay.setWeight(weight);
							optimalRoute.add(start);
							optimalRoute.addAll(layovers);
							optimalRoute.add(end);
						}

					}
				}
			}
		} else if (stops.size() == 1) {
			optimalWay.setWeight(getWeight(start.getIata(), end.getIata(),
					stops.stream().map(AirportTO::getIata).collect(Collectors.toList())));
			optimalRoute.add(start);
			optimalRoute.addAll(stops);
			optimalRoute.add(end);
			AirportTO stop = stops.get(0);
			for (AirportTO A : destinations) {
				for (AirportTO B : destinations) {
					if ((stop.getIata().equals(A.getIata()) || stop.getIata().equals(B.getIata()))
							&& !A.getIata().equals(B.getIata())) {
						List<String> values = new ArrayList<>();
						if (A.getIata().equals(stop.getIata())) {
							values.add(stop.getIata());
							if (stop.getIata().equals("") || B.getIata().equals("")
									|| getDistance(stop.getIata(), B.getIata()) > 300) {
								values.add(B.getIata());
							}

						}
						if (B.getIata().equals(stop.getIata())) {
							if (stop.getIata().equals("") || A.getIata().equals("")
									|| getDistance(A.getIata(), stop.getIata()) > 300) {
								values.add(A.getIata());
							}
							values.add(stop.getIata());
						}
						if (values.contains("")) {
							values.remove("");
						}

						int weight = getWeight(start.getIata(), end.getIata(), values);
						List<AirportTO> layovers = getLayovers(destinations, values);
						if (weight < optimalWay.getWeight()
								&& cantMaxPersonas(start.getIata(), end.getIata(), values) < capacity
								&& getDistanceOfRoute(start.getIata(), end.getIata(), values) + 300 < distance) {
							optimalRoute.clear();
							optimalWay.setWeight(weight);
							optimalRoute.add(start);
							optimalRoute.addAll(layovers);
							optimalRoute.add(end);
						}

					}
				}
			}
		} else {
			optimalRoute.add(start);
			optimalRoute.addAll(stops);
			optimalRoute.add(end);
			optimalWay.setWeight(getWeight(start.getIata(), end.getIata(),
					stops.stream().map(AirportTO::getIata).collect(Collectors.toList())));
		}

		optimalWay.setRoute(optimalRoute);
		return optimalWay;
	}

	private List<AirportTO> getLayovers(List<AirportTO> stops, List<String> values) {
		List<AirportTO> layovers = new ArrayList<>();
		for (String value : values) {
			layovers.addAll(stops.stream().filter(s -> s.getIata().equals(value)).collect(Collectors.toList()));
		}
		return layovers;
	}

	private int getDistance(String start, String end) {
		AirportTO startTO = getByCode(start);
		AirportTO endTO = getByCode(end);
		return Util.getDistance(startTO, endTO);
	}

	private AirportTO getByCode(String iata) {
		return airportsGlobal.stream().filter(a -> a.getIata().equals(iata)).findFirst().get();
	}

	private int getDistance(AirportTO start, AirportTO end) {
		return Util.getDistance(start, end);
	}

	private int getDistance(List<AirportTO> destinations) {
		int ret = 0;
		for (int i = 0; i < destinations.size() - 1; i++) {
			ret += Util.getDistance(destinations.get(i), destinations.get(i + 1));
		}
		return ret;
	}

	private String getWeekDay() {
		List<DayWeekDE> days = this.dayWeekRepository.findAll();
		List<DayWeekDE> filtersDays = days.stream().filter(d -> d.getQty() < 10).collect(Collectors.toList());
		if (filtersDays.isEmpty()) {
			filtersDays = resetWeekDays(days);
		}
		int dayId = new Random().nextInt(filtersDays.size());
		DayWeekDE day = filtersDays.get(dayId);
		day.setQty(day.getQty() + 1);
		this.dayWeekRepository.save(day);
		return day.getDay();
	}

	private List<DayWeekDE> resetWeekDays(List<DayWeekDE> days) {
		days.forEach(d -> d.setQty(0));
		this.dayWeekRepository.saveAll(days);
		return this.dayWeekRepository.findAll();
	}

	private int getDistance(String start, String end, List<String> layovers) {
		int ret = 0;
		List<AirportTO> destinations = new ArrayList<>();
		destinations.add(getByCode(start));
		destinations.addAll(layovers.stream().map(this::getByCode).collect(Collectors.toList()));
		destinations.add(getByCode(end));
		for (int i = 0; i < destinations.size() - 1; i++) {
			ret += Util.getDistance(destinations.get(i), destinations.get(i + 1));
		}
		return ret;
	}

	private int cantPersonas(String start, String end) {
		int cantPersonas = 0;
		List<Flight> flights = getPlanDeVuelos(start, end, flightsGlobal);
		int personasABordoProm = promedioDePersonas(flights);
		if (flights.size() > 3 && personasABordoProm > 30) {
			cantPersonas = personasABordoProm;
		} else {
			cantPersonas = promedioDePersonas;
		}
		return cantPersonas;
	}

	private int cantMaxPersonas(String start, String end, List<String> layovers) {
		int cant = 0;
		int cantMax = 0;
		if (layovers.isEmpty()) {
			return cantPersonas(start, end);
		}
		for (int i = 0; i < layovers.size() + 1; i++) {
			if (i == 0) {
				cant += cantPersonas(start, layovers.get(0)) + cantPersonas(start, end);
				if (layovers.size() > 1) {
					cant += cantPersonas(start, layovers.get(1));
				}
			}
			if (i == 1) {
				cant = cant - cantPersonas(start, layovers.get(0)) + cantPersonas(layovers.get(0), end);
				if (layovers.size() > 1) {
					cant += cantPersonas(layovers.get(1), layovers.get(1));
				}
			}
			if (i == 2 && layovers.size() > 1) {
				cant = cant - cantPersonas(layovers.get(0), layovers.get(1)) + cantPersonas(layovers.get(1), end);
			}
			if (cant > cantMax) {
				cantMax = cant;
			}
		}
		return cantMax;
	}

	private int cantMaxPersonas(FlightRouteResponseTO response) {
		String start = response.getRoute().get(0).getIata();
		String end = response.getRoute().get(response.getRoute().size() - 1).getIata();
		List<String> layovers = response.getRoute().stream().map(AirportTO::getIata).collect(Collectors.toList());
		layovers.remove(start);
		layovers.remove(end);
		return cantMaxPersonas(start, end, layovers);
	}

	public FlightRouteResponseApiTO generateFlightRouteApi(FlightRouteRequestApiTO request) throws Exception {
		if (request.getIncludeDestinations() == null || request.getIncludeDestinations().size() < 2) {
			throw new Exception("Se deben enviar almenos dos destinos a incluir");
		}
		FlightRouteRequestTO ret = new FlightRouteRequestTO();
		List<AirportTO> airportsToInclude = new ArrayList<>();
		List<AirportTO> airportsToExclude = new ArrayList<>();
		AircraftTO aircraft = new AircraftTO();

		if (request.getAircraft() != null && !request.getAircraft().isEmpty()) {
			aircraft = this.aircraftService.getAircraftByModel(request.getAircraft().trim());
			if (aircraft == null) {
				throw new Exception("Modelo de aeronave no existe");
			} else {
				ret.setAircraft(aircraft);
			}
		} else {
			ret.setAircraft(null);
		}

		for (String iata : request.getIncludeDestinations()) {
			AirportTO airport = this.airportService.getByCode(iata.trim());
			if (airport == null) {
				throw new Exception("Codigo de aeropuerto " + airport + " no existe");
			} else {
				airportsToInclude.add(airport);
			}
		}

		if (request.getExcludeDestinations() != null && !request.getExcludeDestinations().isEmpty()) {
			for (String iata : request.getExcludeDestinations()) {
				AirportTO airport = this.airportService.getByCode(iata.trim());
				if (airport == null) {
					throw new Exception("Codigo de aeropuerto " + airport + " no existe");
				} else {
					airportsToExclude.add(airport);
				}
			}
		}

		ret.setIncludeDestinations(airportsToInclude);
		ret.setExcludeDestinations(airportsToExclude);
		FlightRouteResponseTO response = this.generateFlightRoute(ret);
		FlightRouteResponseApiTO res = new FlightRouteResponseApiTO();
		if (response == null) {
			throw new Exception("No se pudo calcular la ruta optima");
		} else {
			res.setAircraft(response.getAircraft().getModel());
			res.setRoute(response.getRoute().stream().map(AirportTO::getIata).collect(Collectors.toList()));
			res.setOptimalAircrafts(
					response.getOptimalAircrafts().stream().map(AircraftTO::getModel).collect(Collectors.toList()));

			res.setDistance(response.getDistance());
			res.setDay(response.getDay());
			res.setPeopleEstimate(response.getPeopleEstimate());
			res.setCombustibleEstimado(response.getCombustibleEstimado());
			res.setLubricanteEstimado(response.getLubricanteEstimado());
		}
		return res;
	}

}
