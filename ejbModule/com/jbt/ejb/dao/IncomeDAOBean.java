package com.jbt.ejb.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jbt.ejb.beans.Income;
import com.jbt.ejb.beans.OperationType;

@Stateless
public class IncomeDAOBean implements IncomeDAO {

	@PersistenceContext(unitName="coupons")
	private EntityManager em;
	
	@Override
	public Income create(Income income) {
		em.persist(income);
		return income;
	}

	@Override
	public List<Income> getAll() {
		List<Income> list = em.createNamedQuery("Income_GetAll" ,Income.class).getResultList();
		return list;
	}

	@Override
	public List<Income> getAllCustomerIncome(long custId) {
		List<Income> list = em.createNamedQuery("Income_GetAllByCustomer",Income.class)
				.setParameter("custId",custId)
				.setParameter("description", OperationType.CUSTOMER_PURCHASE)
				.getResultList();
		return list;
	}

	@Override
	public List<Income> getAllCompanyIncome(long compId) {
		List<Income> list = em.createNamedQuery("Income_GetAllByCompany",Income.class)
				.setParameter("compId",compId)
				.setParameter("description", OperationType.CUSTOMER_PURCHASE)
				.getResultList();
		return list;
	}
}
