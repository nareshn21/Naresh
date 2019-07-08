package ozbank.ozbankapp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BankingPersonnels {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long personnelsId;
	
	private String personnelDesignation;
	
	@OneToMany(mappedBy="bankingPersonnel")
	List<Loans> loans;
	
	public List<Loans> getLoans() {
		return loans;
	}

	public void setLoans(List<Loans> loans) {
		this.loans = loans;
	}

	public Long getPersonnelsId() {
		return personnelsId;
	}

	public void setPersonnelsId(Long personnelsId) {
		this.personnelsId = personnelsId;
	}

	public String getPersonnelDesignation() {
		return personnelDesignation;
	}

	public void setPersonnelDesignation(String personnelDesignation) {
		this.personnelDesignation = personnelDesignation;
	}

		

}
