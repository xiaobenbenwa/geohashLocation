package momo.com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONException;
import org.json.JSONObject;

import momo.com.tool.Tool;

public class testLoc {

	public static void main(String[] args) {
	    String url = "http://apis.map.qq.com/ws/geocoder/v1/?key=DZSBZ-ZRWAD-M3F4U-PNEQH-FL7V6-CYFPH&location=";
		String location = "45.653625,133.123448";


		url+=location;
		HttpURLConnection connection = Tool.getHttpURLConnection(url);
		BufferedReader reader = Tool.getHttpGetReader(connection);
		
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
		
		System.out.println(obj);

	}

}
