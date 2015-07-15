package com.team.dream.runlegwork.xml;

import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.content.res.XmlResourceParser;
import android.util.Xml;

/**
 * 
 * ClassName: AreaParse
 * Function: 
 * 			采用pull解析方式，解析xml，获取地区信息 
 * date: 2014年7月24日 上午10:46:19 
 * @author ernest.kong
 * @version  
 * @since JDK 1.7
 */
public class AreaParse {

	private XmlResourceParser mXmlResourceParser;
	
	private AreaChina areaChina = new AreaChina();
	
	public AreaParse (XmlResourceParser mXmlResourceParser){
		this.mXmlResourceParser = mXmlResourceParser;
		parse();
	}
	
	private void parse(){
		XmlPullParser parser = Xml.newPullParser();
		parser = mXmlResourceParser;
		
		AreaChina.AreaProvince province = null;
		AreaChina.AreaCity city = null;
		AreaChina.Area area = null;
		
		List<AreaChina.AreaProvince> areaProvinceList = null;
		List<AreaChina.AreaCity> areaCityList = null;
		List<AreaChina.Area> areaList = null;
		
		try {
			int evenType = parser.getEventType();

			while(evenType != XmlPullParser.END_DOCUMENT){
				switch (evenType) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					
					String tag = parser.getName();
					if (tag.equalsIgnoreCase("province")) {						
						province = new AreaChina().new AreaProvince();						
					} else if (province != null) {
						if (tag.equalsIgnoreCase("province_id")) {
							province.setProvince_id(parser.nextText());
						} else if (tag.equalsIgnoreCase("province_name")) {
							province.setProvince_name(parser.nextText());
						}
					}

					if (tag.equalsIgnoreCase("city")) {
						city = new AreaChina().new AreaCity();
					} else if (city != null) {
						if (tag.equalsIgnoreCase("city_id")) {
							city.setCity_id(parser.nextText());
						} else if (tag.equalsIgnoreCase("city_name")) {
							String cityname = parser.nextText();
							city.setCity_name(cityname);
						}
					}

					if (tag.equalsIgnoreCase("area")) {
						area = new AreaChina().new Area();
					} else if (area != null) {
						if (tag.equalsIgnoreCase("area_id")) {
							area.setArea_id(parser.nextText());
						} else if (tag.equalsIgnoreCase("area_name")) {
							area.setArea_name(parser.nextText());
						}
					}

					break;
				case XmlPullParser.END_TAG:

					if (parser.getName().equalsIgnoreCase("province") && province != null) {
						
						if(areaProvinceList == null) areaProvinceList = new ArrayList<AreaChina.AreaProvince>();
						
						areaProvinceList.add(province);
						
					}
					
					if (parser.getName().equalsIgnoreCase("city") && city != null) {
						
						if(areaCityList == null) areaCityList = new ArrayList<AreaChina.AreaCity>();
						
						areaCityList.add(city);

					}else if(parser.getName().equalsIgnoreCase("citys")){
						
						province.setAreaCityList(areaCityList);
						
						areaCityList = null;
					}
					
					if (parser.getName().equalsIgnoreCase("area") && area != null) {
						
						if(areaList == null) areaList = new ArrayList<AreaChina.Area>();
						
						areaList.add(area);		
						
					}else if(parser.getName().equalsIgnoreCase("areas")){
						
						city.setAreaList(areaList);
						
						areaList = null;
					}
					
					
					break;

				default:
					break;
				}
				parser.next();
				evenType = parser.getEventType();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		areaChina.setAreaProvinceList(areaProvinceList);
		
	}

	public AreaChina getAreaChina() {
		return areaChina;
	}

	public void setAreaChina(AreaChina areaChina) {
		this.areaChina = areaChina;
	}

	
}
