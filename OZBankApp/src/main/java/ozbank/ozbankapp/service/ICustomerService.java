package ozbank.ozbankapp.service;

import ozbank.ozbankapp.dto.ResponseDto;
import ozbank.ozbankapp.entity.Customers;

public interface ICustomerService {
	
	public ResponseDto addNewCustomer(Customers customer);
	public ResponseDto emiDetails(Long customerId);
	public ResponseDto emiDetails();
	public ResponseDto deleteLoan(String customerId);

}
