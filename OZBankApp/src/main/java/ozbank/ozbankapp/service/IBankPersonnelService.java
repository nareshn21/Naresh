package ozbank.ozbankapp.service;

import java.text.ParseException;

import ozbank.ozbankapp.dto.ResponseDto;

public interface IBankPersonnelService {
	
	public ResponseDto approveLoan(Long personnelId,Long loanId) throws ParseException;

}
