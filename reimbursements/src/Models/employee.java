package Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class employee
{
	@Id @GeneratedValue
	@Column(name = "employee_id")
    private int employee_id;
	@Column(name = "employee_status")
    private String employee_status;
	@Column(name = "employee_first_name")
    private String employee_first_name;
	@Column(name = "employee_last_name")
    private String employee_last_name;
	@Column(name = "reimbursement_request_id")
    private int reimbursement_request_id;
	@Column(name = "employee_username")
    private String employee_username;
	@Column(name = "employee_password")
    private String employee_password;
	@Column(name = "employee_email")
    private String employee_email;

	public employee() {}
    public employee(String employee_username, String employee_password) {
        this.employee_username = employee_username;
        this.employee_password = employee_password;
    }
    public employee(String employee_email, String employee_username, String employee_password) {
        this.employee_email = employee_email;
        this.employee_username = employee_username;
        this.employee_password = employee_password;
    }

    public employee(int employee_id, String employee_status, String employee_first_name, String employee_last_name,
                    int reimbursement_request_id)
    {
        this.employee_id = employee_id;
        this.employee_status = employee_status;
        this.employee_first_name = employee_first_name;
        this.employee_last_name = employee_last_name;
        this.reimbursement_request_id = reimbursement_request_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }
    public String getEmployee_status()
    {
        return employee_status;
    }
    public void setEmployee_status(String employee_status)
    {
        this.employee_status=employee_status;
    }
    public String getEmployee_first_name() {
        return employee_first_name;
    }

    public void setEmployee_first_name(String employee_first_name) {
        this.employee_first_name = employee_first_name;
    }

    public String getEmployee_last_name() {
        return employee_last_name;
    }

    public void setEmployee_last_name(String employee_last_name) {
        this.employee_last_name = employee_last_name;
    }

    public int getReimbursement_request_id() {
        return reimbursement_request_id;
    }

    public void setReimbursement_request_id(int reimbursement_request_id) {
        this.reimbursement_request_id = reimbursement_request_id;
    }

    public String getEmployee_username() {
        return employee_username;
    }

    public void setEmployee_username(String employee_username) {
        this.employee_username = employee_username;
    }
    public String getEmployee_password() {
    return employee_password;
    }

    public void setEmployee_password(String employee_password) {
        this.employee_password = employee_password;
    }

    public String getEmployee_email() {
        return employee_email;
    }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee_email == null) ? 0 : employee_email.hashCode());
		result = prime * result + ((employee_first_name == null) ? 0 : employee_first_name.hashCode());
		result = prime * result + employee_id;
		result = prime * result + ((employee_last_name == null) ? 0 : employee_last_name.hashCode());
		result = prime * result + ((employee_password == null) ? 0 : employee_password.hashCode());
		result = prime * result + ((employee_status == null) ? 0 : employee_status.hashCode());
		result = prime * result + ((employee_username == null) ? 0 : employee_username.hashCode());
		result = prime * result + reimbursement_request_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		employee other = (employee) obj;
		if (employee_email == null) {
			if (other.employee_email != null)
				return false;
		} else if (!employee_email.equals(other.employee_email))
			return false;
		if (employee_first_name == null) {
			if (other.employee_first_name != null)
				return false;
		} else if (!employee_first_name.equals(other.employee_first_name))
			return false;
		if (employee_id != other.employee_id)
			return false;
		if (employee_last_name == null) {
			if (other.employee_last_name != null)
				return false;
		} else if (!employee_last_name.equals(other.employee_last_name))
			return false;
		if (employee_password == null) {
			if (other.employee_password != null)
				return false;
		} else if (!employee_password.equals(other.employee_password))
			return false;
		if (employee_status == null) {
			if (other.employee_status != null)
				return false;
		} else if (!employee_status.equals(other.employee_status))
			return false;
		if (employee_username == null) {
			if (other.employee_username != null)
				return false;
		} else if (!employee_username.equals(other.employee_username))
			return false;
		if (reimbursement_request_id != other.reimbursement_request_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "employee [employee_id=" + employee_id + ", employee_status=" + employee_status
				+ ", employee_first_name=" + employee_first_name + ", employee_last_name=" + employee_last_name
				+ ", reimbursement_request_id=" + reimbursement_request_id + ", employee_username=" + employee_username
				+ ", employee_password=" + employee_password + ", employee_email=" + employee_email + "]";
	}

}
