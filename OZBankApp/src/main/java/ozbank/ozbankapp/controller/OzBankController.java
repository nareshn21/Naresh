package ozbank.ozbankapp.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ozbank.ozbankapp.dto.ResponseDto;
import ozbank.ozbankapp.entity.Customers;
import ozbank.ozbankapp.entity.Loans;
import ozbank.ozbankapp.service.IBankPersonnelService;
import ozbank.ozbankapp.service.ICustomerService;
import ozbank.ozbankapp.service.ILoanService;

@RestController
@RequestMapping("/api/ozbank")
public class OzBankController {

	@Autowired
	ICustomerService customerServiceImpl;

	@Autowired
	IBankPersonnelService bankingPersonnelServiceImpl;

	@Autowired
	ILoanService loanImpl;

	@PostMapping("/customers/addnew")
	public ResponseEntity<ResponseDto> addNewCustomer(@RequestBody Customers customer) {

		return new ResponseEntity<ResponseDto>(customerServiceImpl.addNewCustomer(customer), HttpStatus.CREATED);

	}

	@PostMapping("/customers/{customerId}/loan/applyloan")
	public ResponseEntity<ResponseDto> applyLoan(@PathVariable("customerId") String customerId,
			@RequestBody Loans loan) {
		return new ResponseEntity<ResponseDto>(loanImpl.applyLoan(customerId, loan), HttpStatus.CREATED);

	}

	@PostMapping("/bankpersonnel/{personnelId}/approveloan/{loanId}")
	public ResponseEntity<ResponseDto> approveLoan(@PathVariable("personnelId") Long personnelId,
			@PathVariable("loanId") Long loanId) throws ParseException {
		return new ResponseEntity<ResponseDto>(bankingPersonnelServiceImpl.approveLoan(personnelId, loanId),
				HttpStatus.CREATED);
	}

	@GetMapping("/customers/{customerId}/emi")
	public ResponseEntity<ResponseDto> emiDetails(@PathVariable("customerId") Long customerId) {
		return new ResponseEntity<ResponseDto>(customerServiceImpl.emiDetails(customerId), HttpStatus.OK);
	}

	@GetMapping("/customers/emi")
	public ResponseEntity<ResponseDto> emiDetails() {
		return new ResponseEntity<ResponseDto>(customerServiceImpl.emiDetails(), HttpStatus.OK);
	}

	@DeleteMapping("/customers/{customerId}/delete")
	public ResponseEntity<ResponseDto> deleteLoan(@PathVariable("customerId") String customerId) {
		return new ResponseEntity<ResponseDto>(customerServiceImpl.deleteLoan( customerId), HttpStatus.OK);

	}

}
