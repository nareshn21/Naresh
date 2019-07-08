package ozbank.ozbankapp.service;

import ozbank.ozbankapp.dto.ResponseDto;
import ozbank.ozbankapp.entity.Loans;

public interface ILoanService {
	
	//public ResponseDto applyLoan(String customerId);

	public ResponseDto applyLoan(String customerId, Loans loan);

}
