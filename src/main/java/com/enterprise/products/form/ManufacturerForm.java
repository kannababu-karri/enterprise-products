package com.enterprise.products.form;

import java.util.List;

import com.enterprise.products.entities.Manufacturer;

public class ManufacturerForm {
	
	private List<Manufacturer> resultManufacturers;
    private boolean showDetails;
	private Manufacturer manufacturer;
	private Long manufacturerId;
	
	public List<Manufacturer> getResultManufacturers() {
		return resultManufacturers;
	}
	public void setResultManufacturers(List<Manufacturer> resultManufacturers) {
		this.resultManufacturers = resultManufacturers;
	}
	public boolean isShowDetails() {
		return showDetails;
	}
	public void setShowDetails(boolean showDetails) {
		this.showDetails = showDetails;
	}
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Long getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
}
