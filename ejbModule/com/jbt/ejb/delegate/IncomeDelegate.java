package com.jbt.ejb.delegate;

import javax.ejb.Local;

import com.jbt.ejb.beans.Income;
import com.jbt.ejb.dao.IncomeReportingDAO;

@Local
public interface IncomeDelegate extends IncomeReportingDAO {
  void storeIncome(Income income);
}
