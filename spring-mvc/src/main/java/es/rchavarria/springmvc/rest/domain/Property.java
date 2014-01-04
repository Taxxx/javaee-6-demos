package es.rchavarria.springmvc.rest.domain;

public class Property {

	private String city;
	private String address;
	private int price;
	
	public Property() {}

	@Override
	public String toString() {
		return "[Property: (city: " + city + "), (address: " + address + "), (price: " + price + ")]";
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public static Property fromAttributes(String city, String address, int price) {
		Property property = new Property();
		
		property.city = city;
		property.address = address;
		property.price = price;
		
		return property;
	}
}
