package com.springboot.app.business.flightroutemaster;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.springboot.app.business.aircraft.model.AircraftTO;
import com.springboot.app.services.model.Flight;

class FlightRouteMasterServiceTest {

	@Test
	public void testGetOilCProm() {
		FlightRouteMasterService flightRoute = new FlightRouteMasterService();
		ArrayList<Flight> vuelos = new ArrayList<Flight>();
		Flight vuelo1 = new Flight();
		vuelo1.setLubricantereal(100);
		vuelo1.setKilometrajereal(10000);
		Flight vuelo2 = new Flight();
		vuelo2.setLubricantereal(100);
		vuelo2.setKilometrajereal(5000);
		vuelos.add(vuelo1);
		vuelos.add(vuelo2);
		int oilProm = flightRoute.getOilCPromTesteable(vuelos);
		assertEquals(15, oilProm);
	}

	@Test
	public void testGetFuelCProm() {
		FlightRouteMasterService flightRoute = new FlightRouteMasterService();
		ArrayList<Flight> vuelos = new ArrayList<Flight>();
		Flight vuelo1 = new Flight();
		vuelo1.setLtscombustiblereal(5000);
		vuelo1.setKilometrajereal(250);
		Flight vuelo2 = new Flight();
		vuelo2.setLtscombustiblereal(5000);
		vuelo2.setKilometrajereal(100);
		vuelos.add(vuelo1);
		vuelos.add(vuelo2);
		int fuelProm = flightRoute.getFuelCPromTesteable(vuelos);
		assertEquals(35, fuelProm);
	}

	@Test
	public void testGetDistanciaPromedio() {
		FlightRouteMasterService flightRoute = new FlightRouteMasterService();
		ArrayList<Flight> vuelos = new ArrayList<Flight>();
		Flight vuelo1 = new Flight();
		vuelo1.setKilometrajereal(500);
		Flight vuelo2 = new Flight();
		vuelo2.setKilometrajereal(300);
		vuelos.add(vuelo1);
		vuelos.add(vuelo2);
		int distProm = flightRoute.getDistanciaPromedioTesteable(vuelos);
		assertEquals(400, distProm);
	}

	@Test
	public void testPromedioDePersonas() {
		FlightRouteMasterService flightRoute = new FlightRouteMasterService();
		ArrayList<Flight> vuelos = new ArrayList<Flight>();
		Flight vuelo1 = new Flight();
		vuelo1.setTotalpersonasabordo(250);
		Flight vuelo2 = new Flight();
		vuelo2.setTotalpersonasabordo(200);
		vuelos.add(vuelo1);
		vuelos.add(vuelo2);
		int promPersonas = flightRoute.promedioDePersonasTesteable(vuelos);
		assertEquals(225, promPersonas);

	}

	@Test
	public void testPromedioDePersonasVacio() {
		FlightRouteMasterService flightRoute = new FlightRouteMasterService();
		ArrayList<Flight> vuelos = new ArrayList<Flight>();
		int promPersonas = flightRoute.promedioDePersonasTesteable(vuelos);
		assertEquals(0, promPersonas);

	}

	@Test
	public void testGetLubricantCAverageByAircrafts() {
		FlightRouteMasterService flightRoute = new FlightRouteMasterService();
		ArrayList<AircraftTO> aeronaves = new ArrayList<AircraftTO>();
		AircraftTO aeronave1 = new AircraftTO();
		aeronave1.setLubricantConsumption(400);
		AircraftTO aeronave2 = new AircraftTO();
		aeronave2.setLubricantConsumption(200);
		aeronaves.add(aeronave1);
		aeronaves.add(aeronave2);
		int lubricantAverage = flightRoute.getLubricantCAverageByAircraftsTesteable(aeronaves);
		assertEquals(300, lubricantAverage);
	}

	@Test
	public void testgetFuelCAverageByAircrafts() {
		FlightRouteMasterService flightRoute = new FlightRouteMasterService();
		ArrayList<AircraftTO> aeronaves = new ArrayList<AircraftTO>();
		AircraftTO aeronave1 = new AircraftTO();
		aeronave1.setFuelConsumption(900);
		AircraftTO aeronave2 = new AircraftTO();
		aeronave2.setFuelConsumption(200);
		aeronaves.add(aeronave1);
		aeronaves.add(aeronave2);
		int fuelAverage = flightRoute.getFuelCAverageByAircraftsTesteable(aeronaves);
		assertEquals(550, fuelAverage);
	}
}
