package com.team.dream.runlegwork.entity;

public class LocationInfo {
	private String cityName;

	private Location location;

	public static class Location {
		private double lat;
		private double lng;
		

		public Location(double latitude, double longitude) {
			super();
			this.lat = latitude;
			this.lng = longitude;
		}

		public double getLatitude() {
			return lat;
		}

		public void setLatitude(double latitude) {
			this.lat = latitude;
		}

		public double getLongitude() {
			return lng;
		}

		public void setLongitude(double longitude) {
			this.lng = longitude;
		}

	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
