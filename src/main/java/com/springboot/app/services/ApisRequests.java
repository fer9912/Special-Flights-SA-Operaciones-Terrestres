package com.springboot.app.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.app.services.model.CrewXFlight;
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

	public List<Flight> getFlight(String idVuelo) {
		String respuesta = "";
		List<Flight> ret = new ArrayList<>();
		try {
			respuesta = peticionHttpPostVuelo(idVuelo);
			ObjectMapper objectMapper = new ObjectMapper();
			ret = objectMapper.readValue(respuesta, new TypeReference<List<Flight>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;

	}

	public List<CrewXFlight> getCrew(String idVuelo) {
		String respuesta = "";
		List<CrewXFlight> ret = new ArrayList<>();
		try {
			respuesta = peticionHttpPost(idVuelo);
			ObjectMapper objectMapper = new ObjectMapper();
			ret = objectMapper.readValue(respuesta, new TypeReference<List<CrewXFlight>>() {
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

		// Abrir la conexion e indicar que sera de tipo GET
		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		// Buferes para leer
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

	public static String peticionHttpPostVuelo(String idVuelo) throws Exception {

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("https://proyecto-icarus.herokuapp.com/obtenerPlanVueloRes");
		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("idvuelo", idVuelo));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// writing error to Log
			e.printStackTrace();
		}

		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity respEntity = response.getEntity();

			if (respEntity != null) {
				// EntityUtils to get the response content
				String content = EntityUtils.toString(respEntity);
				return content;
			}
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}
		return null;

	}

	public static String peticionHttpPost(String idVuelo) throws Exception {

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("https://proyecto-icarus.herokuapp.com/tripulacionPorIdVuelo");
		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("idvuelo", idVuelo));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// writing error to Log
			e.printStackTrace();
		}

		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity respEntity = response.getEntity();

			if (respEntity != null) {
				// EntityUtils to get the response content
				String content = EntityUtils.toString(respEntity);
				return content;
			}
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}
		return null;
	}

}
