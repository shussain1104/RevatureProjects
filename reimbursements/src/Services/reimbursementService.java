package Services;

import DAO.reimbursementDAO;
import Models.reimbursement;

import java.util.List;

public class reimbursementService
{
    private reimbursementDAO reimbursementDAO;
    public reimbursementService()
    {
        reimbursementDAO = new reimbursementDAO();
    }
    public reimbursementDAO reimbursementDAO()
    {
        return reimbursementDAO;
    }
    //Opens a new entry into db, inserts the entry, then closes the connection
    public void persist(reimbursement submission)
    {
       this.reimbursementDAO.save(submission);
    }
    //Opens a new connection to the db, updates the data entry object, closes the connection
    public void update(reimbursement submission)
    {
        this.reimbursementDAO.update(submission);
    }
    //Finds the employee reimbursement by its reimbursement id
    public reimbursement findByID(String id)
    {
        reimbursement submission = this.reimbursementDAO.FindById(id);
        return submission;
    }
    //////////////////////////////////////////////////////////
    public void delete(String id)
    {
      
        reimbursement reimbursement = this.reimbursementDAO.FindById(id);
        this.reimbursementDAO.delete(reimbursement);
    }
    /////////////////////////////////////////////////////////
    public List<reimbursement> findAll()
    {
        List<reimbursement> reimbursements = this.reimbursementDAO.findAll();
        return reimbursements;
    }
    //////////////////////////////////////////////////////////
    public void deleteAll()
    {
        this.reimbursementDAO.deleteAll();
    }
}
