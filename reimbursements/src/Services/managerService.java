package Services;

import DAO.ManagerDAO;
import Models.manager;

import java.util.List;

public class managerService
{
    private ManagerDAO managerDAO;
    public managerService()
    {
        managerDAO = new ManagerDAO();
    }
    public ManagerDAO managerDAO()
    {
        return managerDAO;
    }
    public void persist(manager manager)
    {
        
        this.managerDAO.save(manager);
        
    }
    public void update(manager manager)
    {
        this.managerDAO.update(manager);
        
    }
    public manager findByID(String id)
    {
        manager manager = this.managerDAO.FindById(id);
        return manager;
    }
    public void delete(String id)
    {
        manager manager = managerDAO.FindById(id);
        managerDAO.delete(manager);
    }
    public List<manager> findAll()
    {
        List<manager> managers = this.managerDAO.findAll();
        return managers;
    }
    public void deleteAll()
    {
        this.managerDAO.deleteAll();
    }
}
