package es.rchavarria.springmvc.rest.domain;

public class Property {

	public final String city;
	public final String address;
	public final int price;
	
	public Property(String city, String address, int price) {
		this.city = city;
		this.address = address;
		this.price = price;
	}
}
