package momo.com.tool;

import java.util.BitSet;
import java.util.HashMap;

public class MomoGeohash {

	private static int numbits = 15;

	final static char[] digits = { '0', '1', '2', '3'};

	final static HashMap<Character, Integer> lookup = new HashMap<Character, Integer>();
	static {
		int i = 0;
		for (char c : digits)
			lookup.put(c, i++);
	}

	public static void main(String[] args) {
		
		double[] latlon = new MomoGeohash().decode("132121013323203");
        System.out.println(latlon[0] + "," + latlon[1]);
        
        /*
        MomoGeohash e = new MomoGeohash();
        StringBuffer s = e.encode(-66.4453125,112.8515625,-90,90,-180,180);
        System.out.println(s);
        latlon = e.decode(s.toString());
        System.out.println(latlon[0] + ", " + latlon[1]);
        */

	}

	public double[] decode(String geohash) {
		StringBuilder buffer = new StringBuilder();
		
		for (char c : geohash.toCharArray()) {

			int i = lookup.get(c);
			buffer.append(Integer.toString(i));
		}
		System.out.println(buffer.toString());
		BitSet lonset = new BitSet();
		BitSet latset = new BitSet();

		//int j = 0;
		for (int i = 0; i < numbits; i++){
			boolean isSet = false;
				if(buffer.charAt(i) == '1' || buffer.charAt(i) == '3'){
					isSet = true;
				}
				else{
					isSet = false;
				}
			lonset.set(i, isSet);
		}
		
		//j = 0;
		for (int i = 1;i < numbits; i++) {
			boolean isSet = false;
				if(buffer.charAt(i) == '1' || buffer.charAt(i) == '2'){
					isSet = true;
				}
				else{
					isSet = false;
				}
			latset.set(i, isSet);
		}

		double lon = decode(lonset, -180,180);
		double lat = decode(latset, -90, 90);
		
          lat+=90;
		return new double[] { lat, lon };
	}

	private double decode(BitSet bs, double floor, double ceiling) {
		double mid = 0;
		for (int i = 0; i < bs.length(); i++) {
			mid = (floor + ceiling) / 2;
			if (bs.get(i))
				floor = mid;
			else
				ceiling = mid;
		}
		System.out.println(Double.toString(mid));
		return mid;
		
	}

	private StringBuffer encode(double lat, double lon, double bottom, double top,double left, double right) {
	StringBuffer buffer = new StringBuffer();
	for (int i = 0; i < numbits; i++) {
		double mid = (bottom + top) / 2;
		double center = (left + right) / 2;
		if (lat>= mid & lon<center){
			buffer.append('2');
			bottom = mid;
			right=center;
		} else if(lat>= mid & lon>=center) {
			buffer.append('1');
			bottom = mid;
			left=center;
			
		}
		else if(lat< mid & lon<center) {
			buffer.append('0');
			top = mid;
			right=center;
			
		}
		else if(lat< mid & lon>=center) {
			buffer.append('3');
			top = mid;
			left=center;
			
		}
	}
	return buffer;
}

}

	