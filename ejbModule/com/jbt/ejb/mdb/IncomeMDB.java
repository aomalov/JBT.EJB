package com.jbt.ejb.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import com.jbt.ejb.beans.Income;
import com.jbt.ejb.dao.IncomeDAO;

/**
 * Message-Driven Bean implementation class for: IncomeMDB
 */
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/ExpiryQueue"),
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") 
		})
public class IncomeMDB implements MessageListener {

	@EJB
	private IncomeDAO stubDAO;
	
	public IncomeMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	 System.out.println("IncomeJMS.onMessage()");
         
         try{
        	 if(message instanceof ObjectMessage) {
        		 Income income = (Income)((ObjectMessage) message).getObject();
        		 stubDAO.create(income);
        	 }
         }catch(Exception e){
         	e.printStackTrace();
         } 
    }

}
