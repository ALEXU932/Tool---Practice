
/**
 * @author Tewelde
 * @version 1.0
 * @created 02-Jan-2026 11:44:28 PM
 */
public class Address {

	private String addressId;
	private String city;
	private String country;
	private String zipCode;

	public Address(){

	}

	public void finalize() throws Throwable {

	}
	public void validate(){

	}
	
	// Getter and Setter methods
	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}//end Address