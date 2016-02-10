package com.jbt.ejb.beans;

public enum OperationType {
  CUSTOMER_PURCHASE(0,100), COMPANY_CREATE(100,0), COMPANY_UPDATE(10,0);
	
  private double price;
  private int percent;
  
private OperationType(double price, int percent) {
	this.price = price;
	this.percent = percent;
}

public double getPrice() {
	return price;
}

public int getPercent() {
	return percent;
}

public double getInvoiceSum(double tranSum) {
	return price+percent/100*tranSum;
}

  
}
