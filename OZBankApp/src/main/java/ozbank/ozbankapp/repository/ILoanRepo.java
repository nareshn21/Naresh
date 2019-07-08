package ozbank.ozbankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ozbank.ozbankapp.entity.Loans;

public interface ILoanRepo extends JpaRepository<Loans, Long> {

	@Query("SELECT l from Loans l WHERE l.customers.customerId=:customerId")
	public Loans findLoanAllreadyAppliedOrNot(@Param("customerId") Long customerId);
}
