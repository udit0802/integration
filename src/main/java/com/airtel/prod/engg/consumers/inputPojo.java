package com.airtel.prod.engg.consumers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class inputPojo {

	double latitude;
	
	double longitude;
	
	long userId;
	
	String travelId;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getTravelId() {
		return travelId;
	}

	public void setTravelId(String travelId) {
		this.travelId = travelId;
	}

	@Override
	public String toString() {
		return "inputPojo [latitude=" + latitude + ", longitude=" + longitude + ", userId=" + userId + ", travelId="
				+ travelId + "]";
	}
}
