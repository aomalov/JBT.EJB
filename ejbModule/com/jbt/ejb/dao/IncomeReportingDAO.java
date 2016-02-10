package com.jbt.ejb.dao;

import java.util.List;

import com.jbt.ejb.beans.Income;

public interface IncomeReportingDAO {
	public List<Income> getAll();
	
	public List<Income> getAllCustomerIncome(long custId);
	
	public List<Income> getAllCompanyIncome(long compId);
}
