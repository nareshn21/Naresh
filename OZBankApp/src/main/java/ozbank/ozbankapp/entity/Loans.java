package ozbank.ozbankapp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Loans {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;

	private String loanStatus;
	private Double loanAmount;
	private int loanPayInYears;
	
	public int getLoanPayInYears() {
		return loanPayInYears;
	}

	public void setLoanPayInYears(int loanPayInYears) {
		this.loanPayInYears = loanPayInYears;
	}

	private Double loanIntrestRate;
	

	@OneToOne
	@JoinColumn(name = "customer_id")
	Customers customers;

	@JsonManagedReference
	@OneToMany(mappedBy = "loans")
	List<Emi> emi;

	@ManyToOne
	@JoinColumn(name = "loanApproverManagerId")
	private BankingPersonnels bankingPersonnel;

	
	
	public List<Emi> getEmi() {
		return emi;
	}

	public void setEmi(List<Emi> emi) {
		this.emi = emi;
	}

	public BankingPersonnels getBankingPersonnel() {
		return bankingPersonnel;
	}

	public void setBankingPersonnel(BankingPersonnels bankingPersonnel) {
		this.bankingPersonnel = bankingPersonnel;
	}

	public Customers getCustomers() {
		return customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	public Long getLoanId() {
		return loanId;
	}

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Double getLoanIntrestRate() {
		return loanIntrestRate;
	}

	public void setLoanIntrestRate(Double loanIntrestRate) {
		this.loanIntrestRate = loanIntrestRate;
	}

}
