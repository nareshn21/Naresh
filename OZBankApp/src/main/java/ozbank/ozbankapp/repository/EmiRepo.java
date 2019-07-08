package ozbank.ozbankapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ozbank.ozbankapp.entity.Emi;

public interface EmiRepo extends JpaRepository<Emi, Long>{

	@Query("SELECT e from Emi e where e.loans.loanId=:loanId")
	public List<Emi> isAlreadySetOrNot(@Param("loanId") Long loanId);
}
