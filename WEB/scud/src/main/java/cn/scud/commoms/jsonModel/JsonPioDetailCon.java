package cn.scud.commoms.jsonModel;

import java.util.Arrays;

/**
 * Created by Administrator on 2015/8/7.
 */
public class JsonPioDetailCon {

    private String title;
    private String[] location;
    private String city;
    private String create_time;
    private String geotable_id;
    private String address;
    private String province;
    private String district;
    private int city_id;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getLocation() {
        return location;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getGeotable_id() {
        return geotable_id;
    }

    public void setGeotable_id(String geotable_id) {
        this.geotable_id = geotable_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "JsonPioDetailCon{" +
                "title='" + title + '\'' +
                ", location=" + Arrays.toString(location) +
                ", city='" + city + '\'' +
                ", create_time='" + create_time + '\'' +
                ", geotable_id='" + geotable_id + '\'' +
                ", address='" + address + '\'' +
                ", province='" + province + '\'' +
                ", district='" + district + '\'' +
                ", city_id=" + city_id +
                ", id=" + id +
                '}';
    }
}
