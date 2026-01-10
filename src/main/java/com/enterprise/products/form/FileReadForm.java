package com.enterprise.products.form;

import java.util.List;

import com.enterprise.products.entities.OrderDocument;

public class FileReadForm {
	private List<OrderDocument> resultOrderDocuments;
    private boolean showDetails;
    
	public List<OrderDocument> getResultOrderDocuments() {
		return resultOrderDocuments;
	}
	public void setResultOrderDocuments(List<OrderDocument> resultOrderDocuments) {
		this.resultOrderDocuments = resultOrderDocuments;
	}
	public boolean isShowDetails() {
		return showDetails;
	}
	public void setShowDetails(boolean showDetails) {
		this.showDetails = showDetails;
	}
}
