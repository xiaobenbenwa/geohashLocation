package momo.com.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import momo.com.tool.Geohash;

public class Tool {

	static final String GET_URL = "http://apis.map.qq.com/ws/geocoder/v1/?key=DZSBZ-ZRWAD-M3F4U-PNEQH-FL7V6-CYFPH&location=";
	static final String FILE_PATH = "/Users/zhoulu/Documents/workspace/UserLocation/data/ll";
	static final int MAX_LAT = 90;
	static final int MIN_LAT = -90;
	static final int MAX_LNG = 180;
	static final int MIN_LNG = -180;
	static final int TOP_LAT_LNG = 200;
	static final int BOTTOM_LAT_LNG = -200;

	public static void main(String[] args) {

	}

	public static double[] getLatLngFromFile(String[] strs) {
		/**
		 * 拿到原始文件中的经纬度，如果不在正常范围内，取一个极大值200 如果字符串为空，则取一个极小值 -200 *
		 */
		double[] latlng = new double[2];
		if (strs[0] != "" & strs[1] != "") {
			if (Double.parseDouble(strs[0]) <= MAX_LAT
					& Double.parseDouble(strs[0]) >= MIN_LAT) {
				latlng[0] = Double.parseDouble(strs[0]);
			} else {
				latlng[0] = TOP_LAT_LNG;
			}
			if (Double.parseDouble(strs[1]) <= MAX_LNG
					& Double.parseDouble(strs[1]) >= MIN_LNG) {
				latlng[1] = Double.parseDouble(strs[1]);
			} else {
				latlng[1] = TOP_LAT_LNG;
			}
		} else {
			latlng[0] = BOTTOM_LAT_LNG;
			latlng[1] = BOTTOM_LAT_LNG;
		}
		return latlng;

	}

	public static String getURL(String[] strs) {
		String location = "";
		String geturl = "";
		if (strs[0] != "" & strs[1] != "") {
			location = strs[0] + "," + strs[1];
			geturl = GET_URL + location;

		} else {
			geturl = GET_URL;
		}
		return geturl;

	}

	public static String[] splitLine(String line) {

		String[] strs = new String[3];
		if (line != null) {
			String[] lines = line.split(",");
			strs[0] = lines[0].trim();
			strs[1] = lines[1].trim();
			strs[2] = lines[2].trim();
		}
		else {
			strs[0] = "";
			strs[1] = "";

			strs[2] = "";


		}

		return strs;
	}

	public static JSONObject getAddrJsonObject(JSONObject obj, String result,String address_component) {
		// System.out.println("****************获取位置解析结果*******************");\
		JSONObject obj1=null;
		JSONObject obj2=null;
		try{
			obj1 = obj.getJSONObject(result);
			obj2 = obj1.getJSONObject(address_component);
		}
		catch(JSONException e){
			
			e.printStackTrace();
			
		}
		return obj2;
	}

	public static String getStringFromJson(JSONObject addObj, String key) {
		/***
		 * 输入地理位置json对象和对应的key值，可以获取对应的字符串元素
		 * 
		 * 
		 */
		// System.out.println("****************获取json元素字符串*******************");
		String component = "";
		try{
			component = addObj.getString(key);
		}
		catch(JSONException e){
			
			e.printStackTrace();
			
		}
		return component;
		
		
	}

	public static String getNation(JSONObject obj) {
		// System.out.println("****************获取国家信息*******************");
		
		String str = "";
		try{
			str = Tool.getStringFromJson(obj, "nation");
		}
		catch(JSONException e){
			
			e.printStackTrace();
			
		}
		return str;

	}

	public static String getProvince(JSONObject obj) {
		// System.out.println("****************获取省份信息*******************");
		
		
		String str = "";
		try{
			str = Tool.getStringFromJson(obj, "province");
		}
		catch(JSONException e){
			
			e.printStackTrace();
			
		}
		return str;
		

	}

	public static String getCity(JSONObject obj) {
		// System.out.println("****************获取城市信息*******************");
		
		String str = "";
		try{
			str = Tool.getStringFromJson(obj, "city");
		}
		catch(JSONException e){
			
			e.printStackTrace();
			
		}
		return str;
		

	}

	public static String getDistrict(JSONObject obj) {
		// System.out.println("****************获取区县信息*******************");
		
		
		String str = "";
		try{
			str = Tool.getStringFromJson(obj, "district");
		}
		catch(JSONException e){
			
			e.printStackTrace();
			
		}
		return str;
		
	

	}

	public static String getStreet(JSONObject obj) {
		// System.out.println("****************获取街道信息*******************");
		
		String str = "";
		try{
			str = Tool.getStringFromJson(obj, "street");
		}
		catch(JSONException e){
			
			e.printStackTrace();
			
		}
		return str;

	}

	public static String getStreetNumber(JSONObject obj) {
		// System.out.println("****************获取门牌号码信息*******************");
		
		
		String str = "";
		try{
			str = Tool.getStringFromJson(obj, "street_number");
		}
		catch(JSONException e){
			
			e.printStackTrace();
			
		}
		return str;
		

		

	}

	public static double[] getLatLng(String geohashcode) {

		double[] latlon = new Geohash().decode(geohashcode);

		return latlon;

	}

	public static String getGeoHashCode(double lat, double lng) {

		String geohashcode = new Geohash().encode(lat, lng);
		return geohashcode;

	}

	public static BufferedReader getFileReader(String pathname) {
		BufferedReader br = null;
		try {

			File filename = new File(pathname);
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					filename)));
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return br;

	}

	public static BufferedReader getHttpGetReader(HttpURLConnection connection) {
		BufferedReader reader = null;
		try {

			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return reader;

	}

	public static JSONObject getJsonObject(BufferedReader reader) {
		String lines = "";
		String json = "";
		JSONObject obj = null;
		try {
			while ((lines = reader.readLine()) != null) {
				json = json + "\n" + lines;
			}
			obj = new JSONObject(json.toString());
		} catch (IOException e) {

			e.printStackTrace();
		}
         catch(JSONException e){
			
			e.printStackTrace();
			
		}

		return obj;

	}

	public static HttpURLConnection getHttpURLConnection(String url) {

		HttpURLConnection connection = null;
		try {
			URL getUrl = new URL(url);
			connection = (HttpURLConnection) getUrl.openConnection();
			connection.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return connection;

	}

}
