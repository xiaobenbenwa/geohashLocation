package momo.com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import momo.com.dao.DataService;
import momo.com.tool.Tool;

import org.json.JSONObject;

import com.mysql.jdbc.Connection;

public class LocationService {

	static final String GET_URL = "http://apis.map.qq.com/ws/geocoder/v1/?key=DZSBZ-ZRWAD-M3F4U-PNEQH-FL7V6-CYFPH&location=";

	static final String FILE_PATH = "/Users/zhoulu/Documents/workspace/UserLocation/data/llas";


	static final int MAX_LAT = 90;
	static final int MIN_LAT = -90;
	static final int MAX_LNG = 180;
	static final int MIN_LNG = -180;
	static final int TOP_LAT_LNG = 200;
	static final int BOTTOM_LAT_LNG = -200;

	public static void main(String[] args) {
		BufferedReader br = Tool.getFileReader(FILE_PATH);
		LocationService locser = new LocationService();
		DataService ds = new DataService();
		Connection conn = ds.getConn();
		String line = "";
		int n = 0;
		try {

			while ((line = br.readLine()) != null) {

				String geohash = locser. getGeoCode(line);

				HashMap<String, String> latlng = locser.getLocMap(line);
				HashMap addMap = locser.getAddPart(line);
				// insert操作
				String lat = latlng.get("lat").toString();
				String lng = latlng.get("lng").toString();
				String nation = addMap.get("nation").toString();
				String province = addMap.get("province").toString();
				String city = addMap.get("city").toString();
				String district = addMap.get("district").toString();
				String street = addMap.get("street").toString();
				String street_number = addMap.get("street_number").toString();

				String[] para = {geohash, lat, lng, nation, province, city,
						district, street, street_number };
				String tablename = "location_info";
              

				ds.insert(tablename, para, conn);
				n++;
				System.out.println(n);

				/****
				 * 
				 * 
				 * 
				 * find操作
				 * 
				 * 
				 * ResultSet rs = ds.find("select * from STUDENT");
				 * 
				 * try { while (rs.next()) {
				 * System.out.println(rs.getString("HEIGHT")); } } catch
				 * (SQLException e) { e.printStackTrace(); }
				 * 
				 * finally {
				 * 
				 * ds.closeConnection(); }
				 */

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ds.closeConnection();
		}
	}

	public String getGeohashCode(String line) {
		String[] strs = new String[2];
		double[] latlng = new double[2];
		strs = Tool.splitLine(line);
		latlng = Tool.getLatLngFromFile(strs);
		String geohash = Tool.getGeoHashCode(latlng[0], latlng[1]);
		return geohash;

	}

	
	public String getGeoCode(String line) {
		String[] strs = Tool.splitLine(line);
		return strs[2];

	}


	public HashMap<String, Double> getDoubleLocMap(String line) {
		HashMap<String, Double> locMap = new HashMap<String, Double>();
		String[] strs = Tool.splitLine(line);
		locMap.put("lat", Double.parseDouble(strs[0]));
		locMap.put("lng", Double.parseDouble(strs[1]));
		return locMap;

	}

	public HashMap<String, String> getLocMap(String line) {
		HashMap<String, String> locMap = new HashMap<String, String>();
		String[] strs = Tool.splitLine(line);
		locMap.put("lat", strs[0]);
		locMap.put("lng", strs[1]);
		return locMap;

	}

	public HashMap<String, String> getAddPart(String line) {
		String[] strs = new String[2];
		HashMap<String, String> addMap = new HashMap<String, String>();
		String result = "result";
		String address_component = "address_component";

		strs = Tool.splitLine(line);
		String url = Tool.getURL(strs);
		HttpURLConnection connection = Tool.getHttpURLConnection(url);
		BufferedReader reader = Tool.getHttpGetReader(connection);
		JSONObject obj = Tool.getJsonObject(reader);

       if(obj.getInt("status") == 121){
    	   System.out.println("腾讯地图请求已达上限，停止服务！");
    	   System.exit(-1);
			
		}

		if (obj.getInt("status") == 0) {

			JSONObject addObj = Tool.getAddrJsonObject(obj, result,
					address_component);
			String nation = Tool.getNation(addObj);
			String province = Tool.getProvince(addObj);
			String city = Tool.getCity(addObj);
			String district = Tool.getDistrict(addObj);
			String street = Tool.getStreet(addObj);
			String street_number = Tool.getStreetNumber(addObj);
			addMap.put("nation", nation);
			addMap.put("province", province);
			addMap.put("city", city);
			addMap.put("district", district);
			addMap.put("street", street);
			addMap.put("street_number", street_number);

		} 
		
 else {

			addMap.put("nation", "");
			addMap.put("province", "");
			addMap.put("city", "");
			addMap.put("district", "");
			addMap.put("street", "");
			addMap.put("street_number", "");
		}
		return addMap;

	}

}
