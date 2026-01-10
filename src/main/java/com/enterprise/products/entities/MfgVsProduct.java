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
@Table(name="mfgvsproduct")
public class MfgVsProduct {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mfgvsproductId")
	private Long mfgvsproductId;
	
	@Column(name = "manufacturerId", nullable = false)
	private Long manufacturerId;
	
	@Column(name = "productId", nullable = false)
	private Long productId;
	
	@OneToMany(mappedBy = "productId")  // mappedBy = field name in Customer
    private List<Product> products;

	public Long getMfgvsproductId() {
		return mfgvsproductId;
	}

	public void setMfgvsproductId(Long mfgvsproductId) {
		this.mfgvsproductId = mfgvsproductId;
	}

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
