package Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "manager")
public class manager extends employee
{
	//@Id @GeneratedValue
	//@Column(name = "manager_id")
    private int manager_id;
	//@Column(name = "manager_reimbursement_approval_status")
    private boolean manager_reimbursement_approval_status;

    public manager(String employee_username, String employee_password, String employee_email) {
        super(employee_username, employee_password,employee_email);
    }

    public manager(int employee_id, String employee_status, String employee_first_name, String employee_last_name,
                   int manager_id, int reimbursement_request_id,
                   Date reimbursement_date, boolean manager_reimbursement_approval_status) {
        super(employee_id, employee_status, employee_first_name, employee_last_name, reimbursement_request_id);
        this.manager_id = manager_id;
        this.manager_reimbursement_approval_status = manager_reimbursement_approval_status;
    }

    public int getManager_id() {
    return manager_id;
}

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public boolean isManager_reimbursement_approval_status() {
        return manager_reimbursement_approval_status;
    }

    public void setManager_reimbursement_approval_status(boolean manager_reimbursement_approval_status) {
        this.manager_reimbursement_approval_status = manager_reimbursement_approval_status;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + manager_id;
		result = prime * result + (manager_reimbursement_approval_status ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		manager other = (manager) obj;
		if (manager_id != other.manager_id)
			return false;
		if (manager_reimbursement_approval_status != other.manager_reimbursement_approval_status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "manager [manager_id=" + manager_id + ", manager_reimbursement_approval_status="
				+ manager_reimbursement_approval_status + "]";
	}
}
