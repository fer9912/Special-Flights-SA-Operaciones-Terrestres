package com.springboot.app.utils;

import com.springboot.app.business.airport.model.AirportTO;

public class Util {

	public static boolean isEmptyOrNull(String any) {
		return any == null || any.trim().equals("");
	}

	public static int getDistance(AirportTO a, AirportTO b) {
		return calculateDistanceByHaversineFormula(a.getLon(), a.getLat(), b.getLon(), b.getLat());
	}

	private static int calculateDistanceByHaversineFormula(double lon1, double lat1, double lon2, double lat2) {

		double earthRadius = 6371; // km

		lat1 = Math.toRadians(lat1);
		lon1 = Math.toRadians(lon1);
		lat2 = Math.toRadians(lat2);
		lon2 = Math.toRadians(lon2);

		double dlon = (lon2 - lon1);
		double dlat = (lat2 - lat1);

		double sinlat = Math.sin(dlat / 2);
		double sinlon = Math.sin(dlon / 2);

		double a = (sinlat * sinlat) + Math.cos(lat1) * Math.cos(lat2) * (sinlon * sinlon);
		double c = 2 * Math.asin(Math.min(1.0, Math.sqrt(a)));

		double distanceInMeters = earthRadius * c * 1000;

		return (int) distanceInMeters / 1000;

	}
}
