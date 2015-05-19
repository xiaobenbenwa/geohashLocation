package momo.com.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class HttpGetRequest {

	public static void main(String[] args) {
		String GET_URL = "http://apis.map.qq.com/ws/geocoder/v1/?key=DZSBZ-ZRWAD-M3F4U-PNEQH-FL7V6-CYFPH&location=";
		String location = "45.764149,131.030196";

		try {
			String getURL = GET_URL + location;

			URL getUrl = new URL(getURL);

			HttpURLConnection connection = (HttpURLConnection) getUrl
					.openConnection();

			connection.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			System.out.println("=============================");
			System.out.println("Contents of get request");
			System.out.println("=============================");
			String json="";
			String lines;
			while ((lines = reader.readLine()) != null) {

				json=json+'\n'+lines;
			}
		    System.out.print(json);
			//
		    JSONObject obj = new JSONObject(json.toString());  
		    System.out.print(obj);
		    System.out.println("****************位置解析结果*******************");
		    JSONObject obj2 = obj.getJSONObject("result"); 
		    JSONObject obj3 = obj2.getJSONObject("address_component");
		    System.out.println("国家:"+obj3.getString("nation"));  
		    System.out.println("省份:"+obj3.getString("province"));
		    System.out.println("城市:"+obj3.getString("city"));
		    System.out.println("地区:"+obj3.getString("district"));
		    System.out.println("街道:"+obj3.getString("street"));
		    System.out.println("门牌号码:"+obj3.getString("street_number"));
			reader.close();
            connection.disconnect();
			System.out.println("=============================");
			System.out.println("Contents of get request ends");
			System.out.println("=============================");
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		


	}

}
