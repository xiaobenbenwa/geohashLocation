package momo.com.model;

public class LocationInfo {
	
	private String geohashcode;
	private double lat;
	private double lng;
	private String nation;
	private String province;
	private String city;
	private String district;
	private String street;
	private String street_number;
	
	
	LocationInfo(String geohashcode,double lat,double lng,String nation,
			    String province,String city,String district,String street,String street_number){
		
		this.geohashcode=geohashcode;
		this.lat=lat;
		this.lng=lng;
		this.nation=nation;
		this.province=province;
		this.city=city;
		this.district=district;
		this.street=street;
		this.street_number=street_number;
		
		}
	

	public static void main(String[] args) {
		

	}

}
