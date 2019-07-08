package ozbank.ozbankapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ozbank.ozbankapp.dto.ResponseDto;
import ozbank.ozbankapp.entity.Customers;
import ozbank.ozbankapp.entity.Emi;
import ozbank.ozbankapp.entity.Loans;
import ozbank.ozbankapp.exception.OzBankException;
import ozbank.ozbankapp.repository.EmiRepo;
import ozbank.ozbankapp.repository.ICustomerRepo;
import ozbank.ozbankapp.repository.ILoanRepo;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	ICustomerRepo customerRepo;

	@Autowired
	ILoanRepo loanRepo;

	@Autowired
	EmiRepo emiRepo;

	@Override
	public ResponseDto addNewCustomer(Customers customer) {
		// TODO Auto-generated method stub
		customerRepo.save(customer);
		return new ResponseDto("Customer Added SuccessFully in the Database...");
	}

	@Override
	public ResponseDto emiDetails(Long customerId) {

		try {
			Customers customer = customerRepo.findById(customerId).get();
		} catch (NoSuchElementException ex) {
			throw new OzBankException("Please provide existing Customer Id");
		}
		Loans loan = loanRepo.findLoanAllreadyAppliedOrNot(customerId);
		if (loan != null && "approved".equalsIgnoreCase(loan.getLoanStatus())) {
			List<Emi> emiList = emiRepo.isAlreadySetOrNot(loan.getLoanId());
			List<Emi> emiList1 = new ArrayList<>();
			for (Emi e1 : emiList) {
				emiList1.add(e1);
			}
			ResponseDto response = new ResponseDto();
			response.setEmi(emiList1);
			return response;

		} else {
			throw new OzBankException("This Customer's LoanStatus is not APPROVED SO, can't get Emi List");
		}
		// return null;
	}

	@Override
	public ResponseDto emiDetails() {
		List<Long> customersIds = customerRepo.getCustomersIds();
		List<Map<Long, List<Emi>>> emiOfAllCustomers = new ArrayList<>();
		
		if (customersIds != null && !customersIds.isEmpty()) {

			for (Long custIds : customersIds) {
				Loans loan = loanRepo.findLoanAllreadyAppliedOrNot(custIds);
				// if(loan !=null && "approved".equalsIgnoreCase(loan.getLoanStatus())) {
				Map<Long, List<Emi>> temp = new HashMap<>();

				List<Emi> emiList = emiRepo.isAlreadySetOrNot(loan.getLoanId());
				List<Emi> emiList1 = new ArrayList<>();
				for (Emi e1 : emiList) {
					emiList1.add(e1);
				}
				temp.put(custIds, emiList1);
				emiOfAllCustomers.add(temp);

				// }

			}
			ResponseDto response = new ResponseDto();
			response.setEmiAllCustomers(emiOfAllCustomers);
			return response;

		} else {
			throw new OzBankException("No Customer Found which loan has been approved..");
		}

	}

	@Override
	public ResponseDto deleteLoan(String customerId) {
		
		Long custId;
		try {
			custId=Long.parseLong(customerId);
		}catch(NumberFormatException ex) {
			throw new OzBankException("Please provide valid Customer Id");
		}
		String loanStatusOfCustomer=customerRepo.getLoanStatusWhileDeleting(custId);
		if(!"approved".equalsIgnoreCase(String.valueOf(loanStatusOfCustomer))) {
			Loans loan=loanRepo.findLoanAllreadyAppliedOrNot(custId);
			if(loan ==null)
			{
				throw new OzBankException("Customer doesn't apply for the Loan yet...");
			}
			loan.setLoanStatus("Deleted");
			loanRepo.save(loan);
			return new ResponseDto("Customer Id:"+custId+" is Deleted ");
		}else
		{
			throw new OzBankException("Customer's Emi is Running Can't be deleted...");
		}
		// TODO Auto-generated method stub
		//return null;
	}

}
