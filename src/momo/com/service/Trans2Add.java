package momo.com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import momo.com.tool.Tool;
import org.json.JSONObject;

public class Trans2Add {

	public static void main(String[] args) {
		String GET_URL = "http://apis.map.qq.com/ws/geocoder/v1/?key=DZSBZ-ZRWAD-M3F4U-PNEQH-FL7V6-CYFPH&location=";
		String location = "";
		// String location = "18.23746074291328,109.5031169032923";
		String pathname = "/Users/zhoulu/Documents/workspace/UserLocation/data/ll";

		HttpURLConnection connection = null;
		BufferedReader reader = null;
		BufferedReader br = Tool.getFileReader(pathname);
		String line = "";
		int n=0;

		try {
			
	       while ((line=br.readLine())!= null) {
				//System.out.println(line);
				String[] strs = line.split(",");
				location = strs[2] + "," + strs[1];
				String getURL = GET_URL + location;
				connection = Tool.getHttpURLConnection(getURL);
				reader = Tool.getHttpGetReader(connection);
				
				
				JSONObject obj = Tool.getJsonObject(reader);
				System.out.print(obj);
				System.out.println("****************位置解析结果*******************"+n);
				JSONObject obj2 = obj.getJSONObject("result");
				JSONObject obj3 = obj2.getJSONObject("address_component");
				System.out.println("国家:" + obj3.getString("nation"));
				System.out.println("省份:" + obj3.getString("province"));
				System.out.println("城市:" + obj3.getString("city"));
				System.out.println("地区:" + obj3.getString("district"));
				System.out.println("街道:" + obj3.getString("street"));
				System.out.println("门牌号码:" + obj3.getString("street_number"));
				reader.close();
				connection.disconnect();
				n++;
			}



		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

	
}
