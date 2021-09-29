package DAO;
import Models.employee;
import Models.manager;
import user.HibernateSessionFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ManagerDAO {
    private Session currentSession;
    private Transaction currentTransaction;

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
    // Adds a manager record to the database
    public void save(manager manager)
    {

    	Session currentSession = null;
    	Transaction currentTransaction = null;
    	try
        {
    		currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		currentSession.save(manager);
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
    //Updates the manager record in the database
    public void update(manager manager)
    {
    	Session currentSession = null;
    	Transaction currentTransaction = null;
    	try
        {
    		currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		currentSession.update(manager);
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
    //Deletes the manager record  from the database
    public void delete(manager manager)
    {
    	Session currentSession = null;
    	Transaction currentTransaction = null;
    	try
        {
    		currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		currentSession.delete(manager);
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
    //Finds the employee record  by its id
    public manager FindById(String id)
    {
    	Session currentSession = null;
    	Transaction currentTransaction = null;
    	manager managersubmission = null;
    	try
        {
    		currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		managersubmission= currentSession.get(manager.class, id);
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
    	return managersubmission;
    }
    //Lists all the employees in the db
    @SuppressWarnings("unchecked")
    public List<manager> findAll()
    {
    	List<manager> managers = null;
    	Session currentSession = null;
    	Transaction currentTransaction = null;
        try
        {
        	currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		managers = currentSession.createQuery("FROM manager").getResultList();
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
        return managers;
    }
    public void deleteAll()
    {
    	Session currentSession = null;
 	   Transaction currentTransaction = null;
 	   try
 	   {
 		   currentSession = HibernateSessionFactory.getSession();
 		   currentTransaction = currentSession.beginTransaction();
 		   List<manager> managerList = findAll();
 		   for(manager managers : managerList)
 		   {
 			   delete(managers);
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
