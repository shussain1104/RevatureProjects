package Services;

import DAO.employeeDAO;
import Models.employee;

import java.util.List;

public class employeeService {
    private employeeDAO employeeDAO;
    
    public employeeService()
    {
        employeeDAO = new employeeDAO();
    }
    
    public employeeDAO employeeDAO()
    {
        return employeeDAO;
    }
    
    public void persist(employee employee)
    { 
    	this.employeeDAO.save(employee);

    }
    public void update(employee employee)
    {
        this.employeeDAO.update(employee);
    }
    public employee findById(String id)
    {
    	employee employee = this.employeeDAO.FindById(id);
        return employee;
    }
    public employee findbyusername(String employee_username)
    {
    	employee userlogin = this.employeeDAO.findbyusername(employee_username);
    	return userlogin;
    }
    public void delete(String id)
    {
    		employee employee = this.employeeDAO.FindById(id);
    		employeeDAO.delete(employee);
    }
    public List<employee> findAll()
    { 
    	List<employee> employees = this.employeeDAO.findAll();
		return employees;
    }
    public void deleteAll()
    {
        this.employeeDAO.deleteAll();
    }
}