package ozbank.ozbankapp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Emi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long emiId;

	private Double emiIntrestRate;
	private Double monthylyEmi;
	private Double restAmountOfLoan;

	@Temporal(TemporalType.DATE)
	private Date emiPaymentDate;

	public Date getEmiPaymentDate() {
		return emiPaymentDate;
	}

	public void setEmiPaymentDate(Date emiPaymentDate) {
		this.emiPaymentDate = emiPaymentDate;
	}

	@JsonBackReference
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "Loan_Id")
	Loans loans;

	public Long getEmiId() {
		return emiId;
	}

	public void setEmiId(Long emiId) {
		this.emiId = emiId;
	}

	public Double getEmiIntrestRate() {
		return emiIntrestRate;
	}

	public void setEmiIntrestRate(Double emiIntrestRate) {
		this.emiIntrestRate = emiIntrestRate;
	}

	public Double getMonthylyEmi() {
		return monthylyEmi;
	}

	public void setMonthylyEmi(Double monthylyEmi) {
		this.monthylyEmi = monthylyEmi;
	}

	public Double getRestAmountOfLoan() {
		return restAmountOfLoan;
	}

	public void setRestAmountOfLoan(Double restAmountOfLoan) {
		this.restAmountOfLoan = restAmountOfLoan;
	}

	public Loans getLoans() {
		return loans;
	}

	public void setLoans(Loans loans) {
		this.loans = loans;
	}

}
