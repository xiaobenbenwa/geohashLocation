package momo.com.tool;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class TestJson {

	public static void main(String[] args) {
		
		JSONObject jsonObj  = new JSONObject();
        jsonObj.put("name0", "zhangsan");
        jsonObj.put("sex1", "female");
        System.out.println(jsonObj);    //{"sex1":"female","name0":"zhangsan"}
        
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("11");
        jsonArray.put("22");
        jsonArray.put("33");
        System.out.println(jsonArray);    //["11","22","33"]
       
        
        //JSONArray
        String list[]={"11","22"};
        JSONArray ja = new JSONArray();
        ja.put(list);
        System.out.println(ja);    //["11","22","33"]
        
        //Map JSONObject
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("NO1", "��һ��");
        map.put("NO2", "�ڶ���");
        map.put("NO3", "�����");
        JSONArray jo = new JSONArray();
        jo.put(map);
        System.out.println(jo);    //���Ϊ��{"NO3":["11","22","33"],"NO2":"�ڶ���","NO1":"��һ��"}
        
		
        
	}

}
