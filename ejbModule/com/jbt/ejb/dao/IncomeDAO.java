package com.jbt.ejb.dao;

import javax.ejb.Local;

import com.jbt.ejb.beans.Income;

@Local
public interface IncomeDAO extends IncomeReportingDAO {

	public Income create(Income income);

}
	
