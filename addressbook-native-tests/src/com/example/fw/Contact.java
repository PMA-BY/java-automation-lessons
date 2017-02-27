package com.example.fw;

public class Contact implements Comparable<Contact> {
	
	public String name;
	public String surname;
	public String email;
	public String internetContact;
	public String streetAddress;
	public String city;
	public String postalCode;
	public String country;
	public String phone;
	public String fax;
	public String cell;
	public String webPage;
	
	public Contact() {
		
	}
	

	@Override
	public String toString() {
		return "Contact [name=" + name + ", surname=" + surname + ", email=" + email + ", cell=" + cell + "]";
	}


	@Override
	public int compareTo(Contact other) {
		int result = 0;
		
		if (this.name == null)
			this.name = "";
		if (this.surname == null)
			this.surname = "";
		
		if (other.name == null)
			other.name = "";
		if (other.surname == null)
			other.surname = "";

		int resultLastName = this.surname.toLowerCase().compareTo(other.surname.toLowerCase());
		if (resultLastName != 0)
			return resultLastName;
		int resultFirstName = this.name.toLowerCase().compareTo(other.name.toLowerCase());
		if (resultFirstName != 0)
			return resultFirstName;
		return result;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
	
	//---------------setters-------------------------------
		public Contact setName(String name) {
			this.name = name;
			return this;
		}
		
		public Contact setSurname(String surname) {
			this.surname = surname;
			return this;
		}

		public Contact setEmail(String email) {
			this.email = email;
			return this;
		}
		
		public Contact setInternetContact(String internetContact) {
			this.internetContact = internetContact;
			return this;
		}
		
		public Contact setStreetAddress(String streetAddress) {
			this.streetAddress = streetAddress;
			return this;
		}
		
		public Contact setCity(String city) {
			this.city = city;
			return this;
		}
		
		public Contact setPostalCode(String postalCode) {
			this.postalCode = postalCode;
			return this;
		}
		
		public Contact setCountry(String country) {
			this.country = country;
			return this;
		}
		
		public Contact setPhone(String phone) {
			this.phone = phone;
			return this;
		}
		
		public Contact setFax(String fax) {
			this.fax = fax;
			return this;
		}
		
		public Contact setCell(String cell) {
			this.cell = cell;
			return this;
		}
		
		public Contact setWebPage(String webPage) {
			this.webPage = webPage;
			return this;
		}

	//---------------getters-------------------------------
		public String getName() {
			return name;
		}

		public String getSurname() {
			return surname;
		}
		
		public String getEmail() {
			return email;
		}
		
		public String getCity() {
			return city;
		}
		
		public String getInternetContact() {
			return internetContact;
		}
		
		public String getStreetAddress() {
			return streetAddress;
		}
		
		public String getPostalCode() {
			return postalCode;
		}
		
		public String getCountry() {
			return country;
		}
		
		public String getPhone() {
			return phone;
		}
		
		public String getFax() {
			return fax;
		}
		
		public String getCell() {
			return cell;
		}
		
		public String getWebPage() {
			return webPage;
		}

}
