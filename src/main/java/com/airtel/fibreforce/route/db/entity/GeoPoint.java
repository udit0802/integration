package com.airtel.fibreforce.route.db.entity;

import java.io.Serializable;

public class GeoPoint implements Serializable{

	
	@Override
	public String toString() {
		return "GeoPoint [lat=" + lat + ", lng=" + lng + "]";
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 4302238191491169157L;
	Double lat;
	Double lng;
	
	
	
}
