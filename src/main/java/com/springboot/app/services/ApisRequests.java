package com.springboot.app.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.app.services.model.Flight;

@Service
public class ApisRequests {

	public List<Flight> getFlights() {
		String url = "https://proyecto-icarus.herokuapp.com/vuelos";
		String respuesta = "";
		List<Flight> ret = new ArrayList<>();
		try {
			respuesta = peticionHttpGet(url);
			ObjectMapper objectMapper = new ObjectMapper();
			ret = objectMapper.readValue(respuesta, new TypeReference<List<Flight>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;

	}

	public static String peticionHttpGet(String urlParaVisitar) throws Exception {
		// Esto es lo que vamos a devolver
		StringBuilder resultado = new StringBuilder();
		// Crear un objeto de tipo URL
		URL url = new URL(urlParaVisitar);

		// Abrir la conexión e indicar que será de tipo GET
		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		// Búferes para leer
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		String linea;
		// Mientras el BufferedReader se pueda leer, agregar contenido a resultado
		while ((linea = rd.readLine()) != null) {
			resultado.append(linea);
		}
		// Cerrar el BufferedReader
		rd.close();
		// Regresar resultado, pero como cadena, no como StringBuilder
		return resultado.toString();
	}

}
