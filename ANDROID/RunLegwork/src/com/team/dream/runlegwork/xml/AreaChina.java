package com.team.dream.runlegwork.xml;

import java.util.ArrayList;
import java.util.List;

public class AreaChina {

	private List<AreaProvince> areaProvinceList;

	public class AreaProvince {

		private String province_id;
		private String province_name;

		private List<AreaCity> areaCityList;

		public String getProvince_id() {
			return province_id;
		}

		public void setProvince_id(String province_id) {
			this.province_id = province_id;
		}

		public String getProvince_name() {
			return province_name;
		}

		public void setProvince_name(String province_name) {
			this.province_name = province_name;
		}

		public List<AreaCity> getAreaCityList() {
			return areaCityList;
		}

		public void setAreaCityList(List<AreaCity> areaCityList) {
			this.areaCityList = areaCityList;
		}

	}

	public class AreaCity {

		private String city_id;
		private String city_name;

		private List<Area> areaList;

		public String getCity_id() {
			return city_id;
		}

		public void setCity_id(String city_id) {
			this.city_id = city_id;
		}

		public String getCity_name() {
			return city_name;
		}

		public void setCity_name(String city_name) {
			this.city_name = city_name;
		}

		public List<Area> getAreaList() {
			return areaList;
		}

		public void setAreaList(List<Area> areaList) {
			this.areaList = areaList;
		}

	}

	public class Area {

		private String area_id;
		private String area_name;

		public String getArea_id() {
			return area_id;
		}

		public void setArea_id(String area_id) {
			this.area_id = area_id;
		}

		public String getArea_name() {
			return area_name;
		}

		public void setArea_name(String area_name) {
			this.area_name = area_name;
		}

	}

	public List<AreaProvince> getAreaProvinceList() {
		return areaProvinceList;
	}

	public void setAreaProvinceList(List<AreaProvince> areaProvinceList) {
		this.areaProvinceList = areaProvinceList;
	}

	public List<String> getProvinces() {

		List<String> provinceList = new ArrayList<String>();

		if (areaProvinceList == null || areaProvinceList.size() == 0)
			return provinceList;

		for (AreaProvince province : areaProvinceList) {

			String provinceName = province.getProvince_name();

			provinceList.add(provinceName);

		}

		return provinceList;
	}

	public List<String> getAllCitys() {

		List<String> cityList = new ArrayList<String>();

		if (areaProvinceList == null || areaProvinceList.size() == 0)
			return cityList;

		for (AreaProvince areaProvince : areaProvinceList) {

			String provinceName = areaProvince.getProvince_name();

			List<AreaCity> areaCityList = areaProvince.getAreaCityList();

			for (AreaCity areaCity : areaCityList) {

				String cityName = areaCity.getCity_name();

				if (cityName.equals("市辖区"))
					cityName = provinceName;

				if (!cityName.contains("市") || cityName.length() == 1)
					continue;

				cityList.add(cityName);

			}

		}

		return cityList;
	}

	public List<City> getAllCityData() {

		List<City> cities = new ArrayList<AreaChina.City>();

		if (areaProvinceList == null || areaProvinceList.size() == 0)
			return cities;

		for (AreaProvince areaProvince : areaProvinceList) {

			String provinceName = areaProvince.getProvince_name();
			// String proviceId = areaProvince.getProvince_id();

			List<AreaCity> areaCityList = areaProvince.getAreaCityList();

			for (AreaCity areaCity : areaCityList) {

				City city = new City();
				String cityName = areaCity.getCity_name();
				String cityid = areaCity.getCity_id();

				if (cityName.equals("市辖区")) {
					city.setCity_name(provinceName);
					city.setCity_id(cityid);
					cities.add(city);
					continue;
				}

				if (!cityName.contains("市"))
					continue;

				city.setCity_name(cityName);
				city.setCity_id(cityid);
				cities.add(city);

			}

		}

		return cities;
	}

	public class City {
		private String city_name;
		private String city_id;

		public String getCity_name() {
			return city_name;
		}

		public void setCity_name(String city_name) {
			this.city_name = city_name;
		}

		public String getCity_id() {
			return city_id;
		}

		public void setCity_id(String city_id) {
			this.city_id = city_id;
		}

	}

}
