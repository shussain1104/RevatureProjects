package DAO;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import Models.employee;
import Models.reimbursement;
import user.HibernateSessionFactory;

public class reimbursementDAO
{
    private Session currentSession;
    private Transaction currentTransaction;
    public reimbursementDAO(){}

    /*public Session openCurrentSession()
    {
    	currentSession = HibernateSessionFactory.getSession();
        currentSession = ((SessionFactory) HibernateSessionFactory.getSession()).openSession();
        return currentSession;
    }
    public Session openCurrentSessionWithTransaction()
    {
    	currentSession = HibernateSessionFactory.getSession();
        currentSession = ((SessionFactory) HibernateSessionFactory.getSession()).openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
    public void closeCurrentSession()
    {
        currentSession.close();
    }
    public void closeCurrentSessionwithTransaction()
    {
        currentTransaction.commit();
        currentSession.close();
    }
    public Session getCurrentSession()
    {
        return currentSession;
    }
    public void setCurrentSession(Session currentSession)
    {
        this.currentSession = currentSession;
    }
    public Transaction getCurrentTransaction()
    {
        return currentTransaction;
    }
    public void setCurrentTransaction(Transaction currentTransaction)
    {
        this.currentTransaction = currentTransaction;
    }*/
    // Adds a reimbursement submission to the database
    public void save(reimbursement submission)
    {
    	Session currentSession = null;
    	Transaction currentTransaction = null;
    	try
        {
    		currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		currentSession.save(submission);
    		currentTransaction.commit();
        }
    	catch(HibernateException e) {
			currentTransaction.rollback();
			e.printStackTrace();
		}
    	finally
    	{
    		currentSession.close();
    	}
    }
    //Updates the submission in the database
    public void update(reimbursement submission)
    {
    	Session currentSession = null;
    	Transaction currentTransaction = null;
    	try
        {
    		currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		currentSession.update(submission);
    		currentTransaction.commit();
        }
    	catch(HibernateException e) 
    	{
			currentTransaction.rollback();
			e.printStackTrace();
		}
    	finally
    	{
    		currentSession.close();
    	}
    }
    //Deletes the reimbursement submission from the database
    public void delete(reimbursement submission)
    {
    	Session currentSession = null;
    	Transaction currentTransaction = null;
    	try
        {
    		currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		currentSession.delete(submission);
    		currentTransaction.commit();
        }
    	catch(HibernateException e) 
    	{
			currentTransaction.rollback();
			e.printStackTrace();
		}
    	finally
    	{
    		currentSession.close();
    	}
    }
    //Finds the reimbursement by its id
    public reimbursement FindById(String id)
    {
    	Session currentSession = null;
    	Transaction currentTransaction = null;
    	reimbursement submission = null;
    	try
        {
    		currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		submission= currentSession.get(reimbursement.class, id);
    		currentTransaction.commit();
        }
    	catch(HibernateException e) 
    	{
			currentTransaction.rollback();
			e.printStackTrace();
		}
    	finally
    	{
    		currentSession.close();
    	}
        return submission;
    }
    //Lists all the reimbursements in the db
    @SuppressWarnings("unchecked")
    public List<reimbursement> findAll()
    {
    	List<reimbursement> reimbursements = new ArrayList<reimbursement>();
    	Session currentSession = null;
    	Transaction currentTransaction = null;
        try
        {
        	currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		reimbursements = currentSession.createQuery("FROM reimbursement").getResultList();
        	currentTransaction.commit();
        }
    	catch(HibernateException e) 
    	{
			currentTransaction.rollback();
			e.printStackTrace();
		}
        finally
    	{
    		currentSession.close();
    	}
        return reimbursements;
    }
    public void deleteAll()
    {
    	Session currentSession = null;
 	   	Transaction currentTransaction = null;
 	   	try
 	   	{
 		   currentSession = HibernateSessionFactory.getSession();
 		   currentTransaction = currentSession.beginTransaction();
 		   List<reimbursement> reimbursementList = findAll();
 		   for(reimbursement reimbursements: reimbursementList)
 		   {
 			   delete(reimbursements);
 		   }
 		   currentTransaction.commit();
 	   }
 	   catch(HibernateException e) 
 	   {
			currentTransaction.rollback();
			e.printStackTrace();
 	   }
 	   finally
 	   {
 		   currentSession.close();
 	   }
    }


}
