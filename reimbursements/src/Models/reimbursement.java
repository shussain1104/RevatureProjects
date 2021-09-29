package Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursement")
public class reimbursement
{
	@Id @GeneratedValue
	@Column(name = "reimbursement_id")
    private int reimbursement_id;
	@Column(name="reimbursement_employee_id")
    private int reimbursement_employee_id;
	@Column(name="reimbursement_date")
    private Date reimbursement_date;
	@Column(name="reimbursement_amount")
    private double reimbursement_amount;
	@Column(name="reimbursement_status")
    private boolean reimbursement_status;
	@Column(name="reimbursement_type")
    private String reimbursement_type;
	@Column(name="reimbursement_comments")
    private String reimbursement_comments;
	@Column(name="reimbursement_purpose")
    private String reimbursement_purpose;

    public reimbursement(int reimbursement_id, int reimbursement_employee_id, Date reimbursement_date,
			double reimbursement_amount, boolean reimbursement_status, String reimbursement_type,
			String reimbursement_comments, String reimbursement_purpose) {
		super();
		this.reimbursement_id = reimbursement_id;
		this.reimbursement_employee_id = reimbursement_employee_id;
		this.reimbursement_date = reimbursement_date;
		this.reimbursement_amount = reimbursement_amount;
		this.reimbursement_status = reimbursement_status;
		this.reimbursement_type = reimbursement_type;
		this.reimbursement_comments = reimbursement_comments;
		this.reimbursement_purpose = reimbursement_purpose;
	}
    public reimbursement(int reimbursement_id, Date reimbursement_date, String reimbursement_purpose,
			double reimbursement_amount, String reimbursement_type,
			String reimbursement_comments,boolean reimbursement_status) {
		super();
		this.reimbursement_id = reimbursement_id;
		this.reimbursement_date = reimbursement_date;
		this.reimbursement_purpose = reimbursement_purpose;
		this.reimbursement_amount = reimbursement_amount;
		this.reimbursement_type = reimbursement_type;
		this.reimbursement_comments = reimbursement_comments;
		this.reimbursement_status = reimbursement_status;
	}
    public reimbursement(int reimbursement_id, Date reimbursement_date, String reimbursement_purpose,
			double reimbursement_amount, String reimbursement_type,
			String reimbursement_comments) {
		super();
		this.reimbursement_id = reimbursement_id;
		this.reimbursement_date = reimbursement_date;
		this.reimbursement_purpose = reimbursement_purpose;
		this.reimbursement_amount = reimbursement_amount;
		this.reimbursement_type = reimbursement_type;
		this.reimbursement_comments = reimbursement_comments;
	}
    public reimbursement(int reimbursement_id, String reimbursement_purpose,
			double reimbursement_amount, String reimbursement_type,
			String reimbursement_comments) {
		super();
		this.reimbursement_id = reimbursement_id;
		this.reimbursement_purpose = reimbursement_purpose;
		this.reimbursement_amount = reimbursement_amount;
		this.reimbursement_type = reimbursement_type;
		this.reimbursement_comments = reimbursement_comments;
	}


	public int getReimbursement_id() {
        return reimbursement_id;
    }

    public void setReimbursement_id(int reimbursement_id) {
        this.reimbursement_id = reimbursement_id;
    }

    public int getReimbursement_employee_id()
    {
        return reimbursement_employee_id;
    }
    public void setReimbursement_employee_id(int reimbursement_employee_id)
    {
        this.reimbursement_employee_id = reimbursement_employee_id;
    }
    public Date getReimbursement_date() {
        return reimbursement_date;
    }

    public void setReimbursement_date(Date reimbursement_date) {
        this.reimbursement_date = reimbursement_date;
    }

    public double getReimbursement_amount() {
        return reimbursement_amount;
    }

    public void setReimbursement_amount(double reimbursement_amount) {
        this.reimbursement_amount = reimbursement_amount;
    }

    public boolean isReimbursement_status() {
        return reimbursement_status;
    }

    public void setReimbursement_status(boolean reimbursement_status) {
        this.reimbursement_status = reimbursement_status;
    }

    public String getReimbursement_type() {
        return reimbursement_type;
    }

    public void setReimbursement_type(String reimbursement_type) {
        this.reimbursement_type = reimbursement_type;
    }

    public String getReimbursement_comments() {
        return reimbursement_comments;
    }

    public void setReimbursement_comments(String reimbursement_comments) {
        this.reimbursement_comments = reimbursement_comments;
    }

    public String getReimbursement_purpose() {
        return reimbursement_purpose;
    }

    public void setReimbursement_purpose(String reimbursement_purpose) {
        this.reimbursement_purpose = reimbursement_purpose;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(reimbursement_amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((reimbursement_comments == null) ? 0 : reimbursement_comments.hashCode());
		result = prime * result + ((reimbursement_date == null) ? 0 : reimbursement_date.hashCode());
		result = prime * result + reimbursement_employee_id;
		result = prime * result + reimbursement_id;
		result = prime * result + ((reimbursement_purpose == null) ? 0 : reimbursement_purpose.hashCode());
		result = prime * result + (reimbursement_status ? 1231 : 1237);
		result = prime * result + ((reimbursement_type == null) ? 0 : reimbursement_type.hashCode());
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
		reimbursement other = (reimbursement) obj;
		if (Double.doubleToLongBits(reimbursement_amount) != Double.doubleToLongBits(other.reimbursement_amount))
			return false;
		if (reimbursement_comments == null) {
			if (other.reimbursement_comments != null)
				return false;
		} else if (!reimbursement_comments.equals(other.reimbursement_comments))
			return false;
		if (reimbursement_date == null) {
			if (other.reimbursement_date != null)
				return false;
		} else if (!reimbursement_date.equals(other.reimbursement_date))
			return false;
		if (reimbursement_employee_id != other.reimbursement_employee_id)
			return false;
		if (reimbursement_id != other.reimbursement_id)
			return false;
		if (reimbursement_purpose == null) {
			if (other.reimbursement_purpose != null)
				return false;
		} else if (!reimbursement_purpose.equals(other.reimbursement_purpose))
			return false;
		if (reimbursement_status != other.reimbursement_status)
			return false;
		if (reimbursement_type == null) {
			if (other.reimbursement_type != null)
				return false;
		} else if (!reimbursement_type.equals(other.reimbursement_type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "reimbursement [reimbursement_id=" + reimbursement_id + ", reimbursement_employee_id="
				+ reimbursement_employee_id + ", reimbursement_date=" + reimbursement_date + ", reimbursement_amount="
				+ reimbursement_amount + ", reimbursement_status=" + reimbursement_status + ", reimbursement_type="
				+ reimbursement_type + ", reimbursement_comments=" + reimbursement_comments + ", reimbursement_purpose="
				+ reimbursement_purpose + "]";
	}
}
