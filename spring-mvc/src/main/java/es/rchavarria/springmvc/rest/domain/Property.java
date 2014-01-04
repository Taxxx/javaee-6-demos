package es.rchavarria.springmvc.rest.domain;

public class Property {

	public String city;
	public String address;
	public int price;
	
	public Property() {
	}

	public Property(String city, String address, int price) {
		this.city = city;
		this.address = address;
		this.price = price;
	}
	
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
}
