package ozbank.ozbankapp.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Customers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	private String customerName;

	@Basic
	@Temporal(TemporalType.DATE)
	private Date customerDob;

	private int customerExperienceInMonths;
	private int customerCreditScore;

	@OneToOne(mappedBy = "customers")
	private Loans loan;

	public Loans getLoan() {
		return loan;
	}

	public void setLoan(Loans loan) {
		this.loan = loan;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getCustomerDob() {
		return customerDob;
	}

	public void setCustomerDob(Date customerDob) {
		this.customerDob = customerDob;
	}

	
	public int getCustomerExperienceInMonths() {
		return customerExperienceInMonths;
	}

	public void setCustomerExperienceInMonths(int customerExperienceInMonths) {
		this.customerExperienceInMonths = customerExperienceInMonths;
	}

	public int getCustomerCreditScore() {
		return customerCreditScore;
	}

	public void setCustomerCreditScore(int customerCreditScore) {
		this.customerCreditScore = customerCreditScore;
	}

}
