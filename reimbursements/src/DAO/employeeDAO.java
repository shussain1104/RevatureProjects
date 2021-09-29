package DAO;
import Models.employee;
import Models.reimbursement;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import user.HibernateSessionFactory;

public class employeeDAO 
{

    public employeeDAO() { }
    
    /*public Session openCurrentSession()
    {
    	try
        {	
    		currentSession = getSessionFactory.getSession();
        }
    	catch(HibernateException e) 
    	{
			currentTransaction.rollback();
			e.printStackTrace();
		}

    	return currentSession;
    }
    public Session openCurrentSessionWithTransaction()
    {
    	try
        {
    		currentSession = getSessionFactory.getSession();
    		currentSession = ((SessionFactory) getSessionFactory.getSession()).openSession();
    		currentTransaction = currentSession.beginTransaction();
        }
    	catch(HibernateException e) 
    	{
			currentTransaction.rollback();
			e.printStackTrace();
		}

        return currentSession;
    }
    public void closeCurrentSession()
    {
    	try
        {
    		currentSession.close();
        }
    	catch(HibernateException e) 
    	{
			currentTransaction.rollback();
			e.printStackTrace();
		}
    }
    public void closeCurrentSessionwithTransaction()
    {
    	try
        {
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
    // Adds an employee record to the database
    public void save(employee employee)
    {
    	Session currentSession = null;
    	Transaction currentTransaction = null;
    	try
        {
    		currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		currentSession.save(employee);
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
    //Updates the employee record in the database
    public void update(employee employee)
    {
    	Session currentSession = null;
    	Transaction currentTransaction = null;
    	try
        {
    		currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		currentSession.update(employee);
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
    //Deletes the employee record  from the database
    public void delete(employee employee)
    {
    	Session currentSession = null;
    	Transaction currentTransaction = null;
    	try
        {
    		currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		currentSession.delete(employee);
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
    public employee FindById(String id)
    {
    	Session currentSession = null;
    	Transaction currentTransaction = null;
    	employee employeesubmission = null;
    	try
        {
    		currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		employeesubmission= currentSession.get(employee.class, id);
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
        return employeesubmission;
    }
    public employee findbyusername(String employee_username)
    {
    	Session currentSession = null;
    	Transaction currentTransaction = null;
    	employee user = null;
    	try
        {
    		currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		CriteriaBuilder cb = currentSession.getCriteriaBuilder();
    		CriteriaQuery<employee> cq = cb.createQuery(employee.class);
    		Root<employee> root = cq.from(employee.class);
    		cq.select(root).where(cb.equal(root.get("employee_username"), employee_username));
    		Query<employee> query = currentSession.createQuery(cq);
    		user = query.uniqueResult();
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
    	return user;
    }
    //Lists all the employees in the db
    @SuppressWarnings("unchecked")
    public List<employee> findAll()
    {
    	List<employee> employees = null;
    	Session currentSession = null;
    	Transaction currentTransaction = null;
        try
        {
        	currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		employees = currentSession.createQuery("FROM employee").getResultList();
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
        return employees;
    }
    public void deleteAll()
    {
    	Session currentSession = null;
    	   Transaction currentTransaction = null;
    	try
        {
    		currentSession = HibernateSessionFactory.getSession();
    		currentTransaction = currentSession.beginTransaction();
    		List<employee> employeeList = findAll();
    		for(employee employees: employeeList)
        	{
            	delete(employees);
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
