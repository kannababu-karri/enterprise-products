package com.enterprise.products.form;

import java.util.List;

import com.enterprise.products.entities.Product;

public class ProductForm {
	
	private List<Product> resultProducts;
    private boolean showDetails;
	private Product product;
	private Long ProductId;
	
	public List<Product> getResultProducts() {
		return resultProducts;
	}
	public void setResultProducts(List<Product> resultProducts) {
		this.resultProducts = resultProducts;
	}
	public boolean isShowDetails() {
		return showDetails;
	}
	public void setShowDetails(boolean showDetails) {
		this.showDetails = showDetails;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Long getProductId() {
		return ProductId;
	}
	public void setProductId(Long productId) {
		ProductId = productId;
	}
}
