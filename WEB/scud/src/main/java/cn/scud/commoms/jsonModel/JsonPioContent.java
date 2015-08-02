package cn.scud.commoms.jsonModel;

import java.util.Arrays;

/**
 * Created by Administrator on 2015/7/21.
 */
public class JsonPioContent{

    public String tags;
    public int uid;
    public String province;
    public int geotable_id;
    public int modify_time;
    public String district;
    public int create_time;
    public  String city;
    public String[] location;
    public String address;
    public String title;
    public int coord_type;
    public int type;
    public int distance;
    public int weight;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getGeotable_id() {
        return geotable_id;
    }

    public void setGeotable_id(int geotable_id) {
        this.geotable_id = geotable_id;
    }

    public int getModify_time() {
        return modify_time;
    }

    public void setModify_time(int modify_time) {
        this.modify_time = modify_time;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String[] getLocation() {
        return location;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCoord_type() {
        return coord_type;
    }

    public void setCoord_type(int coord_type) {
        this.coord_type = coord_type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "JsonPioContent{" +
                "tags='" + tags + '\'' +
                ", uid=" + uid +
                ", province='" + province + '\'' +
                ", geotable_id=" + geotable_id +
                ", modify_time=" + modify_time +
                ", district='" + district + '\'' +
                ", create_time=" + create_time +
                ", city='" + city + '\'' +
                ", location=" + Arrays.toString(location) +
                ", address='" + address + '\'' +
                ", title='" + title + '\'' +
                ", coord_type=" + coord_type +
                ", type=" + type +
                ", distance=" + distance +
                ", weight=" + weight +
                '}';
    }
}
