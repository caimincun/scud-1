package com.team.dream.runlegwork.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.team.dream.runlegwork.R;
import com.team.dream.runlegwork.entity.CityModel;
import com.team.dream.runlegwork.xml.AreaChina;
import com.team.dream.runlegwork.xml.AreaChina.AreaCity;
import com.team.dream.runlegwork.xml.AreaChina.AreaProvince;
import com.team.dream.runlegwork.xml.AreaChina.City;
import com.team.dream.runlegwork.xml.AreaParse;

public class CityData {

	private static AreaChina AREA_CHINA;
	/**
	 * 热门城市
	 */
	private String[] hotCitys = new String[] { "北京市", "天津市", "上海市", "成都市", "重庆市" };

	/**
	 * 城市名称多音字处理
	 */
	private String[] polyphoneCitys = new String[] { "重庆市" };

	public static void initChinaArea(Context context) {
		XmlResourceParser res = context.getResources().getXml(R.xml.area_address);
		AreaParse mAreaParse = new AreaParse(res);
		AREA_CHINA = mAreaParse.getAreaChina();
	}

	public List<CityModel> setHotCityModel() {
		List<CityModel> cityModelList = new ArrayList<CityModel>();
		for (String city : hotCitys) {
			CityModel hotCityModel = new CityModel();
			hotCityModel.setName(city);
			hotCityModel.setSortLetters("#");
			cityModelList.add(hotCityModel);
		}
		return cityModelList;
	}

	/**
	 * 
	 * isPolyphone: 判断是否为多音字，设置角标
	 * 
	 * @author ernest.kong
	 * @param cityName
	 * @return
	 * @since JDK 1.7
	 */
	public String isPolyphone(String cityName, String[] polyphones) {
		if (polyphones == null || polyphoneCitys.length == 0)
			return null;

		for (String polyphone : polyphones) {
			if (polyphone.equals(cityName))
				return "C";

		}
		return null;
	}

	public void sortCityModel(List<CityModel> cityModelList) {
		LettersComparator cityLettersComparator = new LettersComparator();
		Collections.sort(cityModelList, cityLettersComparator);
	}

	class LettersComparator implements Comparator<CityModel> {

		@Override
		public int compare(CityModel lhs, CityModel rhs) {

			String lLetters = lhs.getSortLetters();
			String rLetters = rhs.getSortLetters();

			if (lLetters.equals("#") || rLetters.equals("#"))
				return 27;

			else
				return lLetters.compareTo(rLetters);

		}

	}

	public static String isLetter(String s) {
		char[] list = new char[s.length()];
		for (int j = 0; j < s.length(); j++) {
			char c = s.charAt(j);
			int i = (int) c;
			if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122)) {
				c = Character.toLowerCase(c);

			}
			list[j] = c;
		}
		return new String(list);
	}

	public static boolean isAcronym(String word) {
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!Character.isLowerCase(c)) {
				return false;
			}
		}
		return true;
	}

	public static String getCityCodeByCityName(String cityName) {

		if (StringUtils.isEmpty(cityName)) {
			cityName = "成都";
		}

		for (AreaProvince province : AREA_CHINA.getAreaProvinceList()) {
			for (AreaCity city : province.getAreaCityList()) {
				if (city.getCity_name().startsWith(cityName)) {
					return city.getCity_id();
				}
			}
		}
		return null;
	}

	public static String getCityCode(String cityName) {

		for (City city : AREA_CHINA.getAllCityData()) {
			if (city.getCity_name().equals(cityName)) {
				return city.getCity_id();
			}
		}
		return null;
	}

}
