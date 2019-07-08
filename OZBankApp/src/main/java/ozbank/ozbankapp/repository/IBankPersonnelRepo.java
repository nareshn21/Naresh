package ozbank.ozbankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ozbank.ozbankapp.entity.BankingPersonnels;

public interface IBankPersonnelRepo extends JpaRepository<BankingPersonnels, Long> {

}
