package com.enterprise.products.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="manufacturer")
public class Manufacturer {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "manufacturerId")
	private Long manufacturerId;
	
	@OneToMany(mappedBy = "productId")  // mappedBy = field name
    private List<Product> products;
	
	@Column(name = "mfgName", unique = true, nullable = false)
	private String mfgName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String zipExt;
	
	public Long getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public String getMfgName() {
		return mfgName;
	}
	public void setMfgName(String mfgName) {
		this.mfgName = mfgName;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getZipExt() {
		return zipExt;
	}
	public void setZipExt(String zipExt) {
		this.zipExt = zipExt;
	}
	
}
