package com.jbt.ejb.delegate;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;

import com.jbt.ejb.beans.Income;
import com.jbt.ejb.dao.IncomeDAO;

@Stateless
public class IncomeDelegateBean implements IncomeDelegate {

	@EJB
	IncomeDAO daoStub;
	
	private QueueConnectionFactory factory;
	private QueueConnection connection;
	private QueueSession session;
	private Queue queue;
	private QueueSender sender;
	private ObjectMessage message;
	
	@Override
	public void storeIncome(Income income) {
		System.out.println("IncomeDelegateBean.StoreIncome()");
		try {
			message.setObject(income);
			sender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@PostConstruct
	public void init() {
		try {
			InitialContext ctx = new InitialContext();
			factory = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
			connection = factory.createQueueConnection();
			session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = (Queue) ctx.lookup("java:/jms/queue/ExpiryQueue");
			sender = session.createSender(queue);
			message = session.createObjectMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PreDestroy
	public void destroy() {
		try {
			sender.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Income> getAll() {
		return daoStub.getAll();
	}



	@Override
	public List<Income> getAllCustomerIncome(long custId) {
		return daoStub.getAllCustomerIncome(custId);
	}



	@Override
	public List<Income> getAllCompanyIncome(long compId) {
		return daoStub.getAllCompanyIncome(compId);
	}

	

}
